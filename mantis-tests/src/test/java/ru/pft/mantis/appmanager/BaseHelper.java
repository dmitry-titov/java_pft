package ru.pft.mantis.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseHelper {

    protected WebDriver wd;
    protected ApplicationManager app;
    protected Wait<WebDriver> wait;

    protected String baseUrl;


    public BaseHelper(ApplicationManager app) {
        this.app = app;
        this.wd = app.getWebDriver();
        this.wait = new WebDriverWait(wd, 5, 100).ignoring(StaleElementReferenceException.class);
        this.baseUrl = app.getProperty("web.baseUrl");
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

    protected void attach(By locator, File file) {
        if (file != null) {
            findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    protected void attach(By locator, String path) {
        if (path != null) {
            File file = new File(path);
            findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    protected void selectOption(By locator, String text) {
        if (text != null) {
            Select select = new Select(findElement(locator));
            if (!getText(select.getFirstSelectedOption()).equals(text)) {
                select.selectByVisibleText(text);
            }
        }
    }

    protected void selectOptionByValue(By locator, int value) {
        if (value != 0) {
            Select select = new Select(findElement(locator));
            if (!getValue(select.getFirstSelectedOption()).equals(value)) {
                select.selectByValue(String.valueOf(value));
            }
        }
    }

    protected WebElement findElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
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

    protected String getText(WebElement element) {
        return element.getText();
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

    protected void openUrl(String url) {
        wd.get(url);
    }

    protected void checked(By locator, boolean checked) {
        WebElement element = findElement(locator);
        boolean state = element.isSelected();
        if ((checked && !state) || (!checked && state)) {
            click(element);
        }
    }

    protected void backToPage() {
        wd.navigate().back();
    }
}
