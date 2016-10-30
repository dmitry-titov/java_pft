package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ChromeDriver wd) {
        super(wd);
    }

    public void gotoNewContactForm() {
        click(By.linkText("add new"));
    }

    public void gotoGroups() {
        click(By.linkText("groups"));

    }
}
