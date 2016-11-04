package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactForm() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        selectOption(By.name("bday"), contactData.getBday());
        selectOption(By.name("bmonth"), contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());
    }

    public void returnToContactsPage() {
        click(By.linkText("home page"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void viewDetailsContact() {
        click(By.cssSelector("tr[name='entry'] > td:nth-child(7)"));
    }

    public void modifyContactInDetailsPage() {
        click(By.name("modifiy"));
    }

    public void submitContactUpdate() {
        click(By.name("update"));
    }

    public void modifyContact() {
        click(By.cssSelector("tr[name='entry'] > td:nth-child(8)"));
    }

    public void initDeleteContact() {
        click(By.cssSelector("input[value='Delete']"));
    }

    public void confirmDeleteContact() {
        confirmAlert();
    }
}
