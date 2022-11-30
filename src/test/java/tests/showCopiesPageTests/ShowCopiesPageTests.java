package tests.showCopiesPageTests;

import jdk.jfr.Description;
import tests.TestBase;
import org.testng.annotations.Test;
import pages.loginPage.LoginPage;
import pages.copiesPage.ShowCopiesPage;
import static org.testng.Assert.*;

public class ShowCopiesPageTests extends TestBase {

    @Test
    @Description("This test is checks if adding new copies work. It compares number of copies before and after " +
            "operation.")
    public void shouldAddNewCopyToList() {

        ShowCopiesPage showCopiesPage = new LoginPage()
                .correctLogin()
                .copyOfRandomTitle()
                .addNewCopy();

        int numberOfCopiesBeforeOperation = showCopiesPage.getSizeBeforeOperation();
        int numberOfCopiesAfterOperation = showCopiesPage.getCopiesList().size();

        assertEquals(numberOfCopiesBeforeOperation, numberOfCopiesAfterOperation - 1);
    }

    @Test
    @Description("This test is checks if the remove of one random copy works correct. It compares number of copies " +
            "before and after operation. If copy has rents history, test checks that message is displayed")
    public void shouldRemoveCopyFromList() throws InterruptedException {
            ShowCopiesPage showCopiesPage = new LoginPage()
                    .correctLogin()
                    .copyOfRandomTitle()
                    .removeRandomCopy();

            int numberOfCopiesBeforeOperation = showCopiesPage.getSizeBeforeOperation();
            int numberOfCopiesAfterOperation = showCopiesPage.getCopiesList().size();

            if (numberOfCopiesBeforeOperation == numberOfCopiesAfterOperation + 1) {
                assertEquals(numberOfCopiesBeforeOperation, numberOfCopiesAfterOperation + 1);
            } else {
                assertEquals(showCopiesPage.getAlertMessage(), "You can't remove copy with the rents history!");
            }
        }

    }

