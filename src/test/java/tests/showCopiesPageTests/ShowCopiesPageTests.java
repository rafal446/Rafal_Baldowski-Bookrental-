package tests.showCopiesPageTests;

import pages.mainPage.MainPage;
import tests.TestBase;
import org.testng.annotations.Test;
import pages.loginPage.LoginPage;
import pages.copiesPage.ShowCopiesPage;
import static org.testng.Assert.*;

public class ShowCopiesPageTests extends TestBase {

    @Test
    public void shouldAddNewCopyToList() {

        ShowCopiesPage showCopiesPage = new LoginPage()
                .correctLogin()
                .copiesOfRandomTitle()
                .addNewCopy();

        int numberOfCopiesBeforeOperation = showCopiesPage.getSizeBeforeOperation();
        int numberOfCopiesAfterOperation = showCopiesPage.getCopiesList().size();

        assertEquals(numberOfCopiesBeforeOperation, numberOfCopiesAfterOperation - 1);
    }

    @Test
    public void shouldRemoveCopyFromList() {

        ShowCopiesPage showCopiesPage = new LoginPage()
                .correctLogin()
                .copiesOfRandomTitle();
        int numberOfCopiesBeforeOperation = showCopiesPage.getCopiesList().size();

        ShowCopiesPage newShowCopiesPage =  showCopiesPage.removeRandomCopy();
        int numberOfCopiesAfterOperation = newShowCopiesPage.getCopiesList().size();
        System.out.println(numberOfCopiesAfterOperation);

            if (numberOfCopiesBeforeOperation == numberOfCopiesAfterOperation + 1) {
                assertEquals(numberOfCopiesBeforeOperation, numberOfCopiesAfterOperation + 1);
            } else {
                assertEquals(showCopiesPage.getAlertMessage(), "You can't remove copy with the rents history!");
            }
    }

    @Test
    public void shouldEditCopy() {

        ShowCopiesPage showCopiesPage = new LoginPage()
                .correctLogin()
                .copiesOfRandomTitle()
                .editRandomCopy();
    }
}
