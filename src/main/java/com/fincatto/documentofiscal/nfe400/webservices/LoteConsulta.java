package com.fincatto.documentofiscal.nfe400.webservices;

import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.nfe.NFeConfig;
import com.fincatto.documentofiscal.nfe.classes.lote.consulta.NFLoteConsulta;
import com.fincatto.documentofiscal.nfe.classes.lote.consulta.NFLoteConsultaRetorno;
import com.fincatto.documentofiscal.nfe400.ElementStringConverter;
import com.fincatto.documentofiscal.nfe400.classes.NFAutorizador4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeDadosMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.NfeResultMsg;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.retautorizacao.NFeRetAutorizacao4;
import com.fincatto.documentofiscal.nfe400.webservices.stubs.retautorizacao.NFeRetAutorizacao4Soap12;
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

class LoteConsulta {

    final private static Logger LOGGER = LoggerFactory.getLogger(LoteConsulta.class);
    private final NFeConfig config;

    LoteConsulta(final NFeConfig config) {
        this.config = config;
    }

    NFLoteConsultaRetorno consultaLote(final String numeroRecibo, final DFModelo modelo) throws Exception {
        String retorno = efetuaConsulta(gerarDadosConsulta(numeroRecibo).toString(), modelo);

        return new Persister(new DFRegistryMatcher(), new Format(0)).read(NFLoteConsultaRetorno.class, retorno);
    }

    private String efetuaConsulta(String xml, final DFModelo modelo) throws RemoteException, MalformedURLException {
        final NfeDadosMsg dadosMsg = new NfeDadosMsg();
        dadosMsg.getContent().add(ElementStringConverter.read(xml));

        final NFAutorizador4 autorizador = NFAutorizador4.valueOfTipoEmissao(this.config.getTipoEmissao(),
                this.config.getCUF());
        final String target
                = DFModelo.NFCE.equals(modelo) ? autorizador.getNfceRetAutorizacao(this.config.getAmbiente()) : autorizador.getNfeRetAutorizacao(
                this.config.getAmbiente());
        if (target == null) {
            throw new IllegalArgumentException(
                    "Nao foi possivel encontrar URL para RetAutorizacao " + modelo.name() + ", autorizador " + autorizador.name());
        }

        final NFeRetAutorizacao4Soap12 retAutorizacao
                = new NFeRetAutorizacao4(new URL(target)).getNFeRetAutorizacao4Soap12();

        NfeResultMsg result = retAutorizacao.nfeRetAutorizacaoLote(dadosMsg);

        return ElementStringConverter.write((Element) result.getContent().get(0));
    }

    private NFLoteConsulta gerarDadosConsulta(final String numeroRecibo) {
        final NFLoteConsulta consulta = new NFLoteConsulta();
        consulta.setRecibo(numeroRecibo);
        consulta.setAmbiente(this.config.getAmbiente());
        consulta.setVersao(new BigDecimal(this.config.getVersao()));
        return consulta;
    }
}
