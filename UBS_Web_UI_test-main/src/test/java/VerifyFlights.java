import Pages.landPage;

import envSetup.driverSet;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class VerifyFlights {
    driverSet driverIn = new driverSet();
    @BeforeClass
    public void start(){
        driverIn.setUp();
    }

    @Test(priority=1)
    public void verifyRoundTrip() throws InterruptedException {
        landPage land = new landPage();
        land.navURL();
       // Assert.assertEquals(land.chkRoundTrp(),"Round-trip");
    }

    @Test(priority=2,dependsOnMethods = {"verifyRoundTrip"})
    public void verifyFlights() throws InterruptedException, IOException, ParseException {
        landPage land = new landPage();
        land.enterFromTo();
        land.setDates();
    }
    @Test(priority = 3,dependsOnMethods = {"verifyFlights"})
    public void verifyMaxPriceSet() throws InterruptedException {
        landPage land = new landPage();
        Assert.assertEquals(land.setMaxP(),"Maximum price");
    }

    @AfterClass
    public void tearDown(){
        driverIn.tearDown();
    }
}

