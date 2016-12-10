package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactMainPageInformationTests extends TestBase {

    @Test
    public void contactPhonesTest() {
        app.goTo().contactsPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(contactInfoEditForm.mergePhones()));
    }

    @Test
    public void contactEmailsTest() {
        app.goTo().contactsPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(contactInfoEditForm.mergeEmails()));
    }

    @Test
    public void contactAddressTest() {
        app.goTo().contactsPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoEditForm.getAddress()));
    }
}
