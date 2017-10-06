package com.fincatto.documentofiscal.nfe400.webservices;

import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.nfe.NFeConfig;
import com.fincatto.documentofiscal.nfe.classes.statusservico.consulta.NFStatusServicoConsulta;
import com.fincatto.documentofiscal.nfe.classes.statusservico.consulta.NFStatusServicoConsultaRetorno;
import com.fincatto.documentofiscal.nfe400.ElementStringConverter;
import com.fincatto.documentofiscal.nfe400.classes.NFAutorizador4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeDadosMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeResultMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.statusservico.NFeStatusServico4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.statusservico.NFeStatusServico4Soap12;
import com.fincatto.documentofiscal.transformers.DFRegistryMatcher;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import org.apache.commons.lang3.StringUtils;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

/**
 * Class: StatusConsulta.
 *
 * <p>
 * Insert description here.
 * </p>
 *
 * <p>
 * History:<br><br>
 * - Walter Portugal - Oct 2, 2017: Criação do Arquivo<br>
 * <p>
 *
 * @author Walter Portugal
 * @since 1.0
 *
 */
public class StatusConsulta {

    private static final String NOME_SERVICO = "STATUS";
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusConsulta.class);
    private final NFeConfig config;

    public StatusConsulta(final NFeConfig config) {
        this.config = config;
    }

    public NFStatusServicoConsultaRetorno consultaStatus(final DFUnidadeFederativa uf, final DFModelo modelo) throws
            Exception {
        return new Persister(new DFRegistryMatcher(), new Format(0)).read(NFStatusServicoConsultaRetorno.class,
                this.efetuaConsultaStatus(gerarDadosConsulta(uf).toString(), uf, modelo));
    }

    private NFStatusServicoConsulta gerarDadosConsulta(final DFUnidadeFederativa unidadeFederativa) {
        final NFStatusServicoConsulta consStatServ = new NFStatusServicoConsulta();
        consStatServ.setUf(unidadeFederativa);
        consStatServ.setAmbiente(this.config.getAmbiente());
        consStatServ.setVersao(this.config.getVersao());
        consStatServ.setServico(StatusConsulta.NOME_SERVICO);
        return consStatServ;
    }

    private String efetuaConsultaStatus(final String xml, final DFUnidadeFederativa unidadeFederativa,
            final DFModelo modelo) throws RemoteException, MalformedURLException {

        NfeDadosMsg dadosMsg = new NfeDadosMsg();
        dadosMsg.getContent().add(ElementStringConverter.read(xml));
        StatusConsulta.LOGGER.debug(xml);

        final NFAutorizador4 autorizador = NFAutorizador4.valueOfCodigoUF(unidadeFederativa);
        final String target = modelo == DFModelo.NFCE ? autorizador.getNfeStatusServico(this.config.getAmbiente()) :
                autorizador.getNfeStatusServico(this.config.getAmbiente());

        if (StringUtils.isEmpty(target)) {
            throw new IllegalArgumentException(String.format(
                    "Nao foi possivel encontrar URL para StatusServico - Modelo: %s - Autorizador: %s", modelo.toString(),
                    autorizador.name()));
        }

        NFeStatusServico4Soap12 port = new NFeStatusServico4(new URL(target)).getNFeStatusServico4Soap12();

        NfeResultMsg result = port.nfeStatusServicoNF(dadosMsg);
        
        String xmlResult = ElementStringConverter.write((Element) result.getContent().get(0));
        StatusConsulta.LOGGER.debug(xmlResult);

        return xmlResult;
    }
}
