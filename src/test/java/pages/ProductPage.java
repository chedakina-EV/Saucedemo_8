package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private final By title = By.className("title");
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean pageTitleVisible() {
        return driver.findElement(title).isDisplayed();
    }

    public String getTitleText() {
        return driver.findElement(title).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
