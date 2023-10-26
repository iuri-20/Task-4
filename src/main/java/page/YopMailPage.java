package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static wait.ExplicitWaitClass.*;

public class YopMailPage{


    private final WebDriver driver;
    private static final String BASE_URL = "https://yopmail.com/email-generator";
    private static final String  POPUP_IFRAME = "aswift_3";
    private static final String INBOX_LIST_IFRAME = "ifinbox";
    private static final String INBOX_CONTENT_IFRAME = "ifmail";

    @FindBy(xpath = "//a[@href='email-generator']")
    private WebElement emailGenerator;

    @FindBy(id = "cprnd")
    private WebElement emailCopy;

    @FindBy(id = "dismiss-button")
    private WebElement closeButton;

    @FindBy(xpath = "//button[span[contains(text(), 'Check Inbox')]]")
    private WebElement inbox;

    @FindBy(id = "refresh")
    private WebElement refreshButton;


    @FindBy(xpath = "//tr/td/h3[contains(text(), 'USD')]")
    private WebElement estimatedPrice;



    public YopMailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public YopMailPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public YopMailPage copyEmil() {
        waitForElement(driver, Duration.ofSeconds(15), emailCopy).click();
        return this;
    }

    public YopMailPage closePopUP() {
        WebElement iframe = waitFroElementPresence(driver, Duration.ofSeconds(15), By.id(POPUP_IFRAME));
        if (iframe.isDisplayed()) {
            driver.switchTo().frame(iframe);
            driver.switchTo().frame("ad_iframe");
            closeButton.click();
            driver.switchTo().defaultContent();
        }
        return this;
    }

    public YopMailPage checkInbox() {
        waitForElement(driver, Duration.ofSeconds(15), inbox).click();
        return this;
    }

    public YopMailPage refreshEmail() {
        WebElement iframe = waitFroElementPresence(driver, Duration.ofSeconds(15), By.id(INBOX_LIST_IFRAME));
        WebElement refresh = waitForElement(driver, Duration.ofSeconds(15), refreshButton);
        for (int i = 0; i < 10; ++i) {
            refresh.click();
            try {
                driver.switchTo().frame(iframe);
                waitForElementLocatedBy(driver, By.xpath("//div[contains(text(), 'Google Cloud Price Estimate')]"));
            } catch (TimeoutException ex) {
                driver.switchTo().defaultContent();
                continue;
            }
            break;
        }
        driver.switchTo().defaultContent();
        return this;

    }


    public String getPriceEstimate() {
        WebElement iframe = waitForElementLocatedBy(driver, By.id(INBOX_CONTENT_IFRAME));
        driver.switchTo().frame(iframe);
        return estimatedPrice.getText();
    }

}
