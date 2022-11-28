package tests.rentPageTest;

import pages.loginPage.LoginPage;
import tests.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.rentPage.ShowRentPage;

import static org.testng.Assert.assertEquals;

public class RentPageTest extends TestBase {

    @Test
    public void shouldAddNewRentalToList() {
        ShowRentPage showRentPage = new LoginPage()
                .correctLogin()
                .copiesOfRandomTitle()
                .rentBook()
                .addNewRent();

        int numberOfRentalsBeforeNewRent = showRentPage.getStartedNumberOfRentals();
        int numberOfRentalsAfterNewRent = showRentPage.getListOfRentals().size();

        assertEquals(numberOfRentalsBeforeNewRent, numberOfRentalsAfterNewRent - 1);
    }

    @Test
    public void shouldNotAddNewRentalWhenCustomerIsEmpty() {
        String errorMessage = new LoginPage()
                .correctLogin()
                .copiesOfRandomTitle()
                .rentBook()
                .addNewEmptyRent();

        assertEquals(errorMessage, "\"customerName\" field shouldn't be empty...");
    }

    @Test
    public void shouldRemoveOneRandomRental() {
        ShowRentPage showRentPage = new LoginPage()
                .correctLogin()
                .copiesOfRandomTitle()
                .rentBook()
                .removeRandomRental();

        int numberOfRentalsBeforeRemove = showRentPage.getStartedNumberOfRentals();
        int numberOfRentalsAfterRemove = showRentPage.getListOfRentals().size();

        assertEquals(numberOfRentalsBeforeRemove, numberOfRentalsAfterRemove + 1);
    }

    @Test
    public void shouldEditRandomRental() {
        ShowRentPage showRentPage = new LoginPage()
                .correctLogin()
                .copiesOfRandomTitle()
                .rentBook()
                .editRandomRental("Paweł Wojdyga");

        WebElement edited = showRentPage.getRentalById();
        String newCustomer = showRentPage.getNameOfCustomer(edited);

        assertEquals(newCustomer, "PAWEŁ WOJDYGA");
    }

}
