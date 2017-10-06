package com.fincatto.documentofiscal.nfe400.webservices;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.nfe.NFeConfig;
import com.fincatto.documentofiscal.nfe.assinatura.AssinaturaDigital;
import com.fincatto.documentofiscal.nfe.classes.evento.NFEnviaEventoRetorno;
import com.fincatto.documentofiscal.nfe.classes.evento.cancelamento.NFEnviaEventoCancelamento;
import com.fincatto.documentofiscal.nfe.classes.evento.cancelamento.NFEventoCancelamento;
import com.fincatto.documentofiscal.nfe.classes.evento.cancelamento.NFInfoCancelamento;
import com.fincatto.documentofiscal.nfe.classes.evento.cancelamento.NFInfoEventoCancelamento;
import com.fincatto.documentofiscal.nfe.parsers.NotaFiscalChaveParser;
import com.fincatto.documentofiscal.nfe.persister.NFPersister;
import com.fincatto.documentofiscal.nfe400.ElementStringConverter;
import com.fincatto.documentofiscal.nfe400.classes.NFAutorizador4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeResultMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.recepcaoevento.NFeRecepcaoEvento4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.recepcaoevento.NFeRecepcaoEvento4Soap12;

import java.math.BigDecimal;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collections;
import javax.xml.stream.XMLStreamException;
import org.w3c.dom.Element;

class Cancelamento {
    private static final String DESCRICAO_EVENTO = "Cancelamento";
    private static final BigDecimal VERSAO_LEIAUTE = new BigDecimal("1.00");
    private static final String EVENTO_CANCELAMENTO = "110111";
    private static final Logger LOGGER = LoggerFactory.getLogger(Cancelamento.class);
    private final NFeConfig config;

    Cancelamento(final NFeConfig config) {
        this.config = config;
    }

    NFEnviaEventoRetorno cancelaNotaAssinada(final String chaveAcesso, final String eventoAssinadoXml) throws Exception {
        return this.efetuaCancelamento(eventoAssinadoXml, chaveAcesso);
    }

    NFEnviaEventoRetorno cancelaNota(final String chaveAcesso, final String numeroProtocolo, final String motivo) throws Exception {
        final String cancelamentoNotaXML = this.gerarDadosCancelamento(chaveAcesso, numeroProtocolo, motivo).toString();
        final String xmlAssinado = new AssinaturaDigital(this.config).assinarDocumento(cancelamentoNotaXML);
        
        return this.efetuaCancelamento(xmlAssinado, chaveAcesso);
    }

    private NFEnviaEventoRetorno efetuaCancelamento(final String xmlAssinado, final String chaveAcesso) throws
            XMLStreamException, RemoteException, Exception {

        final com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeDadosMsg dadosMsg = new com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeDadosMsg();
        dadosMsg.getContent().add(ElementStringConverter.read(xmlAssinado));

        Cancelamento.LOGGER.debug(xmlAssinado);

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
        Cancelamento.LOGGER.debug(eventoRetorno.toString());

        return eventoRetorno;
    }

    private NFEnviaEventoCancelamento gerarDadosCancelamento(final String chaveAcesso, final String numeroProtocolo, final String motivo) {
        final NotaFiscalChaveParser chaveParser = new NotaFiscalChaveParser(chaveAcesso);

        final NFInfoCancelamento cancelamento = new NFInfoCancelamento();
        cancelamento.setDescricaoEvento(Cancelamento.DESCRICAO_EVENTO);
        cancelamento.setVersao(Cancelamento.VERSAO_LEIAUTE);
        cancelamento.setJustificativa(motivo);
        cancelamento.setProtocoloAutorizacao(numeroProtocolo);

        final NFInfoEventoCancelamento infoEvento = new NFInfoEventoCancelamento();
        infoEvento.setAmbiente(this.config.getAmbiente());
        infoEvento.setChave(chaveAcesso);
        infoEvento.setCnpj(chaveParser.getCnpjEmitente());
        infoEvento.setDataHoraEvento(DateTime.now());
        infoEvento.setId(String.format("ID%s%s0%s", Cancelamento.EVENTO_CANCELAMENTO, chaveAcesso, "1"));
        infoEvento.setNumeroSequencialEvento(1);
        infoEvento.setOrgao(chaveParser.getNFUnidadeFederativa());
        infoEvento.setCodigoEvento(Cancelamento.EVENTO_CANCELAMENTO);
        infoEvento.setVersaoEvento(Cancelamento.VERSAO_LEIAUTE);
        infoEvento.setCancelamento(cancelamento);

        final NFEventoCancelamento evento = new NFEventoCancelamento();
        evento.setInfoEvento(infoEvento);
        evento.setVersao(Cancelamento.VERSAO_LEIAUTE);

        final NFEnviaEventoCancelamento enviaEvento = new NFEnviaEventoCancelamento();
        enviaEvento.setEvento(Collections.singletonList(evento));
        enviaEvento.setIdLote(Long.toString(DateTime.now().getMillis()));
        enviaEvento.setVersao(Cancelamento.VERSAO_LEIAUTE);
        return enviaEvento;
    }
}
