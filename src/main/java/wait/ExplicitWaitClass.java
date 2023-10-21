package wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaitClass {

    public static WebElement waitForElementLocatedBy(WebDriver driver, Duration timeout, By by) {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement waitFroElementPresence(WebDriver driver, Duration timeout, By by) {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement waitForElement(WebDriver driver, Duration timeout,WebElement element) {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

}
