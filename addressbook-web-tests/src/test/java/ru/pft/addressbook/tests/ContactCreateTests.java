package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreateTests extends TestBase {

    @Test
    public void contactCreateTests() {
        Contacts before = app.contact().all();
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

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}
