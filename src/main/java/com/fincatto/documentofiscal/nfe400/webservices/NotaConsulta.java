package com.fincatto.documentofiscal.nfe400.webservices;

import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.nfe.NFeConfig;
import com.fincatto.documentofiscal.nfe.classes.nota.consulta.NFNotaConsulta;
import com.fincatto.documentofiscal.nfe.classes.nota.consulta.NFNotaConsultaRetorno;
import com.fincatto.documentofiscal.nfe.parsers.NotaFiscalChaveParser;
import com.fincatto.documentofiscal.nfe400.ElementStringConverter;
import com.fincatto.documentofiscal.nfe400.classes.NFAutorizador4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeDadosMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeResultMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.consultaprotocolo.NFeConsultaProtocolo4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.consultaprotocolo.NFeConsultaProtocolo4Soap12;
import com.fincatto.documentofiscal.transformers.DFRegistryMatcher;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

class NotaConsulta {

    private static final String NOME_SERVICO = "CONSULTAR";
    private static final String VERSAO_SERVICO = "3.10";
    private final static Logger LOGGER = LoggerFactory.getLogger(NotaConsulta.class);
    private final NFeConfig config;

    NotaConsulta(final NFeConfig config) {
        this.config = config;
    }

    NFNotaConsultaRetorno consultaNota(final String chaveDeAcesso) throws Exception {
        String xmlConsultaProtocolo = efetuaConsulta(this.gerarDadosConsulta(chaveDeAcesso).toString(), chaveDeAcesso);

        NotaConsulta.LOGGER.debug(xmlConsultaProtocolo);

        return new Persister(new DFRegistryMatcher(), new Format(0)).read(NFNotaConsultaRetorno.class,
                xmlConsultaProtocolo);
    }

    private String efetuaConsulta(final String xml, final String chaveDeAcesso) throws RemoteException,
            MalformedURLException {

        final NotaFiscalChaveParser notaFiscalChaveParser = new NotaFiscalChaveParser(chaveDeAcesso);

        final boolean consultaNFeBahia
                = DFUnidadeFederativa.BA.equals(notaFiscalChaveParser.getNFUnidadeFederativa()) && DFModelo.NFE.equals(
                notaFiscalChaveParser.getModelo());
        
        if (consultaNFeBahia) {
            return this.efetuaConsultaBA(xml, chaveDeAcesso, notaFiscalChaveParser.getModelo());
        } else {
            return this.efetuaConsulta(xml, chaveDeAcesso, notaFiscalChaveParser.getModelo());
        }

    }

    private String efetuaConsulta(final String xml, final String chaveDeAcesso, DFModelo modelo) throws
            RemoteException, MalformedURLException {

        final NfeDadosMsg dadosMsg = new NfeDadosMsg();
        dadosMsg.getContent().add(ElementStringConverter.read(xml));

        final NFAutorizador4 autorizador = NFAutorizador4.valueOfChaveAcesso(chaveDeAcesso);
        final String target = DFModelo.NFCE.equals(modelo) ? autorizador.getNfceConsultaProtocolo(
                this.config.getAmbiente()) : autorizador.getNfeConsultaProtocolo(this.config.getAmbiente());
        if (target == null) {
            throw new IllegalArgumentException(
                    "Nao foi possivel encontrar URL para ConsultaProtocolo " + modelo.name() + ", autorizador " + autorizador.name());
        }

        NFeConsultaProtocolo4Soap12 consultaProtocolo
                = new NFeConsultaProtocolo4(new URL(target)).getNFeConsultaProtocolo4Soap12();

        NfeResultMsg result = consultaProtocolo.nfeConsultaNF(dadosMsg);

        return ElementStringConverter.write((Element) result.getContent().get(0));
    }

    private String efetuaConsultaBA(final String xml, final String chaveDeAcesso, DFModelo modelo) throws
            RemoteException {
        return "";
    }

    private NFNotaConsulta gerarDadosConsulta(final String chaveDeAcesso) {
        final NFNotaConsulta notaConsulta = new NFNotaConsulta();
        notaConsulta.setAmbiente(this.config.getAmbiente());
        notaConsulta.setChave(chaveDeAcesso);
        notaConsulta.setServico(NotaConsulta.NOME_SERVICO);
        notaConsulta.setVersao(new BigDecimal(NotaConsulta.VERSAO_SERVICO));
        return notaConsulta;
    }
}
