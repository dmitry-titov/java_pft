package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

public class ContactDeleteTests extends TestBase {

    @Test
    public void contactDeleteThroughContactsListTest() {
        app.getNavigationHelper().gotoContactsPage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoNewContactForm();
            app.getContactHelper().createContact(new ContactData(
                    "testName",
                    "testMiddleName",
                    "testLastName",
                    "testNickname",
                    "89285681010",
                    "test@test.com",
                    "7",
                    "April",
                    "1977", null), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initDeleteContact();
        app.getContactHelper().confirmDeleteContact();
    }

    @Test
    public void contactDeleteThroughContactFormTest() {
        app.getNavigationHelper().gotoContactsPage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoNewContactForm();
            app.getContactHelper().createContact(new ContactData(
                    "testName",
                    "testMiddleName",
                    "testLastName",
                    "testNickname",
                    "89285681010",
                    "test@test.com",
                    "7",
                    "April",
                    "1977", null), true);
        }
        app.getContactHelper().modifyContact();
        app.getContactHelper().initDeleteContactAndWait();
    }
}
