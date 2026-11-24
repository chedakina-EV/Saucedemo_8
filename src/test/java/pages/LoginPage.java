package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final By loginInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMsg = By.cssSelector("h3[data-test='error']");
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void authorization(String username, String password) {
        driver.findElement(loginInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean errorMsgVisible() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    public String errorMsgText() {
        return driver.findElement(errorMsg).getText();
    }
}
