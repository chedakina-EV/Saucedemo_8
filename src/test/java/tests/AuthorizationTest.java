package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationTest extends BaseTest {

    @Test
    public void validLoginPassword() {
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");

        assertTrue(productPage.pageTitleVisible());
        assertEquals(productPage.getTitleText(), "Products");
        assertEquals(productPage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void lockedOutLogin() throws InterruptedException {
        loginPage.open();
        loginPage.authorization("locked_out_user", "secret_sauce");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.errorMsgText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void invalidLoginPassword() {
        loginPage.open();
        loginPage.authorization("", "");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.errorMsgText(), "Epic sadface: Username is required");
    }

    @Test
    public void invalidPassword() {
        loginPage.open();
        loginPage.authorization("standard_user", "");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.errorMsgText(), "Epic sadface: Password is required");
    }

    @Test
    public void errorPassword() {
        loginPage.open();
        loginPage.authorization("standard_user", "Secret_sauce");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.errorMsgText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void invalidLogin() {
        loginPage.open();
        loginPage.authorization("", "secret_sauce");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.errorMsgText(), "Epic sadface: Username is required");
    }

    @Test
    public void errorLogin() {
        loginPage.open();
        loginPage.authorization("Standard_user", "secret_sauce");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.errorMsgText(),
                "Epic sadface: Username and password do not match any user in this service");
    }
}
