package ru.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.pft.mantis.appmanager.ApplicationManager;
import ru.pft.mantis.model.MailMessage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestBase {

    protected final static ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),
                "config_inc.php",
                "config_inc_backup.php");
    }

    @AfterSuite
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc_backup.php", "config_inc.php");
        app.stop();
    }

    protected String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
