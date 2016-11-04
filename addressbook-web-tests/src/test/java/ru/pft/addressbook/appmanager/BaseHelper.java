package ru.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseHelper {

    private WebDriver wd;
    private Wait<WebDriver> wait;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
        this.wait = new WebDriverWait(wd, 5, 100).ignoring(StaleElementReferenceException.class);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement webElement = findElement(locator);
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected void selectOption(By locator, String text) {
        new Select(findElement(locator)).selectByVisibleText(text);
    }

    protected WebElement findElement(By locator) {
        return wd.findElement(locator);
    }

    protected void confirmAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
