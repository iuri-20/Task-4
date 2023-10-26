package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static wait.ExplicitWaitClass.waitForElement;

public class GoogleCloudHomePage {

    private final WebDriver driver;
    private final String BASE_URL = "https://cloud.google.com/";

    @FindBy(name = "q")
    private WebElement searchButton;

    @FindBy(xpath = "//b[text()='Google Cloud Pricing Calculator']")
    private WebElement priceCalculatorLink;





    public GoogleCloudHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public GoogleCloudHomePage search(String input) {
        searchButton.click();
        searchButton.sendKeys(input);
        searchButton.sendKeys(Keys.RETURN);
        return this;
    }

    public CloudPricingCalculatorPage goToCalculator() {
        waitForElement(driver, Duration.ofSeconds(5), priceCalculatorLink).click();
        return new CloudPricingCalculatorPage(driver);
    }

}
