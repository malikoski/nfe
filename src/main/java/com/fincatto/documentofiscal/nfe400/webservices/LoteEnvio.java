package com.fincatto.documentofiscal.nfe400.webservices;

import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.nfe.NFeConfig;
import com.fincatto.documentofiscal.nfe.assinatura.AssinaturaDigital;
import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteEnvio;
import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteEnvioRetorno;
import com.fincatto.documentofiscal.nfe.classes.lote.envio.NFLoteEnvioRetornoDados;
import com.fincatto.documentofiscal.nfe.classes.nota.NFNota;
import com.fincatto.documentofiscal.nfe.classes.nota.NFNotaInfoSuplementar;
import com.fincatto.documentofiscal.nfe.parsers.NotaParser;
import com.fincatto.documentofiscal.nfe.persister.NFPersister;
import com.fincatto.documentofiscal.nfe.utils.NFGeraChave;
import com.fincatto.documentofiscal.nfe.utils.NFGeraQRCode;
import com.fincatto.documentofiscal.nfe.validadores.xsd.XMLValidador;
import com.fincatto.documentofiscal.nfe400.ElementStringConverter;
import com.fincatto.documentofiscal.nfe400.classes.NFAutorizador4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeDadosMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeResultMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.autorizacao.NFeAutorizacao4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.autorizacao.NFeAutorizacao4Soap12;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

class LoteEnvio {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoteEnvio.class);
    private final NFeConfig config;

    LoteEnvio(final NFeConfig config) {
        this.config = config;
    }

    NFLoteEnvioRetorno enviaLoteAssinado(final String loteAssinadoXml, final DFModelo modelo) throws Exception {
        return this.comunicaLote(loteAssinadoXml, modelo);
    }

    NFLoteEnvioRetornoDados enviaLote(final NFLoteEnvio lote) throws Exception {
        // adiciona a chave e o dv antes de assinar
        for (final NFNota nota : lote.getNotas()) {
            final NFGeraChave geraChave = new NFGeraChave(nota);
            nota.getInfo().getIdentificacao().setCodigoRandomico(StringUtils.defaultIfBlank(nota.getInfo().getIdentificacao().getCodigoRandomico(), geraChave.geraCodigoRandomico()));
            nota.getInfo().getIdentificacao().setDigitoVerificador(geraChave.getDV());
            nota.getInfo().setIdentificador(geraChave.getChaveAcesso());
        }

        // assina o lote
        final String documentoAssinado = new AssinaturaDigital(this.config).assinarDocumento(lote.toString());
        final NFLoteEnvio loteAssinado = new NotaParser().loteParaObjeto(documentoAssinado);

        // verifica se nao tem NFCe junto com NFe no lote e gera qrcode (apos assinar mesmo, eh assim)
        int qtdNF = 0, qtdNFC = 0;
        for (final NFNota nota : loteAssinado.getNotas()) {
            switch (nota.getInfo().getIdentificacao().getModelo()) {
                case NFE:
                    qtdNF++;
                    break;
                case NFCE:
                    final NFGeraQRCode geraQRCode = new NFGeraQRCode(nota, this.config);
                    nota.setInfoSuplementar(new NFNotaInfoSuplementar());
                    nota.getInfoSuplementar().setQrCode(geraQRCode.getQRCode());
                    qtdNFC++;
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Modelo de nota desconhecida: %s", nota.getInfo().getIdentificacao().getModelo()));
            }
        }

        // verifica se todas as notas do lote sao do mesmo modelo
        if ((qtdNF > 0) && (qtdNFC > 0)) {
            throw new IllegalArgumentException("Lote contendo notas de modelos diferentes!");
        }

        // guarda o modelo das notas
        final DFModelo modelo = qtdNFC > 0 ? DFModelo.NFCE : DFModelo.NFE;

        // comunica o lote
        final NFLoteEnvioRetorno loteEnvioRetorno = this.comunicaLote(loteAssinado.toString(), modelo);
        return new NFLoteEnvioRetornoDados(loteEnvioRetorno, loteAssinado);
    }

    private NFLoteEnvioRetorno comunicaLote(final String loteAssinadoXml, final DFModelo modelo) throws Exception {
        //valida o lote assinado, para verificar se o xsd foi satisfeito, antes de comunicar com a sefaz
        XMLValidador.validaLote4(loteAssinadoXml);

        final NfeDadosMsg dadosMsg = new NfeDadosMsg();
        dadosMsg.getContent().add(ElementStringConverter.read(loteAssinadoXml));

        //define o tipo de emissao
        final NFAutorizador4 autorizador = NFAutorizador4.valueOfTipoEmissao(this.config.getTipoEmissao(), this.config.getCUF());

        final String target = DFModelo.NFE.equals(modelo) ? autorizador.getNfeAutorizacao(this.config.getAmbiente()) : autorizador.getNfceAutorizacao(this.config.getAmbiente());
        
        if (target == null) {
            throw new IllegalArgumentException("Nao foi possivel encontrar URL para Autorizacao " + modelo.name() + ", autorizador " + autorizador.name());
        }

        final NFeAutorizacao4Soap12 autorizacao = new NFeAutorizacao4(new URL(target)).getNFeAutorizacao4Soap12();
        
        final NfeResultMsg result = autorizacao.nfeAutorizacaoLote(dadosMsg);
        
        final NFLoteEnvioRetorno loteEnvioRetorno = new NFPersister().read(NFLoteEnvioRetorno.class, ElementStringConverter.write((Element) result.getContent().get(0)));
        
        LoteEnvio.LOGGER.info(loteEnvioRetorno.toString());
        
        return loteEnvioRetorno;
    }
}
