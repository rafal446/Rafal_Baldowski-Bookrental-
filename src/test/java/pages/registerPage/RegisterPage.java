package pages.registerPage;

import driver.DriverManager;
import driver.WaitForElement;
import io.qameta.allure.Step;
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
    @Step("Getting success sign up message")
    public String getSuccessSignUpMessage() {
        WaitForElement.waitUntilElementIsVisible(successSignUpMessage);
        return successSignUpMessage.getText();
    }
    @Step("Getting wrong register message")
    public String getWrongRegisterAlert() {
        WaitForElement.waitUntilElementIsVisible(wrongRegisterAlert);
        return wrongRegisterAlert.getText();
    }
    @Step("Register with correct data")
    public String registerWithCorrectData() {
        WaitForElement.waitUntilElementIsVisible(repeatPasswordField);
        String data = randomUserAndPassword();
        loginField.sendKeys(data);
        passwordField.sendKeys(data);
        repeatPasswordField.sendKeys(data);
        signUpButton.click();
        WaitForElement.waitUntilElementIsVisible(successSignUpMessage);
        String message = getSuccessSignUpMessage();
        return message;
    }
    @Step("Register uswer that already exists")
    public String registerUserThatAlreadyExists() {
        WaitForElement.waitUntilElementIsVisible(repeatPasswordField);
        loginField.sendKeys("testUser");
        passwordField.sendKeys("testPassword");
        repeatPasswordField.sendKeys("testPassword");
        signUpButton.click();
        WaitForElement.waitUntilElementIsVisible(wrongRegisterAlert);
        String message = getWrongRegisterAlert();
        return message;
    }
    @Step("Register user when second password is different than first")
    public String registerUserWhenPasswordDoesNotMatch() {
        WaitForElement.waitUntilElementIsVisible(repeatPasswordField);
        String data = randomUserAndPassword();
        loginField.sendKeys(data);
        passwordField.sendKeys(data);
        repeatPasswordField.sendKeys(data + "1");
        signUpButton.click();
        WaitForElement.waitUntilElementIsVisible(wrongRegisterAlert);
        String message = getWrongRegisterAlert();
        return message;
    }
    @Step("Generating random data to create new user")
    public String randomUserAndPassword() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        String userAndPassword = "test" + randomNumber;
        return userAndPassword;
    }
}
