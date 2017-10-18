
package com.fincatto.documentofiscal.nfe400.webservices.stubs.autorizacao;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * Serviço destinado à recepção de mensagens de lote de NF-e.
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "NFeAutorizacao4", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NFeAutorizacao4", wsdlLocation = "file:/home/walter/Softland/wsdl-nfe/SP/nfeautorizacao4.wsdl")
public class NFeAutorizacao4
    extends Service
{

    private final static URL NFEAUTORIZACAO4_WSDL_LOCATION;
    private final static WebServiceException NFEAUTORIZACAO4_EXCEPTION;
    private final static QName NFEAUTORIZACAO4_QNAME = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NFeAutorizacao4", "NFeAutorizacao4");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/home/walter/Softland/wsdl-nfe/SP/nfeautorizacao4.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        NFEAUTORIZACAO4_WSDL_LOCATION = url;
        NFEAUTORIZACAO4_EXCEPTION = e;
    }

    public NFeAutorizacao4() {
        super(__getWsdlLocation(), NFEAUTORIZACAO4_QNAME);
    }

    public NFeAutorizacao4(WebServiceFeature... features) {
        super(__getWsdlLocation(), NFEAUTORIZACAO4_QNAME, features);
    }

    public NFeAutorizacao4(URL wsdlLocation) {
        super(wsdlLocation, NFEAUTORIZACAO4_QNAME);
    }

    public NFeAutorizacao4(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, NFEAUTORIZACAO4_QNAME, features);
    }

    public NFeAutorizacao4(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NFeAutorizacao4(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns NFeAutorizacao4Soap12
     */
    @WebEndpoint(name = "NFeAutorizacao4Soap12")
    public NFeAutorizacao4Soap12 getNFeAutorizacao4Soap12() {
        return super.getPort(new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NFeAutorizacao4", "NFeAutorizacao4Soap12"), NFeAutorizacao4Soap12.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NFeAutorizacao4Soap12
     */
    @WebEndpoint(name = "NFeAutorizacao4Soap12")
    public NFeAutorizacao4Soap12 getNFeAutorizacao4Soap12(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NFeAutorizacao4", "NFeAutorizacao4Soap12"), NFeAutorizacao4Soap12.class, features);
    }

    private static URL __getWsdlLocation() {
        if (NFEAUTORIZACAO4_EXCEPTION!= null) {
            throw NFEAUTORIZACAO4_EXCEPTION;
        }
        return NFEAUTORIZACAO4_WSDL_LOCATION;
    }

}