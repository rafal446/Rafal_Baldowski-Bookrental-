package pages.mainPage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import driver.DriverManager;
import driver.WaitForElement;
import pages.copiesPage.ShowCopiesPage;

import java.util.List;
import java.util.Random;

public class MainPage {

    List<WebElement> titles;
    WebElement title;
    String idOfTitle;
    String xpathToShowCopiesButton = "//button[@class=\"show-copies-btn btn--small btn btn--primary\"]";
    String xpathToRemoveButton = "//button[@class=\"remove-btn btn--small btn btn--error\"]";
    String xpathToTitle = "//div[@class=\"titles-list__item__title list__item__col list__item__col--primary\"]";
    String xpathToAuthor = "//div[@class=\"titles-list__item__author list__item__col\"]";
    String xpathToYear = "//div[@class=\"titles-list__item__year list__item__col\"]";
    int startedNumbersOfTitles;

    @FindBy(xpath = "//button[@id=\"add-title-button\"]")
    WebElement addNewTitleButton;
    String editButton = "//button[@class=\"edit-btn btn--small btn btn--warning\"]";
    @FindBy(xpath = "//ul[@class=\"titles-list list\"]")
    WebElement titlesPage;
    @FindBy(xpath ="//form[@class=\"form title-form form--short\"]")
    WebElement addTitleForm;
    @FindBy(xpath = "//input[@name=\"title\"]")
    WebElement titleInputField;
    @FindBy(xpath = "//input[@name=\"author\"]")
    WebElement authorInputField;
    @FindBy(xpath = "//input[@name=\"year\"]")
    WebElement yearInputField;
    @FindBy(xpath = "//button[@name=\"submit-button\"]")
    WebElement addTitleButton;
    @FindBy (xpath = "//button[@name=\"submit-button\"]")
    WebElement submitEditTitleButton;
    @FindBy(xpath = "//p[@class=\"alert__content\"]")
    WebElement alertMessage;

    public MainPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    @Step("Adding new title to list {title} / {author} / {year}")
    public MainPage addNewTitleToList(String title, String author, String year) {
        startedNumbersOfTitles = getTitlesList().size();
        WaitForElement.waitUntilElementIsClickable(addNewTitleButton);
        addNewTitleButton.click();
        authorInputField.sendKeys(title);
        titleInputField.sendKeys(author);
        yearInputField.sendKeys(year);
        addTitleButton.click();
        WaitForElement.waitUntilInvisibilityOfElement(addTitleForm);
        return this;
    }
    @Step("Getting random title from liost")
    public WebElement randomTitleFromList() {
        Random random = new Random();
        int randomTitle = random.nextInt(getTitlesList().size() - 1);
        title =  titles.get(randomTitle);
        return  title;
    }
    @Step("Removing random title from list")
    public MainPage removeElementFromList() {
        startedNumbersOfTitles = getTitlesList().size();
        WaitForElement.waitUntilElementIsVisible(titlesPage);
        WebElement toRemove = randomTitleFromList();
        toRemove.findElement(By.xpath(xpathToRemoveButton)).click();
        WaitForElement.waitUntilElementIsVisible(addNewTitleButton);
        return this;
    }
    @Step("Editing random title to : {newTitle} / {newAuthor} / {newYear}")
    public MainPage editRandomTitle(String newTitle, String newAuthor, String newYear) {
        WebElement toEdit = randomTitleFromList();
        idOfTitle = toEdit.getAttribute("id");
        toEdit.findElement(By.xpath(editButton)).click();
        WaitForElement.waitUntilElementIsVisible(titleInputField);
        authorInputField.clear();
        titleInputField.clear();
        yearInputField.clear();
        titleInputField.sendKeys(newTitle);
        authorInputField.sendKeys(newAuthor);
        yearInputField.sendKeys(newYear);
        submitEditTitleButton.click();
        WaitForElement.waitUntilInvisibilityOfElement(addTitleForm);
        getTitlesList();
        return this;
    }
    @Step("Getting edited title")
    public WebElement getEditedTitle() {
        WebElement element;
        WaitForElement.waitUntilElementIsVisible(titlesPage);
        element = titlesPage.findElement(By.id(idOfTitle));
        return element;
    }
    @Step("Getting title after edition")
    public String getTitleAfterEdit() {
        return getEditedTitle().findElement(By.xpath(xpathToTitle)).getText();
    }
    @Step("Getting author after edition")
    public String getAuthorAfterEdition() {
        return getEditedTitle().findElement(By.xpath(xpathToAuthor)).getText();
    }
    @Step("Getting year of publication after edition")
    public String getYearAfterEdition() {
        return getEditedTitle().findElement(By.xpath(xpathToYear)).getText();
    }
    @Step("Getting all titles")
    public List<WebElement> getTitlesList() {
        WaitForElement.waitUntilElementIsVisible(titlesPage);
        titles = getTitlesPage().findElements(By.xpath("//li[@class=\"titles-list__item list__item\"]"));
        return titles;
    }
    @Step("Checking is titles displayed")
    public boolean isTitlesFieldDisplayed() {
        return titlesPage.isDisplayed();
    }
    @Step("Getting titles page")
    public WebElement getTitlesPage() {
        return titlesPage;
    }
    @Step("Getting copy of random title")
    public ShowCopiesPage copyOfRandomTitle() {
        randomTitleFromList().findElement(By.xpath(xpathToShowCopiesButton)).click();
        WaitForElement.waitUntilInvisibilityOfElement(addTitleForm);
        return new ShowCopiesPage();
    }
    @Step("Getting alert message")
    public String getAlertMessage() {
        return alertMessage.getText();
    }
    @Step("Getting started number of title")
    public int getStartedNumbersOfTitles() {
        return startedNumbersOfTitles;
    }

}
