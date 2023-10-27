package page;

import model.PriceData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static wait.ExplicitWaitClass.*;

public class CloudPricingCalculatorPage {


    private final WebDriver driver;
    private static final String IFRAME_LOCATOR = "//article[@id='cloud-site']/devsite-iframe/iframe";
    private static final String SERIES_XPATH = "//md-option[@value= '%s']";
    private static final String MACHINE_TYPE_XPATH = "//md-option//div[contains(text(), '%s')]";
    private static final String GPU_TYPE_XPATH = "//div[contains(text(), '%s')]";
    private static final String GPU_NUMBER_XPATH = "//div[contains(@class,'md-clickable')]//div[contains(text(), '%s')]";
    private static final String LOCAL_SSD_XPATH = "//div[@class='md-text ng-binding' and contains(text(), '%s')]";
    private static final String DATACENTER_LOCATION_XPATH = "//div[contains(@class,'md-clickable')]//div[contains(text(), '%s')]";
    private static final String COMMIT_USAGE_XPATH = "//div[contains(@class,'md-clickable')]//div[contains(text(), '%s')]";


    @FindBy(xpath = "//md-tab-item//div[@class='tab-holder compute']")
    private WebElement computeEngine;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity' and @name='quantity']")
    private WebElement instances;

    @FindBy(xpath = "//md-select-value[.//div[normalize-space(.)='E2']]")
    private WebElement seriesOptions;

    @FindBy(xpath = "//md-select-value[.//div[normalize-space(.)='n1-standard-1 (vCPUs: 1, RAM: 3.75GB)']]")
    private WebElement machineTypeOptions;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//md-checkbox[@aria-label='Add GPUs']")
    private WebElement checkboxGPU;

    @FindBy(xpath = "//md-select[@aria-label='GPU type']")
    private WebElement typeGPU;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUs;

    @FindBy(id = "select_value_label_464")
    private WebElement localSSDDropdown;

    @FindBy(id = "select_132")
    private WebElement dataCenterLocation;

    @FindBy(xpath = "//md-select-value[contains(.//div, 'None')]")
    private WebElement committedUsage;

    @FindBy(id = "select_option_137")
    private WebElement commitUsageValue;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(text(), 'Add to Estimate')]")
    private WebElement addToEstimate;

    @FindBy(xpath = "//b[contains(text(),'Total Estimated Cost:') and contains(text(),'USD') and contains(text(),'per 1 month')]")
    private WebElement estimatedPrice;

    @FindBy(xpath = "//div[@ng-if='item.items.priceFuture']//b[contains(text(), 'USD')]")
    private WebElement estimatedPrice2;

    @FindBy(id="Email Estimate")
    private WebElement emailEstimate;

    @FindBy(id = "input_616")
    private WebElement emailInput;

    @FindBy(xpath = "//button[contains(text(), 'Send Email')]")
    private WebElement sendEmail;



    public CloudPricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CloudPricingCalculatorPage getToCalculator() {
        WebElement iframe = waitFroElementPresence(driver, Duration.ofSeconds(15), By.xpath(IFRAME_LOCATOR));
        driver.switchTo().frame(iframe);
        driver.switchTo().frame("myFrame");
        return this;
    }


    public CloudPricingCalculatorPage setNumberOfInstances(PriceData data) {
        computeEngine.click();
        instances.sendKeys(data.getInstances());
        return this;
    }

    public CloudPricingCalculatorPage setSeriesValue(PriceData data) {
        seriesOptions.click();
        waitForElementLocatedBy(driver, By.xpath(String.format(SERIES_XPATH,  data.getSeries()))).click();
        return this;
    }


    public CloudPricingCalculatorPage setMachineTypeValue(PriceData data) {
        machineTypeOptions.click();
        waitForElementLocatedBy(driver, By.xpath(String.format(MACHINE_TYPE_XPATH, data.getMachineType()))).click();
        return this;
    }

    public CloudPricingCalculatorPage addGpu() {
        checkboxGPU.click();
        return this;
    }

    public CloudPricingCalculatorPage setGPUType(PriceData data) {
        typeGPU.click();
        waitForElementLocatedBy(driver, By.xpath(String.format(GPU_TYPE_XPATH, data.getTypeOfGPU()))).click();
        return this;
    }

    public CloudPricingCalculatorPage setNumberOfGPUsValue(PriceData data) {
        numberOfGPUs.click();
        waitForElementLocatedBy(driver, By.xpath(String.format(GPU_NUMBER_XPATH, data.getNumberOfGPUs()))).click();
        return this;
    }


    public CloudPricingCalculatorPage setLocalSSDD(PriceData data) {
        localSSDDropdown.click();
        waitForElementLocatedBy(driver, By.xpath(String.format(LOCAL_SSD_XPATH, data.getLocalSSD()))).click();
        return this;
    }

    public CloudPricingCalculatorPage setDataCenterLocation(PriceData data) {
        dataCenterLocation.click();
        waitForElementLocatedBy(driver, By.xpath(String.format(DATACENTER_LOCATION_XPATH, data.getDatacenterLocation()))).click();
        return this;
    }

    public CloudPricingCalculatorPage setCommittedUsageValue(PriceData data) {
        committedUsage.click();
        waitForElementLocatedBy(driver, By.xpath(String.format(COMMIT_USAGE_XPATH, data.getCommittedUsage()))).click();
        return this;
    }


    public CloudPricingCalculatorPage clickAddToEstimate() {
        addToEstimate.click();
        return this;
    }

    public boolean isPriceEstimated() {
        return estimatedPrice.isDisplayed();
    }

    public String getEstimatedPrice() {
        return estimatedPrice2.getText();
    }

    public void clickEmailEstimate() {
        emailEstimate.click();
    }

    public void fillInEmailInput() {
        emailInput.click();
        emailInput.sendKeys(Keys.CONTROL, "v");
    }

    public void sendEstimateToEmail() {
        sendEmail.click();
    }

}
