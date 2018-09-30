package maria.belyaeva.qa.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;
import com.lavasoft.GetIpLocation;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GeopIpServiceTests {

    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("92.124.131.219");
        assertTrue(ipLocation.contains("RU"));
    }
}

