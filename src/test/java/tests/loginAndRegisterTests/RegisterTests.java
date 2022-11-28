//package tests.loginAndRegisterTests;
//
//import pages.mainPage.MainPage;
//import pages.registerPage.RegisterPage;
//import tests.TestBase;
//import org.testng.annotations.Test;
//import pages.loginPage.LoginPage;
//
//import static org.testng.Assert.assertEquals;
//
//public class RegisterTests extends TestBase {
//
//    @Test
//    public void shouldAddNewUserWhenDataIsCorrect() {
//
//         new LoginPage().clickSignUpButton();
//         RegisterPage registerPage = new RegisterPage();
//         String message = registerPage.registerWithCorrectData();
//
//         assertEquals(message, "You have been successfully registered!");
//    }
//
//    @Test
//    public void shouldNotAddNewUserWhenUserAlreadyExist() {
//
//        new LoginPage().clickSignUpButton();
//        RegisterPage registerPage = new RegisterPage();
//        String message = registerPage.registerUserThatAlreadyExists();
//
//        assertEquals(message, "This user already exist!");
//    }
//
//    @Test
//    public void shouldNotAddNewUserWhenPasswordDoesntMatch() {
//
//        new LoginPage().clickSignUpButton();
//        RegisterPage registerPage = new RegisterPage();
//        String message = registerPage.registerUserWhenPasswordDoesNotMatch();
//
//        assertEquals(message, "The passwords don't match");
//    }
//}
