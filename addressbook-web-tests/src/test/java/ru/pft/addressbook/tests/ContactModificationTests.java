package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import java.util.List;

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
        List<ContactData> before = app.getContactHelper().getContactList();
        int lastIdx = before.size() - 1;
        app.getContactHelper().viewDetailsContact(lastIdx);
        app.getContactHelper().modifyContactInDetailsPage();
        ContactData contact = new ContactData(before.get(lastIdx).getId(),
                "modifyName",
                "modifyMiddleName",
                "modifyLastName",
                "modifyNickname",
                "89211111234",
                "modify@test.com",
                "11",
                "May",
                "1988", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactUpdate();
        app.getContactHelper().returnToContactsPage();

        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(lastIdx);
        before.add(contact);
        before.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
        after.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
        Assert.assertEquals(after, before);
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

        List<ContactData> before = app.getContactHelper().getContactList();
        int firstIdx = 0;

        app.getContactHelper().modifyContact(firstIdx);
        ContactData contact = new ContactData(before.get(firstIdx).getId(),
                "modifyName",
                "modifyMiddleName",
                "modifyLastName",
                "modifyNickname",
                "89211111234",
                "modify@test.com",
                "11",
                "May",
                "1988", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactUpdate();
        app.getContactHelper().returnToContactsPage();

        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(firstIdx);
        before.add(contact);
        before.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
        after.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
        Assert.assertEquals(after, before);
    }
}
