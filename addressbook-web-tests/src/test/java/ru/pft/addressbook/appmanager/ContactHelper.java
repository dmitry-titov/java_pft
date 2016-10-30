package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(ChromeDriver wd) {
        super(wd);
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        selectOption(By.xpath("//select[@name='bday']/option[@value='" + contactData.getBday() + "']"));
        selectOption(By.xpath("//select[@name='bmonth']/option[@value='" + contactData.getBmonth() + "']"));
        type(By.name("byear"), contactData.getByear());
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }
}
