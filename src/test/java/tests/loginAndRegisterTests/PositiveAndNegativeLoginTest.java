package tests.loginAndRegisterTests;

import tests.TestBase;
import org.testng.annotations.Test;
import pages.loginPage.LoginPage;

import static org.testng.Assert.*;

public class PositiveAndNegativeLoginTest extends TestBase {

    @Test
    public void tryToLogInWithCorrectLoginAndPassword() {

        boolean isTitlesFieldDisplayed = new LoginPage()
                .correctLogin()
                .isTitlesFieldDisplayed();

        assertTrue(isTitlesFieldDisplayed);
    }

    @Test
    public void tryToLogInWithIncorrectLoginAndPassword() {

        String message = new LoginPage()
                .tryToLogInWithIncorrectData();

        assertEquals(message, "Login failed");
    }
}
