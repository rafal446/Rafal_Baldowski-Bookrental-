package pages.rentPage;

import driver.DriverManager;
import driver.WaitForElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class ShowRentPage {

    public ShowRentPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }
    @FindBy(xpath = "//ul[@class=\"rents-list list\"]")
    WebElement visibilityListOfRentals;
    @FindBy(xpath = "//button[@id=\"add-rent-button\"]")
    WebElement rentThisCopyButton;
    @FindBy(xpath = "//form[@class=\"form rent-form form--short form--uncut\"]")
    WebElement addRentalForm;
    @FindBy(xpath = "//input[@name=\"customer-name\"]")
    WebElement customerNameInput;
    @FindBy(xpath = "//button[@name=\"submit-button\"]")
    WebElement submitButton;
    @FindBy(xpath = "//div[@class=\"alert alert--error\"]")
    WebElement errorMessageField;


    String xpathOfOneRent = "//li[@class=\"rents-list__rent list__item\"]";
    String xpathRemoveRentalButton = "//button[@class=\"remove-btn btn--small btn btn--error\"]";
    String xpathEditRentalButton = "//button[@class=\"edit-btn btn--small btn btn--warning\"]";
    String xpathNameOfCustomer = "//div[@class=\"rents-list__rent__customer-name list__item__col list__item__col--primary\"]";
    int numberOfRentals;
    String id;
    List<WebElement> listOfRentals;

    @Step("Getting list of rentals")
    public List<WebElement> getListOfRentals() {
        listOfRentals = visibilityListOfRentals.findElements(By.xpath(xpathOfOneRent));
        return listOfRentals;
    }
    @Step("Adding new rent")
    public ShowRentPage addNewRent() {
        WaitForElement.waitUntilElementIsVisible(rentThisCopyButton);
        rentThisCopyButton.click();
        WaitForElement.waitUntilElementIsVisible(customerNameInput);
        customerNameInput.sendKeys("Test Client");
        submitButton.click();
        WaitForElement.waitUntilInvisibilityOfElement(customerNameInput);
        return this;
    }
    @Step("Adding new empty rent")
    public String addNewEmptyRent() {
        WaitForElement.waitUntilElementIsVisible(rentThisCopyButton);
        rentThisCopyButton.click();
        WaitForElement.waitUntilElementIsVisible(customerNameInput);
        submitButton.click();
        WaitForElement.waitUntilElementIsVisible(errorMessageField);
        return errorMessageField.getText();
    }
    @Step("Getting new random rental")
    public WebElement getRandomRental() {
        if (getListOfRentals().isEmpty());
        WebElement randomRental;
        Random random = new Random();
        if (listOfRentals.size() == 1) randomRental = listOfRentals.get(0);
        else {
        int drawn = random.nextInt(listOfRentals.size() - 1 );
        randomRental = listOfRentals.get(drawn);
        }
        return randomRental;
    }
    @Step("Removing random rental")
    public ShowRentPage removeRandomRental() {
        WaitForElement.waitUntilElementIsVisible(visibilityListOfRentals);
        WebElement toRemove = getRandomRental();
        getRandomRental().findElement(By.xpath(xpathRemoveRentalButton)).click();
        WaitForElement.waitUntilInvisibilityOfElement(toRemove.findElement(By.xpath(xpathRemoveRentalButton)));
        return this;
    }
    @Step("Editing random rental to {customer}")
    public ShowRentPage editRandomRental(String customer) {
        WebElement toEdit = getRandomRental();
        id = toEdit.getAttribute("id");
        toEdit.findElement(By.xpath(xpathEditRentalButton)).click();
        WaitForElement.waitUntilElementIsVisible(addRentalForm);
        customerNameInput.clear();
        customerNameInput.sendKeys(customer);
        submitButton.click();
        WaitForElement.waitUntilInvisibilityOfElement(addRentalForm);
        return this;
    }
    @Step("Getting random rental by id")
    public WebElement getRentalById() {
        WebElement edited;
        edited = visibilityListOfRentals.findElement(By.id(id));
        return edited;
    }
    @Step("Getting name of customer")
    public String getNameOfCustomer(WebElement element) {
        return element.findElement(By.xpath(xpathNameOfCustomer)).getText();
    }
}
