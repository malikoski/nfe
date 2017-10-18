package com.fincatto.documentofiscal.nfe.validadores.xsd;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

public final class XMLValidador {

    private static boolean valida(final String xml, final String xsd, String versaoNfe) throws IOException, SAXException,
            URISyntaxException {

        String pathFilesValidation = "schemas/PL_008i2/%s";

        if (versaoNfe.equals("v400")) {
            pathFilesValidation = "schemas/PL_009_V4/%s";
        }

        final URL xsdPath = XMLValidador.class.getClassLoader().getResource(String.format(pathFilesValidation, xsd));
        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new StreamSource(xsdPath.toURI().toString()));
        
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
        
        return true;
    }

    public static boolean validaLote(final String arquivoXML) throws Exception {
        return XMLValidador.valida(arquivoXML, "enviNFe_v3.10.xsd", "v310");
    }

    public static boolean validaNota(final String arquivoXML) throws Exception {
        return XMLValidador.valida(arquivoXML, "nfe_v3.10.xsd", "v310");
    }

    public static boolean validaLote4(final String arquivoXML) throws Exception {
        return XMLValidador.valida(arquivoXML, "enviNFe_v4.00.xsd", "v400");
    }

    public static boolean validaNota4(final String arquivoXML) throws Exception {
        return XMLValidador.valida(arquivoXML, "nfe_v4.00.xsd", "v400");
    }

    private static boolean validaMDF(final String xml, final String xsd) throws IOException, SAXException,
            URISyntaxException {
        final URL xsdPath = XMLValidador.class.getClassLoader()
                .getResource(String.format("schemas/PL_MDFe_300_NT022017/%s", xsd));
        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new StreamSource(xsdPath.toURI().toString()));
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
        return true;
    }

    public static boolean validaLoteMDFe(final String arquivoXML) throws Exception {
        return XMLValidador.validaMDF(arquivoXML, "enviMDFe_v3.00.xsd");
    }

    public static boolean validaMDFe(final String arquivoXML) throws Exception {
        return XMLValidador.validaMDF(arquivoXML, "mdfe_v3.00.xsd");
    }

}
