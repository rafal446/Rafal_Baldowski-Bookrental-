package tests;

import configuration.ConfigurationProperties;
import configuration.PropertiesLoader;
import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Properties;

public class TestBase {

    protected WebDriver driver;
    @Step("Loading configuration from configuration.properties including browser type")
    @BeforeClass
    public void beforeClass() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties properties = propertiesLoader.getPropertiesFromFile("configuration.properties");
        ConfigurationProperties.setProperties(properties);
    }
    @Step("Getting WebDriver and navigate to \"https://ta-bookrental-fe.onrender.com\"")
    @BeforeMethod
    public void goToLoginPage() {
        driver = DriverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.navigate().to("https://ta-bookrental-fe.onrender.com");
    }
    @Step("Tear down WebDriver")
    @AfterMethod
    public void afterTest() {
        DriverManager.disposeDriver();
    }
}
