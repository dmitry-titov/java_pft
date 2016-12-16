package ru.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends BaseHelper {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        openUrl(baseUrl + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void finish(String confirmationLink, String password) {
        openUrl(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[type='submit']"));
        waitTextOnPage(By.cssSelector("a[href='signup_page.php']"), "зарегистрировать новую учетную запись");
    }
}
