package ru.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.pft.mantis.model.UserData;

public class AdministrationHelper extends BaseHelper {

    public AdministrationHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String login, String password) {
        openUrl(baseUrl + "/login_page.php");
        type(By.name("username"), login);
        type(By.name("password"), password);
        checked(By.id("remember-login"), false);
        checked(By.id("secure-session"), false);
        click(By.cssSelector("input[type='submit']"));
    }

    public void resetPasswordToUser(UserData user) {
//        openUrl(baseUrl + "/manage_user_page.php");
        click(By.linkText("Управление пользователями"));
        click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", user.getId())));
        click(By.cssSelector("input[value='Сбросить пароль']"));
        waitTextOnPage(By.cssSelector("h2"), "Управление пользователями");
    }
}
