package tests.mainPageTests;

import org.testng.annotations.Test;
import pages.loginPage.LoginPage;
import pages.mainPage.MainPage;
import tests.TestBase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainPageTest extends TestBase {

    @Test
    public void shouldAddNewTitleToList() {

        MainPage mainPage = new LoginPage()
                .correctLogin();
        int startedNumberOfTitles = mainPage.getTitlesList().size();

        mainPage.addNewTitleToList("Czwarta rewolucja przemysłowa", "Klaus Schwab", "2016");
        int numberOfTitlesAfterAdded = mainPage.getTitlesList().size();

        assertEquals(startedNumberOfTitles, numberOfTitlesAfterAdded -1 );
    }

    @Test
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
            System.out.println(mainPage.getAlertMessage());
        }
    }

    @Test
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
