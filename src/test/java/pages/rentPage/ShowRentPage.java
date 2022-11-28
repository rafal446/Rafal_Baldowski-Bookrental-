package pages.rentPage;

import driver.DriverManager;
import driver.WaitForElement;
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
    WebElement visibilityListOfRents;
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
    String removeRentalButton = "//button[@class=\"remove-btn btn--small btn btn--error\"]";
    String editRentalButton = "//button[@class=\"edit-btn btn--small btn btn--warning\"]";
    String xpathNameOfCustomer = "//div[@class=\"rents-list__rent__customer-name list__item__col list__item__col--primary\"]";
    int numberOfRentals;
    String id;
    List<WebElement> listOfRentals;


    public List<WebElement> getListOfRentals() {
        listOfRentals = visibilityListOfRents.findElements(By.xpath(xpathOfOneRent));
        return listOfRentals;
    }

    public ShowRentPage addNewRent() {
        numberOfRentals = getListOfRentals().size();
        rentThisCopyButton.click();
        WaitForElement.waitUntilElementIsVisible(customerNameInput);
        customerNameInput.sendKeys("Test Client");
        submitButton.click();
        WaitForElement.waitUntilInvisibilityOfElement(customerNameInput);
        return this;
    }

    public String addNewEmptyRent() {
        rentThisCopyButton.click();
        WaitForElement.waitUntilElementIsVisible(customerNameInput);
        submitButton.click();
        WaitForElement.waitUntilElementIsVisible(errorMessageField);
        return errorMessageField.getText();
    }

    public WebElement getRandomRental() {
        getListOfRentals();
        Random random = new Random();
        int drawn = random.nextInt(listOfRentals.size() - 1);
        WebElement randomRental = listOfRentals.get(drawn);
        return randomRental;
    }

    public ShowRentPage removeRandomRental() {
        WaitForElement.waitUntilElementIsVisible(visibilityListOfRents);
        numberOfRentals = getListOfRentals().size();
        WebElement toRemove = getRandomRental();
        getRandomRental().findElement(By.xpath(removeRentalButton)).click();
        WaitForElement.waitUntilInvisibilityOfElement(toRemove.findElement(By.xpath(removeRentalButton)));
        return this;
    }

    public ShowRentPage editRandomRental(String customer) {
        WebElement toEdit = getRandomRental();
        id = toEdit.getAttribute("id");
        toEdit.findElement(By.xpath(editRentalButton)).click();
        WaitForElement.waitUntilElementIsVisible(addRentalForm);
        customerNameInput.clear();
        customerNameInput.sendKeys(customer);
        submitButton.click();
        WaitForElement.waitUntilInvisibilityOfElement(addRentalForm);
        return this;
    }

    public WebElement getRentalById() {
        WebElement edited;
        edited = visibilityListOfRents.findElement(By.id(id));
        return edited;
    }

    public int getStartedNumberOfRentals() {
        return numberOfRentals;
    }

    public String getNameOfCustomer(WebElement element) {
        return element.findElement(By.xpath(xpathNameOfCustomer)).getText();
    }
}
