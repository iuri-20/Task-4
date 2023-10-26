package test;

import model.PriceData;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.CloudPricingCalculatorPage;
import page.GoogleCloudHomePage;
import page.YopMailPage;
import services.PriceCalculatorCreator;

import static util.WindowSwitchHandler.getNewWindowAndSwitch;


public class GoogleCloudTest extends CommonConditions{

    @Test()
    public void testCalculatePrice() {
        PriceData calculatorForm = PriceCalculatorCreator.withCredentialsFromProperty();
        CloudPricingCalculatorPage page2 = new GoogleCloudHomePage(driver)
                .openPage()
                .search("Google Cloud Platform Pricing Calculator")
                .goToCalculator()
                .getToCalculator()
                .setNumberOfInstances(calculatorForm)
                .setSeriesValue(calculatorForm)
                .setMachineTypeValue(calculatorForm)
                .addGpu()
                .setGPUType(calculatorForm)
                .setNumberOfGPUsValue(calculatorForm)
                .setLocalSSDD(calculatorForm)
                .setDataCenterLocation(calculatorForm)
                .setCommittedUsageValue(calculatorForm)
                .clickAddToEstimate();

        SoftAssert softAssert = new SoftAssert();
        boolean priceCheck = page2.isPriceEstimated();
        String calculatorEstimatedPrice = page2.getEstimatedPrice();

        softAssert.assertTrue(priceCheck, "Element to the right of Calculator not found.");

        String tab1 = driver.getWindowHandle();
        String tab2 = getNewWindowAndSwitch(driver);

        YopMailPage emailPage = new YopMailPage(driver)
                .openPage()
                .copyEmil();

        driver.switchTo().window(tab1);
        page2.getToCalculator();
        page2.clickEmailEstimate();
        page2.fillInEmailInput();
        page2.sendEstimateToEmail();

        driver.switchTo().window(tab2);
        emailPage.closePopUP();
        emailPage.checkInbox();
        emailPage.refreshEmail();
        String emailEstimatedPrice = emailPage.getPriceEstimate();

        softAssert.assertEquals(calculatorEstimatedPrice, emailEstimatedPrice);
        softAssert.assertAll();
    }

}
