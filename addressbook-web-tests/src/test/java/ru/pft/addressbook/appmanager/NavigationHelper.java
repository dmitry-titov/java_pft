package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoNewContactForm() {
        click(By.linkText("add new"));
    }

    public void gotoGroupsPage() {
        click(By.linkText("groups"));
    }

    public void gotoContactsPage() {
        click(By.linkText("home"));
    }
}
