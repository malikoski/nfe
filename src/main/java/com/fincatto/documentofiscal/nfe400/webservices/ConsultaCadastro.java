package com.fincatto.documentofiscal.nfe400.webservices;

import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.nfe.NFeConfig;
import com.fincatto.documentofiscal.nfe.classes.cadastro.NFConsultaCadastro;
import com.fincatto.documentofiscal.nfe.classes.cadastro.NFInfoConsultaCadastro;
import com.fincatto.documentofiscal.nfe.classes.cadastro.NFRetornoConsultaCadastro;
import com.fincatto.documentofiscal.nfe400.ElementStringConverter;
import com.fincatto.documentofiscal.nfe400.classes.NFAutorizador4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeDadosMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeResultMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.consultacadastro.CadConsultaCadastro4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.consultacadastro.CadConsultaCadastro4Soap12;
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

class ConsultaCadastro {

    private static final Logger LOG = LoggerFactory.getLogger(ConsultaCadastro.class);
    private static final String NOME_SERVICO = "CONS-CAD";
    private static final String VERSAO_SERVICO = "2.00";
    private final NFeConfig config;

    ConsultaCadastro(final NFeConfig config) {
        this.config = config;
    }

    NFRetornoConsultaCadastro consultaCadastro(final String cnpj, final DFUnidadeFederativa uf) throws Exception {
        final NFConsultaCadastro dadosConsulta = this.getDadosConsulta(cnpj, uf);
        final String xmlConsulta = dadosConsulta.toString();
        ConsultaCadastro.LOG.debug(xmlConsulta);

        final String resultado = this.efetuaConsulta(uf, xmlConsulta);
        ConsultaCadastro.LOG.debug(resultado);

        return new Persister(new DFRegistryMatcher(), new Format(0)).read(NFRetornoConsultaCadastro.class, resultado);
    }

    private String efetuaConsulta(final DFUnidadeFederativa uf, final String xml) throws RemoteException,
            MalformedURLException {

        final NfeDadosMsg nfeDadosMsg = new NfeDadosMsg();
        nfeDadosMsg.getContent().add(ElementStringConverter.read(xml));

        final NFAutorizador4 autorizador = NFAutorizador4.valueOfCodigoUF(uf);
        final String target = autorizador.getNfeConsultaProtocolo(this.config.getAmbiente());

        if (StringUtils.isEmpty(target)) {
            throw new IllegalArgumentException(String.format(
                    "Nao foi possivel encontrar URL para ConsultaCadastro - Autorizador:%s", autorizador.name()));
        }

        ConsultaCadastro.LOG.debug(String.format("Target: %s", target));

        CadConsultaCadastro4Soap12 consultaCadastro
                = new CadConsultaCadastro4(new URL(target)).getCadConsultaCadastro4Soap12();

        NfeResultMsg result = consultaCadastro.consultaCadastro(nfeDadosMsg);

        return ElementStringConverter.write((Element) result.getContent().get(0));
    }

    private NFConsultaCadastro getDadosConsulta(final String cnpj, final DFUnidadeFederativa uf) {
        final NFConsultaCadastro consulta = new NFConsultaCadastro();
        
        consulta.setVersao(ConsultaCadastro.VERSAO_SERVICO);
        consulta.setConsultaCadastro(new NFInfoConsultaCadastro());
        consulta.getConsultaCadastro().setCnpj(cnpj);
        consulta.getConsultaCadastro().setServico(ConsultaCadastro.NOME_SERVICO);
        consulta.getConsultaCadastro().setUf(uf.getCodigo());
        
        return consulta;
    }
}
