package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap().getGeoIP("93.183.246.254");
        assertEquals(geoIP.getCountryCode(), "UKR");
    }

    @Test
    public void testInvalidIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap().getGeoIP("93.183.246.xxx");
        assertEquals(geoIP.getCountryCode(), "UKR");
    }

}
