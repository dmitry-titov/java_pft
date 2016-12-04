package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void contactModificationThroughDetailsTest() {
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
        app.getContactHelper().viewDetailsContact();
        app.getContactHelper().modifyContactInDetailsPage();
        app.getContactHelper().fillContactForm(
                new ContactData("modifyName",
                        "modifyMiddleName",
                        "modifyLastName",
                        "modifyNickname",
                        "89211111234",
                        "modify@test.com",
                        "11",
                        "May",
                        "1988", null), false);
        app.getContactHelper().submitContactUpdate();
        app.getContactHelper().returnToContactsPage();
    }

    @Test
    public void contactModificationThroughEditButtonTest() {
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
        app.getContactHelper().fillContactForm(
                new ContactData("modifyName",
                        "modifyMiddleName",
                        "modifyLastName",
                        "modifyNickname",
                        "89211111234",
                        "modify@test.com",
                        "10",
                        "May",
                        "1988", null), false);
        app.getContactHelper().submitContactUpdate();
        app.getContactHelper().returnToContactsPage();
    }
}
