package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoNewContactForm() {
        if (isPageCurrent(By.tagName("h1"), "Edit / add address book entry", By.name("submit"))) {
            return;
        }
        click(By.linkText("add new"));
    }

    public void gotoGroupsPage() {
        if (isPageCurrent(By.tagName("h1"), "Groups", By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void gotoContactsPage() {
        if (isPageCurrent(By.xpath("//label/strong"), "Number of results: ", By.id("search-az"))) {
            return;
        }
        click(By.linkText("home"));
    }
}
