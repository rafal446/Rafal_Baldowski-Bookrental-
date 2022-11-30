package tests.loginAndRegisterTests;


import jdk.jfr.Description;
import tests.TestBase;
import org.testng.annotations.Test;
import pages.loginPage.LoginPage;

import static org.testng.Assert.assertEquals;

public class RegisterTests extends TestBase {

    @Test
    @Description("The goal of this test is register new user and check the message is displayed after")
    public void shouldAddNewUserWhenDataIsCorrect() {

         String message = new LoginPage()
                            .clickSignUpButton()
                            .registerWithCorrectData();

         assertEquals(message, "You have been successfully registered!");
    }

    @Test
    @Description("The goal of this test is attempt of register user when the same user almost exist" +
            " and check the message is displayed after")
    public void shouldNotAddNewUserWhenUserAlreadyExist() {

        String message = new LoginPage()
                .clickSignUpButton()
                .registerUserThatAlreadyExists();

        assertEquals(message, "This user already exist!");
    }

    @Test
    @Description("The goal of this test is register new user using different word in input password and repeat password" +
            " and check the message is displayed after")
    public void shouldNotAddNewUserWhenPasswordDoesntMatch() {

        String message = new LoginPage()
                .clickSignUpButton()
                .registerUserWhenPasswordDoesNotMatch();

        assertEquals(message, "The passwords don't match");
    }
}
