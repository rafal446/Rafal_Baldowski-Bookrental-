package pages.registerPage;

import driver.DriverManager;
import driver.WaitForElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class RegisterPage {

    @FindBy(xpath = "//input[@id=\"login\"]")
    WebElement loginField;
    @FindBy(xpath = "//input[@name=\"password\"]")
    WebElement passwordField;
    @FindBy(xpath = "//input[@id=\"password-repeat\"]")
    WebElement repeatPasswordField;
    @FindBy(xpath = "//button[@id=\"register-btn\"]")
    WebElement signUpButton;
    @FindBy(xpath = "//div[@class=\"alert alert--success\"]")
    WebElement successSignUpMessage;
    @FindBy(xpath = "//p[@class=\"alert__content\"]")
    WebElement wrongRegisterAlert;

    public RegisterPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    public void typeLoginName(String userName) {
        loginField.sendKeys(userName);
    }
    public void typePasswordField(String password) {
        passwordField.sendKeys(password);
    }
    public void typeRepeatPasswordField(String password) {
        repeatPasswordField.sendKeys(password);
    }
    public void clickOnSignUpButton() {
        signUpButton.click();
    }
    public String getSuccessSignUpMessage() {
        WaitForElement.waitUntilElementIsVisible(successSignUpMessage);
        return successSignUpMessage.getText();
    }

    public String getWrongRegisterAlert() {
        WaitForElement.waitUntilElementIsVisible(wrongRegisterAlert);
        return wrongRegisterAlert.getText();
    }

    public String registerWithCorrectData() {
        WaitForElement.waitUntilElementIsVisible(loginField);
        String data = randomUserAndPassword();
        loginField.sendKeys(data);
        passwordField.sendKeys(data);
        repeatPasswordField.sendKeys(data);
        signUpButton.click();
        WaitForElement.waitUntilElementIsVisible(successSignUpMessage);
        String message = getSuccessSignUpMessage();
        return message;
    }

    public String registerUserThatAlreadyExists() {
        WaitForElement.waitUntilElementIsVisible(loginField);
        loginField.sendKeys("testUser");
        passwordField.sendKeys("testPassword");
        repeatPasswordField.sendKeys("testPassword");
        signUpButton.click();
        WaitForElement.waitUntilElementIsVisible(wrongRegisterAlert);
        String message = getWrongRegisterAlert();
        return message;
    }

    public String registerUserWhenPasswordDoesNotMatch() {
        WaitForElement.waitUntilElementIsVisible(loginField);
        String data = randomUserAndPassword();
        loginField.sendKeys(data);
        passwordField.sendKeys(data);
        repeatPasswordField.sendKeys(data + "1");
        signUpButton.click();
        WaitForElement.waitUntilElementIsVisible(wrongRegisterAlert);
        String message = getWrongRegisterAlert();
        return message;
    }

    public String randomUserAndPassword() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        String userAndPassword = "test" + randomNumber;
        return userAndPassword;
    }
}
