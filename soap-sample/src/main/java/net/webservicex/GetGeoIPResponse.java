
package net.webservicex;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GetGeoIPResult" type="{http://www.webservicex.net/}GeoIP" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getGeoIPResult"
})
@XmlRootElement(name = "GetGeoIPResponse")
public class GetGeoIPResponse {

    @XmlElement(name = "GetGeoIPResult")
    protected GeoIP getGeoIPResult;

    /**
     * Gets the value of the getGeoIPResult property.
     *
     * @return possible object is
     * {@link GeoIP }
     */
    public GeoIP getGetGeoIPResult() {
        return getGeoIPResult;
    }

    /**
     * Sets the value of the getGeoIPResult property.
     *
     * @param value allowed object is
     *              {@link GeoIP }
     */
    public void setGetGeoIPResult(GeoIP value) {
        this.getGeoIPResult = value;
    }

}
