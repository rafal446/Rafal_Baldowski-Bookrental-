package pages.loginPage;

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

    public void typeIntoUserNameInput(String username) {
        userNameInput.sendKeys(username);
    }
    public void typeIntoPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }
    public void clickOnLogInButton() {
        logInButton.click();
    }
    public String getWarningMessage() {
        WaitForElement.waitUntilElementIsVisible(warningLabel);
        return warningLabel.getText();
    }
    public MainPage correctLogin() {
        WaitForElement.waitUntilElementIsVisible(userNameInput);
        typeIntoPasswordInput("testPassword");
        typeIntoUserNameInput("testUser");
        clickOnLogInButton();
        WaitForElement.waitUntilInvisibilityOfElement(logInButton);
        return new MainPage();
    }
    public String tryToLogInWithIncorrectData() {
        typeIntoUserNameInput("wrongUser");
        typeIntoPasswordInput("wrongPassword");
        clickOnLogInButton();
        String message = getWarningMessage();
        return message;
    }
    public void clickSignUpButton() {
        signUpButton.click();
        WaitForElement.waitURL();
    }
}
