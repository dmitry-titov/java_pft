package ru.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void myIpTest() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("85.234.51.53");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

    @Test
    public void invalidIpTest() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("85.234.51.xxx");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
