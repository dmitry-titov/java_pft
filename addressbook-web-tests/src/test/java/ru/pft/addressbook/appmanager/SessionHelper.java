package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class SessionHelper extends BaseHelper {

    public SessionHelper(ChromeDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.cssSelector("input[value='Login']"));
    }
}
