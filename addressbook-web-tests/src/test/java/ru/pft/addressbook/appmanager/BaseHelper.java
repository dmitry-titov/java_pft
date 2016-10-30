package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseHelper {

    protected ChromeDriver wd;
    private Wait<WebDriver> wait;

    public BaseHelper(ChromeDriver wd) {
        this.wd = wd;
        this.wait = new WebDriverWait(wd, 5, 100).ignoring(StaleElementReferenceException.class);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    protected void selectOption(By locator) {
        if (!isSelected(locator)) {
            click(locator);
        }
    }

    protected boolean isSelected(By locator) {
        return wd.findElement(locator).isSelected();
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
