package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

public class ContactCreateTests extends TestBase {

    @Test
    public void contactCreateTests() {
        app.getNavigationHelper().gotoNewContactForm();
        app.getContactHelper().fillContactForm(new ContactData("testName", "testMiddleName", "testLastName", "testNickname", "89285681010", "test@test.com", "7", "April", "1977"));
        app.getContactHelper().submitContactForm();
        app.getContactHelper().returnToContactsPage();
    }
}
