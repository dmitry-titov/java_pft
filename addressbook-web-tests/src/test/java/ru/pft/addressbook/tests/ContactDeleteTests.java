package ru.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTests extends TestBase {

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
            app.contact().create(contact);
        }
    }

    @Test
    public void contactDeleteThroughContactsListTest() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

    @Test
    public void contactDeleteThroughContactFormTest() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().modifyContactById(deletedContact.getId());
        app.contact().initDeleteAndWait();
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}