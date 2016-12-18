package ru.pft.mantis.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.mantis.model.MailMessage;
import ru.pft.mantis.model.UserData;
import ru.pft.mantis.model.UsersMantis;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() throws IOException, MessagingException {
        app.mail().start();

        String adminLogin = app.getProperty("web.adminLogin");
        UsersMantis users = app.db().users();
        if (users.size() == 1 && users.iterator().next().getUsername().equals(adminLogin)) {
            long now = System.currentTimeMillis();
            String email = String.format("user%s@localhost.localdomain", now);
            String user = String.format("user%s", now);
            app.registration().start(user, email);
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            String password = "password";
            app.registration().finish(confirmationLink, password);
        }
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
