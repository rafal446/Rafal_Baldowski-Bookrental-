package tests.mainPageTests;

import jdk.jfr.Description;
import org.testng.annotations.Test;
import pages.loginPage.LoginPage;
import pages.mainPage.MainPage;
import tests.TestBase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainPageTest extends TestBase {

    @Test
    @Description("This test checks if the addition titles work, it adds new title to list and compares number of titles " +
            "before and after addition")
    public void shouldAddNewTitleToList() {

        MainPage mainPage = new LoginPage()
                .correctLogin();
        int startedNumberOfTitles = mainPage.getTitlesList().size();

        mainPage.addNewTitleToList("Czwarta rewolucja przemysłowa", "Klaus Schwab", "2016");
        int numberOfTitlesAfterAdded = mainPage.getTitlesList().size();

        assertEquals(startedNumberOfTitles, numberOfTitlesAfterAdded -1 );
    }

    @Test
    @Description("This test check if remove titles work, it delete one random title and compares number of title before " +
            "and after delete. In case when title has copies, it checks if the message is displayed")
    public void shouldRemoveTitleFromList() {
        MainPage mainPage = new LoginPage()
                .correctLogin()
                .removeElementFromList();

        int startedNumberOfTitles = mainPage.getStartedNumbersOfTitles();
        int numberOfTitlesAfterRemoved = mainPage.getTitlesList().size();

        if (startedNumberOfTitles == numberOfTitlesAfterRemoved + 1){
            assertEquals(startedNumberOfTitles, numberOfTitlesAfterRemoved + 1);
        } else {
            assertEquals(mainPage.getAlertMessage(), "You can't remove titles with copies!");
        }
    }

    @Test
    @Description("This test change title, author and year of publication of one random title, next it searches this " +
            "title by id number and compares")
    public void shouldEditTitle() {
        MainPage mainPage = new LoginPage()
                .correctLogin()
                .editRandomTitle("NextTitle", "NewAuthor", "1967");

        String newTitle = mainPage.getTitleAfterEdit();
        String newAuthor = mainPage.getAuthorAfterEdition();
        String newYear = mainPage.getYearAfterEdition();

        assertEquals(newTitle, "NEXTTITLE");
        assertEquals(newAuthor, "by NewAuthor");
        assertEquals(newYear, "(1967)");
    }
}
