package test;

import model.PriceData;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.CloudPricingCalculatorPage;
import page.GoogleCloudHomePage;
import services.PriceCalculatorCreator;

public class CalculatorSmokeTest extends CommonConditions{

    @Test
    public void calculatorTest() {
        PriceData calculatorForm = PriceCalculatorCreator.withCredentialsFromProperty();
        CloudPricingCalculatorPage page = new GoogleCloudHomePage(driver)
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
        boolean priceCheck = page.isPriceEstimated();
        softAssert.assertTrue(priceCheck, "Element to the right of Calculator not found.");
    }

}
