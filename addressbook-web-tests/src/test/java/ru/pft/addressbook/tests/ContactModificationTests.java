package ru.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactsPage();
        if (app.contact().all().size() == 0) {
            app.goTo().newContactForm();
            ContactData contact = new ContactData()
                    .withFirstName("testName")
                    .withMiddleName("testMiddleName")
                    .withLastName("testLastName")
                    .withNickname("testNickname")
                    .withMobile("89285681010")
                    .withEmail("test@test.com")
                    .withBday("7")
                    .withBmonth("April")
                    .withByear("1977");
            app.contact().create(contact, true);
        }
    }

    @Test
    public void contactModificationThroughDetailsTest() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().viewDetailsById(modifiedContact.getId());
        app.contact().modifyOnDetailsPage();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("modifyName")
                .withMiddleName("modifyMiddleName")
                .withLastName("modifyLastName")
                .withNickname("modifyNickname")
                .withMobile("89211111234")
                .withEmail("modify@test.com")
                .withBday("11")
                .withBmonth("May")
                .withByear("1988");
        app.contact().modify(contact);

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

    @Test
    public void contactModificationThroughEditButtonTest() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().modifyContactById(modifiedContact.getId());
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("modifyName")
                .withMiddleName("modifyMiddleName")
                .withLastName("modifyLastName")
                .withNickname("modifyNickname")
                .withMobile("89211111234")
                .withEmail("modify@test.com")
                .withBday("11")
                .withBmonth("May")
                .withByear("1988");
        app.contact().modify(contact);

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
