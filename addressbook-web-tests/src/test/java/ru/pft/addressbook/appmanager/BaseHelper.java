package ru.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void type(By locator, String text) {
        if (text != null) {
            WebElement webElement = findElement(locator);
            if (!getValue(webElement).equals(text)) {
                webElement.clear();
                webElement.sendKeys(text);
            }
        }
    }

    protected void selectOption(By locator, String text) {
        if (text != null) {
            Select select = new Select(findElement(locator));
            if (!getValue(select.getFirstSelectedOption()).equals(text)) {
                select.selectByVisibleText(text);
            }
        }
    }

    protected WebElement findElement(By locator) {
        return wd.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return wd.findElements(locator);
    }

    protected void confirmAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    protected void waitTextOnPage(By locator, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    protected String getValue(WebElement element) {
        return element.getAttribute("value");
    }

    protected boolean isElementPresent(By locator) {
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> elements = findElements(locator);
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return elements.size() != 0 && elements.get(0).isDisplayed();
    }

    protected boolean isPageCurrent(By locatorFirstElement, String locatorText, By locatorSecondElement) {
        return isElementPresent(locatorFirstElement)
                && findElement(locatorFirstElement).getText().startsWith(locatorText)
                && isElementPresent(locatorSecondElement);
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void backToPage() {
        wd.navigate().back();
    }
}
