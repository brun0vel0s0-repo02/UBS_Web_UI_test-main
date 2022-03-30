package Pages;
import commonUtils.jsonReader;
import envSetup.driverSet;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class landPage {

    String navUrl = "https://www.kayak.com/flights/";

    By selTrp = By.xpath("(//div[@class='zcIg']/div/div)[1]");
    By selRoundTrp = By.xpath("//li[@aria-label='Round-trip']");
    By destEnter = By.xpath("//div[@class='zEiP-formField zEiP-origin']//div[@aria-label='Flight origin input']");
    By fromDestClose = By.xpath("(//div[@class='vvTc-item-button'])[1]");
    By fromDest = By.xpath("//input[@placeholder='From?']");
    By Dest1Ele = By.xpath("//div[@class='JyN0 JyN0-pres-horizon']/ul/li[1]");

    By toEnter = By.xpath("//div[@class='zEiP-formField zEiP-destination']//div[@aria-label='Flight destination input']");
    By toDest = By.xpath("//input[@placeholder='To?']");

    By Search = By.xpath("(//button[@aria-label='Search'])[1]");
    By departDate = By.xpath("(//div[@aria-label='Departure date input'])[1]");
    By SearchWDates = By.xpath("//div[@title='Search flights']");
    By resetFilt = By.xpath("//div[@class='filterSectionTitle']/*[contains(text(),'Price')]/../a");
    By maxPrice = By.xpath("//div[@aria-label='Maximum price']");




    WebDriver driver = driverSet.driver;
    jsonReader read = new jsonReader();

    public void navURL() {
        driver.get(navUrl);
        driver.manage().window().maximize();
    }

    public String chkRoundTrp() {
        String actual=null;
        actual = driver.findElement(selTrp).getText();
        if(actual!="Round-trip"){
            driver.findElement(selTrp).click();
            driver.findElement(selRoundTrp).click();
        }else{
            actual = driver.findElement(selTrp).getText();
        }
        return actual;
    }

    public void enterFromTo() throws InterruptedException, IOException, ParseException {
        Thread.sleep(5000);
        driver.findElement(destEnter).click();
        Thread.sleep(5000);

        //From
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(fromDestClose));
        driver.findElement(fromDest).sendKeys(read.readData("fromCity"));
        Thread.sleep(7000);
        driver.findElement(Dest1Ele).click();


        //To
        Thread.sleep(5000);
        driver.findElement(toEnter).click();
        Thread.sleep(5000);
        driver.findElement(toDest).sendKeys(read.readData("toCity"));
        Thread.sleep(5000);
        driver.findElement(Dest1Ele).click();

        Thread.sleep(5000);
        driver.findElement(Search).click();


    }

    public void setDates() throws InterruptedException, IOException, ParseException {
        //Dates
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(departDate));

        Thread.sleep(10000);

        Actions at = new Actions(driver);
        at.sendKeys(read.readData("startDate"));
        at.sendKeys(Keys.TAB);
        Thread.sleep(5000);
        at.sendKeys(read.readData("endDate"));
        at.build().perform();
        driver.findElement(SearchWDates).click();
    }

    public String setMaxP() throws InterruptedException {

            String actual = null;
            if (driver.findElement(resetFilt).isDisplayed()) {
                Thread.sleep(5000);
                driver.findElement(resetFilt).click();
            } else {
                Thread.sleep(5000);
                actual = driver.findElement(maxPrice).getAttribute("aria-label");

                Thread.sleep(5000);
                
            }
            return actual;
        }
    }

