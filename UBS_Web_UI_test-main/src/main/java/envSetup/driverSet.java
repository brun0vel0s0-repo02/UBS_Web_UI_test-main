package envSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class driverSet {
    public static WebDriver driver;
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\heroUk\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void closeCurrent(){
        driver.close();
    }

    public void tearDown(){
        driver.quit();
    }
}
