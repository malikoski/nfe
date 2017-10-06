package com.fincatto.documentofiscal.nfe400.webservices;

import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.nfe.NFeConfig;
import com.fincatto.documentofiscal.nfe.assinatura.AssinaturaDigital;
import com.fincatto.documentofiscal.nfe.classes.evento.inutilizacao.NFEnviaEventoInutilizacao;
import com.fincatto.documentofiscal.nfe.classes.evento.inutilizacao.NFEventoInutilizacaoDados;
import com.fincatto.documentofiscal.nfe.classes.evento.inutilizacao.NFRetornoEventoInutilizacao;
import com.fincatto.documentofiscal.nfe.persister.NFPersister;
import com.fincatto.documentofiscal.nfe400.ElementStringConverter;
import com.fincatto.documentofiscal.nfe400.classes.NFAutorizador4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeDadosMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeResultMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.inutilizacao.NFeInutilizacao4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.inutilizacao.NFeInutilizacao4Soap12;
import java.math.BigDecimal;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

class Inutilizacao {

    private static final String VERSAO_SERVICO = "3.10";
    private static final String NOME_SERVICO = "INUTILIZAR";
    private static final Logger LOGGER = LoggerFactory.getLogger(Inutilizacao.class);
    private final NFeConfig config;

    Inutilizacao(final NFeConfig config) {
        this.config = config;
    }

    NFRetornoEventoInutilizacao inutilizaNotaAssinada(final String eventoAssinadoXml, final DFModelo modelo) throws
            Exception {
        return efetuaInutilizacao(eventoAssinadoXml, modelo);
    }

    NFRetornoEventoInutilizacao inutilizaNota(final int anoInutilizacaoNumeracao, final String cnpjEmitente,
            final String serie, final String numeroInicial, final String numeroFinal, final String justificativa,
            final DFModelo modelo) throws Exception {
        final String inutilizacaoXML = this.geraDadosInutilizacao(anoInutilizacaoNumeracao, cnpjEmitente, serie,
                numeroInicial, numeroFinal, justificativa, modelo).toString();
        final String inutilizacaoXMLAssinado = new AssinaturaDigital(this.config).assinarDocumento(inutilizacaoXML);

        return efetuaInutilizacao(inutilizacaoXMLAssinado, modelo);
    }

    private NFRetornoEventoInutilizacao efetuaInutilizacao(final String inutilizacaoXMLAssinado, final DFModelo modelo)
            throws Exception {

        final NfeDadosMsg dadosMsg = new NfeDadosMsg();
        dadosMsg.getContent().add(ElementStringConverter.read(inutilizacaoXMLAssinado));

        final NFAutorizador4 autorizador = NFAutorizador4.valueOfCodigoUF(this.config.getCUF());
        final String target
                = DFModelo.NFE.equals(modelo) ? autorizador.getNfeInutilizacao(this.config.getAmbiente()) : autorizador.getNfceInutilizacao(
                this.config.getAmbiente());

        final NFeInutilizacao4Soap12 inutilizacao = new NFeInutilizacao4(new URL(target)).getNFeInutilizacao4Soap12();

        NfeResultMsg result = inutilizacao.nfeInutilizacaoNF(dadosMsg);

        NFRetornoEventoInutilizacao retornoEventoInutilizacao
                = new NFPersister().read(NFRetornoEventoInutilizacao.class, ElementStringConverter.write(
                        (Element) result.getContent().get(0)));

        Inutilizacao.LOGGER.info(retornoEventoInutilizacao.toString());
        
        return retornoEventoInutilizacao;
    }

    private NFEnviaEventoInutilizacao geraDadosInutilizacao(final int anoInutilizacaoNumeracao,
            final String cnpjEmitente, final String serie, final String numeroInicial, final String numeroFinal,
            final String justificativa, final DFModelo modelo) {
        final NFEnviaEventoInutilizacao inutilizacao = new NFEnviaEventoInutilizacao();
        final NFEventoInutilizacaoDados dados = new NFEventoInutilizacaoDados();
        dados.setAmbiente(this.config.getAmbiente());
        dados.setAno(anoInutilizacaoNumeracao);
        dados.setCnpj(cnpjEmitente);
        dados.setJustificativa(justificativa);
        dados.setModeloDocumentoFiscal(modelo.getCodigo());
        dados.setNomeServico(Inutilizacao.NOME_SERVICO);
        dados.setNumeroNFInicial(numeroInicial);
        dados.setNumeroNFFinal(numeroFinal);
        dados.setSerie(serie);
        dados.setUf(this.config.getCUF());
        final String numeroInicialTamanhoMaximo = StringUtils.leftPad(numeroInicial, 9, "0");
        final String numeroFinalTamanhoMaximo = StringUtils.leftPad(numeroFinal, 9, "0");
        final String serieTamanhoMaximo = StringUtils.leftPad(serie, 3, "0");
        dados.setIdentificador(
                "ID" + this.config.getCUF().getCodigoIbge() + String.valueOf(anoInutilizacaoNumeracao) + cnpjEmitente + modelo.getCodigo() + serieTamanhoMaximo + numeroInicialTamanhoMaximo + numeroFinalTamanhoMaximo);

        inutilizacao.setVersao(new BigDecimal(Inutilizacao.VERSAO_SERVICO));
        inutilizacao.setDados(dados);
        return inutilizacao;
    }
}
