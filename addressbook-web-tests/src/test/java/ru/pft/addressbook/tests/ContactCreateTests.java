package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreateTests extends TestBase {

    @Test
    public void contactCreateTests() {
        List<ContactData> before = app.getContactHelper().getContactList();
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

        List<ContactData> after = app.getContactHelper().getContactList();
        before.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
        after.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
        before.add(contact);
        Assert.assertEquals(after, before);
    }
}
