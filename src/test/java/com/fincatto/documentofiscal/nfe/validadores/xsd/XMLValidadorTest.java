package com.fincatto.documentofiscal.nfe.validadores.xsd;

import com.fincatto.documentofiscal.nfe.assinatura.AssinaturaDigital;
import com.fincatto.documentofiscal.nfe.validadores.xsd.XMLValidador;
import com.fincatto.documentofiscal.nfe.FabricaDeObjetosFake;
import com.fincatto.documentofiscal.nfe.NFeConfigFake;
import org.junit.Assert;
import org.junit.Test;

public class XMLValidadorTest {

    @Test
    public void deveValidarLote() throws Exception {
        final String loteXml = FabricaDeObjetosFake.getNFLoteEnvio().toString();
        final String loteXmlAssinado = new AssinaturaDigital(new NFeConfigFake()).assinarDocumento(loteXml);
        Assert.assertTrue(XMLValidador.validaLote4(loteXmlAssinado));
    }

    @Test
    public void deveValidarNota() throws Exception {
        final String notaXml = FabricaDeObjetosFake.getNFNota().toString();
        final String notaXmlAssinada = new AssinaturaDigital(new NFeConfigFake()).assinarDocumento(notaXml);
        Assert.assertTrue(XMLValidador.validaNota4(notaXmlAssinada));
    }
}