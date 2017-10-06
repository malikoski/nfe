package com.fincatto.documentofiscal.nfe400.webservices;

import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.nfe.NFeConfig;
import com.fincatto.documentofiscal.nfe.assinatura.AssinaturaDigital;
import com.fincatto.documentofiscal.nfe.classes.evento.NFEnviaEventoRetorno;
import com.fincatto.documentofiscal.nfe.classes.evento.NFEvento;
import com.fincatto.documentofiscal.nfe.classes.evento.NFInfoEvento;
import com.fincatto.documentofiscal.nfe.classes.evento.NFTipoEvento;
import com.fincatto.documentofiscal.nfe.classes.evento.cartacorrecao.NFEnviaEventoCartaCorrecao;
import com.fincatto.documentofiscal.nfe.parsers.NotaFiscalChaveParser;
import com.fincatto.documentofiscal.nfe.persister.NFPersister;
import com.fincatto.documentofiscal.nfe400.ElementStringConverter;
import com.fincatto.documentofiscal.nfe400.classes.NFAutorizador4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeDadosMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeResultMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.recepcaoevento.NFeRecepcaoEvento4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.recepcaoevento.NFeRecepcaoEvento4Soap12;
import java.math.BigDecimal;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collections;
import javax.xml.stream.XMLStreamException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

class CartaCorrecao {

    private static final BigDecimal VERSAO_LEIAUTE = new BigDecimal("1.00");
    private static final String EVENTO_CODIGO = "110110";
    private static final String EVENTO_DESCRICAO = "Carta de Correcao";
    private static final String EVENTO_CONDICAO_USO
            = "A Carta de Correcao e disciplinada pelo paragrafo 1o-A do art. 7o do Convenio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularizacao de erro ocorrido na emissao de documento fiscal, desde que o erro nao esteja relacionado com: I - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da operacao ou da prestacao; II - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario; III - a data de emissao ou de saida.";
    private final static Logger LOGGER = LoggerFactory.getLogger(CartaCorrecao.class);
    private final NFeConfig config;

    CartaCorrecao(final NFeConfig config) {
        this.config = config;
    }

    NFEnviaEventoRetorno corrigeNota(final String chaveAcesso, final String textoCorrecao,
            final int numeroSequencialEvento) throws Exception {
        final String cartaCorrecaoXML
                = this.gerarDadosCartaCorrecao(chaveAcesso, textoCorrecao, numeroSequencialEvento).toString();
        final String xmlAssinado = new AssinaturaDigital(this.config).assinarDocumento(cartaCorrecaoXML);

        return this.efetuaCorrecao(xmlAssinado, chaveAcesso);
    }

    NFEnviaEventoRetorno corrigeNotaAssinada(final String chaveAcesso, final String eventoAssinadoXml) throws Exception {
        return this.efetuaCorrecao(eventoAssinadoXml, chaveAcesso);
    }

    private NFEnviaEventoRetorno efetuaCorrecao(final String xmlAssinado, final String chaveAcesso) throws
            XMLStreamException, RemoteException, Exception {

        final NfeDadosMsg dadosMsg = new NfeDadosMsg();
        dadosMsg.getContent().add(ElementStringConverter.read(xmlAssinado));

        CartaCorrecao.LOGGER.debug(xmlAssinado);

        final NotaFiscalChaveParser parser = new NotaFiscalChaveParser(chaveAcesso);

        final NFAutorizador4 autorizador = NFAutorizador4.valueOfCodigoUF(this.config.getCUF());
        final String target = DFModelo.NFCE.equals(parser.getModelo()) ? autorizador.getNfceRecepcaoEvento(
                this.config.getAmbiente()) : autorizador.getRecepcaoEvento(this.config.getAmbiente());
        if (target == null) {
            throw new IllegalArgumentException(String.format(
                    "Nao foi possivel encontrar URL para RecepcaoEvento - Modelo: %s - Autorizador: %s",
                    parser.getModelo().name(), autorizador.name()));
        }

        final NFeRecepcaoEvento4Soap12 recepcaoEvento
                = new NFeRecepcaoEvento4(new URL(target)).getNFeRecepcaoEvento4Soap12();

        final NfeResultMsg result = recepcaoEvento.nfeRecepcaoEvento(dadosMsg);

        NFEnviaEventoRetorno eventoRetorno = new NFPersister().read(NFEnviaEventoRetorno.class,
                ElementStringConverter.write((Element) result.getContent().get(0)));
        CartaCorrecao.LOGGER.debug(eventoRetorno.toString());

        return eventoRetorno;
    }

    private NFEnviaEventoCartaCorrecao gerarDadosCartaCorrecao(final String chaveAcesso, final String textoCorrecao,
            final int numeroSequencialEvento) {
        final NotaFiscalChaveParser chaveParser = new NotaFiscalChaveParser(chaveAcesso);

        final NFTipoEvento cartaCorrecao = new NFTipoEvento();
        cartaCorrecao.setVersao(CartaCorrecao.VERSAO_LEIAUTE);
        cartaCorrecao.setDescricaoEvento(EVENTO_DESCRICAO);
        cartaCorrecao.setCondicaoUso(EVENTO_CONDICAO_USO);
        cartaCorrecao.setTextoCorrecao(textoCorrecao);

        final NFInfoEvento infoEvento = new NFInfoEvento();
        infoEvento.setAmbiente(this.config.getAmbiente());
        infoEvento.setDadosEvento(cartaCorrecao);
        infoEvento.setChave(chaveAcesso);
        infoEvento.setCnpj(chaveParser.getCnpjEmitente());
        infoEvento.setDataHoraEvento(DateTime.now());
        infoEvento.setId(String.format("ID%s%s%02d", CartaCorrecao.EVENTO_CODIGO, chaveAcesso, numeroSequencialEvento));
        infoEvento.setNumeroSequencialEvento(numeroSequencialEvento);
        infoEvento.setOrgao(chaveParser.getNFUnidadeFederativa());
        infoEvento.setTipoEvento(CartaCorrecao.EVENTO_CODIGO);
        infoEvento.setVersaoEvento(CartaCorrecao.VERSAO_LEIAUTE);

        final NFEvento evento = new NFEvento();
        evento.setInfoEvento(infoEvento);
        evento.setVersao(CartaCorrecao.VERSAO_LEIAUTE);

        final NFEnviaEventoCartaCorrecao enviaEvento = new NFEnviaEventoCartaCorrecao();
        enviaEvento.setEvento(Collections.singletonList(evento));
        enviaEvento.setIdLote(Long.toString(DateTime.now().getMillis()));
        enviaEvento.setVersao(CartaCorrecao.VERSAO_LEIAUTE);
        return enviaEvento;
    }
}
