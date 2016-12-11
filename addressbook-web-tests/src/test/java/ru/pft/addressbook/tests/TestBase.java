package ru.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.pft.addressbook.appmanager.ApplicationManager;

import java.util.Arrays;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected final static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @BeforeMethod
    public void logStartTest(java.lang.reflect.Method m, Object[] p) {
        logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logStopTest(java.lang.reflect.Method m) {
        logger.info("Stop test " + m.getName());
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }
}
