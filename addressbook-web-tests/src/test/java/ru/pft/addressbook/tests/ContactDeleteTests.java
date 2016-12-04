package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTests extends TestBase {

    @Test
    public void contactDeleteThroughContactsListTest() {
        app.getNavigationHelper().gotoContactsPage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoNewContactForm();
            ContactData contact = new ContactData(
                    "testName",
                    "testMiddleName",
                    "testLastName",
                    "testNickname",
                    "89285681010",
                    "test@test.com",
                    "7",
                    "April",
                    "1977", null);
            app.getContactHelper().createContact(contact, true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        int lastIdx = before.size() - 1;
        app.getContactHelper().selectContact(lastIdx);
        app.getContactHelper().initDeleteContact();
        app.getContactHelper().confirmDeleteContact();
        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(lastIdx);
        Assert.assertEquals(after, before);
    }

    @Test
    public void contactDeleteThroughContactFormTest() {
        app.getNavigationHelper().gotoContactsPage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoNewContactForm();
            ContactData contact = new ContactData(
                    "testName",
                    "testMiddleName",
                    "testLastName",
                    "testNickname",
                    "89285681010",
                    "test@test.com",
                    "7",
                    "April",
                    "1977", null);
            app.getContactHelper().createContact(contact, true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        int lastIdx = before.size() - 1;
        app.getContactHelper().modifyContact(lastIdx);
        app.getContactHelper().initDeleteContactAndWait();
        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(lastIdx);
        Assert.assertEquals(after, before);
    }
}