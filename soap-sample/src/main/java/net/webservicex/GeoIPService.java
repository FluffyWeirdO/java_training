package net.webservicex;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2016-07-22T23:22:15.523+03:00
 * Generated source version: 3.1.6
 */
@WebServiceClient(name = "GeoIPService",
        wsdlLocation = "file:src/main/resources/geoipservice.wsdl",
        targetNamespace = "http://www.webservicex.net/")
public class GeoIPService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.webservicex.net/", "GeoIPService");
    public final static QName GeoIPServiceSoap = new QName("http://www.webservicex.net/", "GeoIPServiceSoap");
    public final static QName GeoIPServiceSoap12 = new QName("http://www.webservicex.net/", "GeoIPServiceSoap12");
    public final static QName GeoIPServiceHttpGet = new QName("http://www.webservicex.net/", "GeoIPServiceHttpGet");
    public final static QName GeoIPServiceHttpPost = new QName("http://www.webservicex.net/", "GeoIPServiceHttpPost");

    static {
        URL url = null;
        try {
            url = new URL("file:src/main/resources/geoipservice.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(GeoIPService.class.getName())
                    .log(java.util.logging.Level.INFO,
                            "Can not initialize the default wsdl from {0}", "file:src/main/resources/geoipservice.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public GeoIPService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public GeoIPService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GeoIPService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public GeoIPService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public GeoIPService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public GeoIPService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }


    /**
     * @return returns GeoIPServiceSoap
     */
    @WebEndpoint(name = "GeoIPServiceSoap")
    public GeoIPServiceSoap getGeoIPServiceSoap() {
        return super.getPort(GeoIPServiceSoap, GeoIPServiceSoap.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns GeoIPServiceSoap
     */
    @WebEndpoint(name = "GeoIPServiceSoap")
    public GeoIPServiceSoap getGeoIPServiceSoap(WebServiceFeature... features) {
        return super.getPort(GeoIPServiceSoap, GeoIPServiceSoap.class, features);
    }


    /**
     * @return returns GeoIPServiceSoap
     */
    @WebEndpoint(name = "GeoIPServiceSoap12")
    public GeoIPServiceSoap getGeoIPServiceSoap12() {
        return super.getPort(GeoIPServiceSoap12, GeoIPServiceSoap.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns GeoIPServiceSoap
     */
    @WebEndpoint(name = "GeoIPServiceSoap12")
    public GeoIPServiceSoap getGeoIPServiceSoap12(WebServiceFeature... features) {
        return super.getPort(GeoIPServiceSoap12, GeoIPServiceSoap.class, features);
    }


    /**
     * @return returns GeoIPServiceHttpGet
     */
    @WebEndpoint(name = "GeoIPServiceHttpGet")
    public GeoIPServiceHttpGet getGeoIPServiceHttpGet() {
        return super.getPort(GeoIPServiceHttpGet, GeoIPServiceHttpGet.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns GeoIPServiceHttpGet
     */
    @WebEndpoint(name = "GeoIPServiceHttpGet")
    public GeoIPServiceHttpGet getGeoIPServiceHttpGet(WebServiceFeature... features) {
        return super.getPort(GeoIPServiceHttpGet, GeoIPServiceHttpGet.class, features);
    }


    /**
     * @return returns GeoIPServiceHttpPost
     */
    @WebEndpoint(name = "GeoIPServiceHttpPost")
    public GeoIPServiceHttpPost getGeoIPServiceHttpPost() {
        return super.getPort(GeoIPServiceHttpPost, GeoIPServiceHttpPost.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns GeoIPServiceHttpPost
     */
    @WebEndpoint(name = "GeoIPServiceHttpPost")
    public GeoIPServiceHttpPost getGeoIPServiceHttpPost(WebServiceFeature... features) {
        return super.getPort(GeoIPServiceHttpPost, GeoIPServiceHttpPost.class, features);
    }

}
