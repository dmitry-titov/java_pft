package ru.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login(System.getProperty("web.adminLogin"), System.getProperty("web.adminPassword")));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
