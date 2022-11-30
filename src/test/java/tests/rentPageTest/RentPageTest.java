package tests.rentPageTest;

import jdk.jfr.Description;
import pages.loginPage.LoginPage;
import tests.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.rentPage.ShowRentPage;

import static org.testng.Assert.assertEquals;

public class RentPageTest extends TestBase {

    @Test
    @Description("Test checks if addition of new rent works correct. It compares number of rentals before and after" +
            " operation.")
    public void shouldAddNewRentalToList() {
        ShowRentPage showRentPage = new LoginPage()
                .correctLogin()
                .copyOfRandomTitle()
                .rentBook();

        int numberOfRentalsBeforeNewRental;
        if (driver.getPageSource().contains("alert alert--info")) {
            numberOfRentalsBeforeNewRental = 0;
            showRentPage.addNewRent();
        } else {
            numberOfRentalsBeforeNewRental = showRentPage.getListOfRentals().size();
            showRentPage.addNewRent();
        }

        int numberOfRentalsAfterNewRental = showRentPage.getListOfRentals().size();

        assertEquals(numberOfRentalsAfterNewRental - 1, numberOfRentalsBeforeNewRental);
    }

    @Test
    @Description("This test is checks whether is possible to add new rental without type client name. It checks if the" +
            " message is displayed")
    public void shouldNotAddNewRentalWhenCustomerIsEmpty() {
        String errorMessage = new LoginPage()
                .correctLogin()
                .copyOfRandomTitle()
                .rentBook()
                .addNewEmptyRent();

        assertEquals(errorMessage, "\"customerName\" field shouldn't be empty...");
    }

    @Test
    @Description("This test is checks if the remove of  one random rental works correct. It compares number of rentals " +
            "before and after operation. When there is no rentals in history, test adds two new and after that remove " +
            "one. ")
    public void shouldRemoveOneRandomRental() throws InterruptedException {
        ShowRentPage showRentPage = new LoginPage()
                .correctLogin()
                .copyOfRandomTitle()
                .rentBook();

        if (driver.getPageSource().contains("alert alert--info")) {
            showRentPage.addNewRent()
                        .addNewRent();
        }
        int numberOfRentalsBeforeNewRental = showRentPage.getListOfRentals().size();

        showRentPage.removeRandomRental();
        Thread.sleep(1000);

        int numberOfRentalsAfterRemove = showRentPage.getListOfRentals().size();

        assertEquals(numberOfRentalsBeforeNewRental, numberOfRentalsAfterRemove + 1);
    }

    @Test
    @Description("This test is checks if editing of one random rental works. It compares name of customer after " +
            "editing.")
    public void shouldEditRandomRental() {
        ShowRentPage showRentPage = new LoginPage()
                .correctLogin()
                .copyOfRandomTitle()
                .rentBook()
                .editRandomRental("Paweł Wojdyga");

        WebElement edited = showRentPage.getRentalById();
        String newCustomer = showRentPage.getNameOfCustomer(edited);

        assertEquals(newCustomer, "PAWEŁ WOJDYGA");
    }

}
