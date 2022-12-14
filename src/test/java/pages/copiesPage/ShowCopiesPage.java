package pages.copiesPage;

import driver.DriverManager;
import driver.WaitForElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.rentPage.ShowRentPage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class ShowCopiesPage {

    List<WebElement> listOfCopies;
    WebElement oneRandomCopy;
    int sizeBeforeOperation;
    String id;

    public ShowCopiesPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    @FindBy(xpath = "//button[@id=\"add-item-button\"]")
    WebElement addNewCopyButton;
    @FindBy(xpath = "//input[@id=\"id\"]")
    WebElement dateField;
    @FindBy(xpath = "//button[@name=\"submit-button\"]")
    WebElement submitButton;
    @FindBy(xpath = "//ul[@class=\"items-list list\"]")
    WebElement copiesList;
    @FindBy(xpath = "//form[@class=\"form item-form form--short form--uncut\"]")
    WebElement addCopyForm;
    @FindBy(xpath = "//div[@class=\"items-list__item__purchase-date list__item__col list__item__col--primary\"]")
    WebElement purchaseDate;
    @FindBy(xpath = "//p[@class=\"alert__content\"]")
    WebElement alertMessage;

    String showHistoryButton = "//button[@class=\"show-rents-btn btn--small btn btn--primary\"]";
    String  editButton = "//button[@class=\"edit-btn btn--small btn btn--warning\"]";
    String  removeCopyButton = "//button[@class=\"remove-btn btn--small btn btn--error\"]";
    String xpathToOneCopy = "//li[@class=\"items-list__item list__item\"]";
    @Step("Adding new copy")
    public ShowCopiesPage addNewCopy() {
        WaitForElement.waitUntilElementIsClickable(addNewCopyButton);
        sizeBeforeOperation = getCopiesList().size();
        addNewCopyButton.click();
        submitButton.click();
        WaitForElement.waitUntilInvisibilityOfElement(addCopyForm);
        return this;
    }
    @Step("Removing random copy")
    public ShowCopiesPage removeRandomCopy() throws InterruptedException {
        WaitForElement.waitUntilElementIsClickable(addNewCopyButton);
        WebElement toRemove = getRandomCopy();
        sizeBeforeOperation = getCopiesList().size();
        WebElement button = toRemove.findElement(By.xpath(removeCopyButton));
        button.click();
        Thread.sleep(1000);
        return this;
    }
    @Step("Getting copies list")
    public List<WebElement> getCopiesList() throws NoSuchElementException {
        WaitForElement.waitUntilElementIsVisible(copiesList);
        listOfCopies = copiesList.findElements(By.xpath(xpathToOneCopy));
        return listOfCopies;
    }
    @Step("Getting random copy")
    public WebElement getRandomCopy() {
        Random random = new Random();
        if (getCopiesList().size() == 1) oneRandomCopy = listOfCopies.get(0);
        else if (getCopiesList().size() > 1) {
            int randomCopy = random.nextInt(getCopiesList().size() - 1);
            oneRandomCopy = listOfCopies.get(randomCopy);
        }
        return oneRandomCopy;
    }
    @Step("Editing Random Copy")
    public ShowCopiesPage editRandomCopy() {
        WebElement toEdit = getRandomCopy();
        id = toEdit.getAttribute("id");
        toEdit.findElement(By.xpath(editButton)).click();
        WaitForElement.waitUntilElementIsVisible(submitButton);
        submitButton.click();
        WaitForElement.waitUntilInvisibilityOfElement(addCopyForm);
        return this;
    }
    @Step("Getting numbers of copies")
    public int getSizeBeforeOperation() {
        return sizeBeforeOperation;
    }

    public WebElement getCopyById() {
        WebElement x;
        x = copiesList.findElement(By.id(id));
        return x;
    }
    @Step("Clicked on showHistory Button")
    public ShowRentPage rentBook() {
        WebElement toRent = getRandomCopy();
        toRent.findElement(By.xpath(showHistoryButton)).click();
        WaitForElement.waitUntilInvisibilityOfElement(copiesList);
        return new ShowRentPage();
    }
    @Step("Getting alert message")
    public String getAlertMessage() {
        return alertMessage.getText();
    }

}