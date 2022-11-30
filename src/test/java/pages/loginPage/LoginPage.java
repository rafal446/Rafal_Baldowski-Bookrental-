package pages.loginPage;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import driver.DriverManager;
import driver.WaitForElement;
import pages.registerPage.RegisterPage;
import pages.mainPage.MainPage;

public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }
    @FindBy(xpath = "//input[@id=\"login\"]")
    WebElement userNameInput;

    @FindBy(xpath = "//input[@id=\"password\"]")
    WebElement passwordInput;

    @FindBy(xpath = "//button[@id=\"login-btn\"]")
    WebElement logInButton;

    @FindBy(xpath = "//p[@class=\"alert__content\"]")
    WebElement warningLabel;
    @FindBy(xpath = "//button[@id=\"register-btn\"]")
    WebElement signUpButton;
    @Step("Type into user name input {username}")
    public void typeIntoUserNameInput(String username) {
        userNameInput.sendKeys(username);
    }
    @Step("Type into password input {password}")
    public void typeIntoPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }
    @Step("Click on login button")
    public void clickOnLogInButton() {
        logInButton.click();
    }
    @Step("Getting warning message")
    public String getWarningMessage() {
        WaitForElement.waitUntilElementIsVisible(warningLabel);
        return warningLabel.getText();
    }
    @Step("Login to page")
    public MainPage correctLogin() {
        WaitForElement.waitUntilElementIsVisible(userNameInput);
        typeIntoPasswordInput("testPassword");
        typeIntoUserNameInput("testUser");
        clickOnLogInButton();
        WaitForElement.waitUntilInvisibilityOfElement(logInButton);
        return new MainPage();
    }
    @Step("Attempt to login with incorrect data")
    public String tryToLogInWithIncorrectData() {
        typeIntoUserNameInput("wrongUser");
        typeIntoPasswordInput("wrongPassword");
        clickOnLogInButton();
        String message = getWarningMessage();
        return message;
    }
    @Step("Click on sing up button")
    public RegisterPage clickSignUpButton() {
        signUpButton.click();
        return new RegisterPage();
    }
}
