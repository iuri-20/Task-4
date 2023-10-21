package page;

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


    @FindBy(xpath = "//md-tab-item//div[@class='tab-holder compute']")
    private WebElement computeEngine;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity' and @name='quantity']")
    private WebElement instances;

    @FindBy(xpath = "//md-select-value[.//div[normalize-space(.)='E2']]")
    private WebElement seriesValue;

    @FindBy(xpath = "//md-select-value[.//div[normalize-space(.)='n1-standard-1 (vCPUs: 1, RAM: 3.75GB)']]")
    private WebElement machineType;

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

    @FindBy(id = "select_option_264")
    private WebElement locationValue;

    @FindBy(xpath = "//md-select-value[contains(.//div, 'None')]")
    private WebElement committedUsage;

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

    public void getToCalculator() {
        WebElement iframe = waitFroElementPresence(driver, Duration.ofSeconds(15), By.xpath("//article[@id='cloud-site']/devsite-iframe/iframe"));
        driver.switchTo().frame(iframe);
        driver.switchTo().frame("myFrame");
    }


    public void setNumberOfInstances(String num) {
        computeEngine.click();
        instances.sendKeys(num);
    }

    public void setSeriesValue() {
        seriesValue.click();
        WebElement element = waitForElementLocatedBy(driver, Duration.ofSeconds(15), By.xpath("//md-option[@value='n1' and normalize-space(.)='N1']"));
        element.click();
    }

    public void setMachineType() {
        machineType.click();
        WebElement element = waitForElementLocatedBy(driver, Duration.ofSeconds(15), By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8' and normalize-space(.//div)='n1-standard-8 (vCPUs: 8, RAM: 30GB)']"));
        element.click();
//        return this;
    }

    public void addGpu() {
        checkboxGPU.click();
        typeGPU.click();
        waitForElementLocatedBy(driver, Duration.ofSeconds(15), By.xpath("//md-option[@value='NVIDIA_TESLA_V100' and normalize-space(.//div)='NVIDIA Tesla V100']")).click();

        numberOfGPUs.click();
        waitForElementLocatedBy(driver, Duration.ofSeconds(15), By.xpath("//md-option[@value='1' and normalize-space(.//div)='1']")).click();

    }

    public void setLocalSSDD() {
        localSSDDropdown.click();
        waitForElementLocatedBy(driver, Duration.ofSeconds(15), By.id("select_option_491")).click();
    }

    public void setDataCenterLocation() {
        dataCenterLocation.click();
        waitForElement(driver, Duration.ofSeconds(15), locationValue).click();

    }

    public void setCommittedUsage() {
        committedUsage.click();
        waitForElementLocatedBy(driver, Duration.ofSeconds(5), By.id("select_option_137")).click();
    }


    public void clickAddToEstimate() {
        addToEstimate.click();
    }

    public boolean isPriceEstimated() {
        return estimatedPrice.isDisplayed();
    }

    public String getEstimatedPrice() {
        return estimatedPrice2.getText();
    }

    public void sendEstimateToEmail() {

        emailEstimate.click();
        emailInput.click();
        emailInput.sendKeys(Keys.CONTROL, "v");
        sendEmail.click();

    }


}
