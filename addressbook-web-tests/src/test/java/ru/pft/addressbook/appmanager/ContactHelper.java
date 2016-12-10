package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;

import java.util.List;

import static org.openqa.selenium.By.*;
import static org.testng.Assert.assertFalse;

public class ContactHelper extends BaseHelper {

    private Contacts contactCache = null;

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
        type(name("home"), contactData.getHomePhone());
        type(name("mobile"), contactData.getMobilePhone());
        type(name("work"), contactData.getWorkPhone());
        type(name("email"), contactData.getEmail());
        type(name("email2"), contactData.getEmail2());
        type(name("email3"), contactData.getEmail3());
        type(name("address"), contactData.getAddress());

        selectOption(name("bday"), contactData.getBday());
        selectOption(name("bmonth"), contactData.getBmonth());
        type(name("byear"), contactData.getByear());
        attach(name("photo"), contactData.getPhotoPath());


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
        contactCache = null;
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
        contactCache = null;
        returnToContactsPage();
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        initDelete();
        confirmDelete();
        contactCache = null;
    }

    public boolean isThereContact() {
        return isElementPresent(name("selected[]"));
    }

    public int count() {
        return findElements(By.cssSelector("tr[name='entry']")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        modifyContactById(contact.getId());
        String firstName = getValue(findElement(name("firstname")));
        String lastName = getValue(findElement(name("lastname")));
        String home = getValue(findElement(name("home")));
        String mobile = getValue(findElement(name("mobile")));
        String work = getValue(findElement(name("work")));

        String email1 = getValue(findElement(name("email")));
        String email2 = getValue(findElement(name("email2")));
        String email3 = getValue(findElement(name("email3")));

        String address = getValue(findElement(name("address")));

        backToPage();
        return new ContactData().withId(contact.getId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withHome(home)
                .withMobile(mobile)
                .withWork(work)
                .withEmail(email1)
                .withEmail2(email2)
                .withEmail3(email3)
                .withAddress(address);
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = findElements(cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            String lastName = element.findElement(cssSelector("td:nth-child(2)")).getText();
            String firstName = element.findElement(cssSelector("td:nth-child(3)")).getText();
            String address = element.findElement(cssSelector("td:nth-child(4)")).getText();
            String allEmails = element.findElement(cssSelector("td:nth-child(5)")).getText();
            String allPhones = element.findElement(cssSelector("td:nth-child(6)")).getText();

            int id = Integer.parseInt(element.findElement(cssSelector("td:nth-child(1)> input")).getAttribute("id"));
            ContactData contact = new ContactData()
                    .withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmails)
                    .withAddress(address);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactData details(ContactData contact) {
        String details[] = findElement(By.id("content")).getText().replaceAll("[HMW:]", "").split("\n\n");
        String firstPack[] = details[0].split("\n");

        return new ContactData()
                .withId(contact.getId())
                .withFullName(firstPack[0])
                .withAddress(firstPack[1])
                .withAllPhones(details[1].replaceAll(" ", ""))
                .withAllEmails(details[2]);
    }
}