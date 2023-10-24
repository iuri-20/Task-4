package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.CloudPricingCalculatorPage;
import page.GoogleCloudHomePage;
import page.YopMailPage;

import static util.WindowSwitchHandler.getNewWindowAndSwitch;


public class GoogleCloudTest {

    private WebDriver driver;

    private GoogleCloudHomePage homePageObj;
    private CloudPricingCalculatorPage calculatorObj;

    private YopMailPage emailObj;


    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        homePageObj = new GoogleCloudHomePage(driver);
        calculatorObj = new CloudPricingCalculatorPage(driver);
        emailObj = new YopMailPage(driver);
        driver.manage().window().maximize();
        homePageObj.openPage("https://cloud.google.com/");
    }

    @Test(priority = 1)
    public void testSearchCalculator() {
        homePageObj.search("Google Cloud Platform Pricing Calculator");
        homePageObj.goToCalculator();
    }

    @Test(priority = 2)
    public void testCalculatePrice() {
        calculatorObj.getToCalculator();
        calculatorObj.setNumberOfInstances("4");
        calculatorObj.setSeriesValue();
        calculatorObj.setMachineType();
        calculatorObj.addGpu();
        calculatorObj.setGPUType();
        calculatorObj.setNumberOfGPUs();
        calculatorObj.setLocalSSDD();
        calculatorObj.setDataCenterLocation();
        calculatorObj.setCommittedUsage();
        calculatorObj.clickAddToEstimate();

        SoftAssert softAssert = new SoftAssert();
        boolean priceCheck = calculatorObj.isPriceEstimated();
        String calculatorEstimatedPrice = calculatorObj.getEstimatedPrice();

        softAssert.assertTrue(priceCheck, "Element to the right of Calculator not found.");

        String tab1 = driver.getWindowHandle();
        String tab2 = getNewWindowAndSwitch(driver);

        emailObj.openPage();
        emailObj.copyEmil();

        driver.switchTo().window(tab1);
        calculatorObj.getToCalculator();
        calculatorObj.clickEmailEstimate();
        calculatorObj.fillInEmailInput();
        calculatorObj.sendEstimateToEmail();

        driver.switchTo().window(tab2);
        emailObj.closePopUP();
        emailObj.checkInbox();
        emailObj.refreshEmail();
        String emailEstimatedPrice = emailObj.getPriceEstimate();

        softAssert.assertEquals(calculatorEstimatedPrice, emailEstimatedPrice);
        softAssert.assertAll();
    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
