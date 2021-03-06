package ru.pft.addressbook.appmanager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    private final Properties properties;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private DbHelper dbHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        System.setProperty("webdriver.firefox.marionette", "geckodriver");
        System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();
        if ("".equals(properties.getProperty("selenium.server"))) {
            switch (browser) {
                case BrowserType.CHROME:
                    wd = new ChromeDriver();
                    break;
                case BrowserType.FIREFOX:
                    wd = new FirefoxDriver();
                    break;
                case BrowserType.IE:
                    wd = new InternetExplorerDriver();
                    break;
                default:
                    wd = new ChromeDriver();
                    break;
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "linux")));
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }
}
