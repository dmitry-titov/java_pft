package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactForm() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        selectOption(By.name("bday"), contactData.getBday());
        selectOption(By.name("bmonth"), contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());

        if (creation) {
            selectOption(By.name("new_group"), contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void returnToContactsPage() {
        click(By.linkText("home page"));
    }

    public void selectById(int id) {
        click(findElement(By.cssSelector("input[value= '" + id + "']")));
    }

    public void viewDetailsById(int id) {
        click(findElement(By.cssSelector("a[href='view.php?id=" + id + "']")));
    }

    public void modifyOnDetailsPage() {
        click(By.name("modifiy"));
    }

    public void submitContactUpdate() {
        click(By.name("update"));
    }

    public void modifyContactById(int id) {
        click(findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")));
    }

    public void modify(ContactData contact) {
        fillContactForm(contact, false);
        submitContactUpdate();
        returnToContactsPage();
    }

    public void initDelete() {
        click(By.cssSelector("input[value='Delete']"));
    }

    public void initDeleteAndWait() {
        click(By.cssSelector("input[value='Delete']"));
        waitTextOnPage(By.xpath("//div[@id='content']/h1"), "Delete record");
    }

    public void confirmDelete() {
        confirmAlert();
        waitTextOnPage(By.xpath("//div[@id='content']/h1"), "Delete record");
    }

    public void create(ContactData contactData, boolean creation) {
        fillContactForm(contactData, creation);
        submitContactForm();
        returnToContactsPage();
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        initDelete();
        confirmDelete();
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1)> input")).getAttribute("id"));
            ContactData contact = new ContactData()
                    .withId(id).withFirstName(firstName).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}
