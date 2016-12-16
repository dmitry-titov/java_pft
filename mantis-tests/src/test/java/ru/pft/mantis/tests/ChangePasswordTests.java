package ru.pft.mantis.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.mantis.model.MailMessage;
import ru.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void changePasswordToUserTest() throws IOException, MessagingException {
        String adminLogin = app.getProperty("web.adminLogin");
        String adminPassword = app.getProperty("web.adminPassword");

        UserData user = app.db().users().stream()
                .filter((u) -> !u.getUsername().equals(adminLogin)).iterator().next();
        app.admin().login(adminLogin, adminPassword);
        app.admin().resetPasswordToUser(user);

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
        String password = RandomStringUtils.randomAlphabetic(6);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user.getUsername(), password));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
