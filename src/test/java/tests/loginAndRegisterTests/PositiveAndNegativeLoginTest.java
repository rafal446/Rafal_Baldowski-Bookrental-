package tests.loginAndRegisterTests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import tests.TestBase;
import org.testng.annotations.Test;
import pages.loginPage.LoginPage;

import static org.testng.Assert.*;

public class PositiveAndNegativeLoginTest extends TestBase {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("The goal of this test is to log in with correct data and check after that the list of titles is displayed")
    public void tryToLogInWithCorrectLoginAndPassword() {

        boolean isTitlesFieldDisplayed = new LoginPage()
                .correctLogin()
                .isTitlesFieldDisplayed();

        assertTrue(isTitlesFieldDisplayed);
    }

    @Test
    @Description("The goal of this test is login attempt with incorrect data and check if message is displayed after")
    public void tryToLogInWithIncorrectLoginAndPassword() {

        String message = new LoginPage()
                .tryToLogInWithIncorrectData();

        assertEquals(message, "Login failed");
    }
}
