package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class WindowSwitchHandler {

    public static String getNewWindowAndSwitch(WebDriver driver) {
        driver.switchTo().newWindow(WindowType.TAB);
        return driver.getWindowHandle();
    }

}
