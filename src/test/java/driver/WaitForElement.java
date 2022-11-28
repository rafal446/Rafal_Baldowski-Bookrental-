package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitForElement {

    private static WebDriverWait webDriverWait() {
        WebDriver driver = DriverManager.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait;
    }
    public static void waitUntilElementIsVisible(WebElement element) {
        WebDriverWait wait = webDriverWait();
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void waitUntilElementIsClickable(WebElement element) {
        WebDriverWait wait = webDriverWait();
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void waitUntilInvisibilityOfElement(WebElement element) {
        WebDriverWait wait = webDriverWait();
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public static void waitURL() {
        WebDriverWait wait = webDriverWait();
        wait.until(ExpectedConditions.urlContains("register"));
    }
}
