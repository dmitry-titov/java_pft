package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertFalse;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactForm() {
        click(name("submit"));
    }

    public void fillContactForm(ContactData contactData) {
        type(name("firstname"), contactData.getFirstName());
        type(name("middlename"), contactData.getMiddleName());
        type(name("lastname"), contactData.getLastName());
        type(name("nickname"), contactData.getNickname());
        type(name("mobile"), contactData.getMobile());
        type(name("email"), contactData.getEmail());

        selectOption(name("bday"), contactData.getBday());
        selectOption(name("bmonth"), contactData.getBmonth());
        type(name("byear"), contactData.getByear());

        if (getValue(findElements(xpath("//input[@type=\"submit\"]")).get(0)).equals("Enter")) {
            selectOption(name("new_group"), contactData.getGroup());
        } else {
            assertFalse(isElementPresent(name("new_group")));
        }
    }

    public void returnToContactsPage() {
        click(By.linkText("home page"));
    }

    public void selectById(int id) {
        click(findElement(cssSelector("input[value= '" + id + "']")));
    }

    public void viewDetailsById(int id) {
        click(findElement(cssSelector("a[href='view.php?id=" + id + "']")));
    }

    public void modifyOnDetailsPage() {
        click(name("modifiy"));
    }

    public void submitContactUpdate() {
        click(name("update"));
    }

    public void modifyContactById(int id) {
        click(findElement(cssSelector("a[href='edit.php?id=" + id + "']")));
    }

    public void modify(ContactData contact) {
        fillContactForm(contact);
        submitContactUpdate();
        returnToContactsPage();
    }

    public void initDelete() {
        click(cssSelector("input[value='Delete']"));
    }

    public void initDeleteAndWait() {
        click(cssSelector("input[value='Delete']"));
        waitTextOnPage(xpath("//div[@id='content']/h1"), "Delete record");
    }

    public void confirmDelete() {
        confirmAlert();
        waitTextOnPage(xpath("//div[@id='content']/h1"), "Delete record");
    }

    public void create(ContactData contactData) {
        fillContactForm(contactData);
        submitContactForm();
        returnToContactsPage();
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        initDelete();
        confirmDelete();
    }

    public boolean isThereContact() {
        return isElementPresent(name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = findElements(cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            String lastName = element.findElement(cssSelector("td:nth-child(2)")).getText();
            String firstName = element.findElement(cssSelector("td:nth-child(3)")).getText();
            int id = Integer.parseInt(element.findElement(cssSelector("td:nth-child(1)> input")).getAttribute("id"));
            ContactData contact = new ContactData()
                    .withId(id).withFirstName(firstName).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}