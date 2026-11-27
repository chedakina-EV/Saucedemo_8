package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationTest extends BaseTest {

    @Test
    public void validLoginPassword() {
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");

        assertTrue(productPage.pageTitleVisible());
        assertEquals("Products", productPage.getTitleText());
        assertEquals("https://www.saucedemo.com/inventory.html", productPage.getCurrentUrl());
    }

    @Test
    public void lockedOutLogin() {
        loginPage.open();
        loginPage.authorization("locked_out_user", "secret_sauce");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.errorMsgText());
    }

    @Test
    public void invalidLoginPassword() {
        loginPage.open();
        loginPage.authorization("", "");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals("Epic sadface: Username is required", loginPage.errorMsgText());
    }

    @Test
    public void invalidPassword() {
        loginPage.open();
        loginPage.authorization("standard_user", "");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals("Epic sadface: Password is required", loginPage.errorMsgText());
    }

    @Test
    public void errorPassword() {
        loginPage.open();
        loginPage.authorization("standard_user", "Secret_sauce");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                loginPage.errorMsgText());
    }

    @Test
    public void invalidLogin() {
        loginPage.open();
        loginPage.authorization("", "secret_sauce");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals("Epic sadface: Username is required", loginPage.errorMsgText());
    }

    @Test
    public void errorLogin() {
        loginPage.open();
        loginPage.authorization("Standard_user", "secret_sauce");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                loginPage.errorMsgText());
    }
}
