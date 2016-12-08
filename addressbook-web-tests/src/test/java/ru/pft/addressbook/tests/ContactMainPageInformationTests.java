package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactMainPageInformationTests extends TestBase {

    @Test
    public void contactPhonesTest() {
        app.goTo().contactsPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(cleaned(mergePhones(contactInfoEditForm))));
    }

    @Test
    public void contactEmailsTest() {
        app.goTo().contactsPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoEditForm)));
    }

    @Test
    public void contactAddressTest() {
        app.goTo().contactsPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoEditForm.getAddress()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.isEmpty())
                .map(ContactMainPageInformationTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.isEmpty())
                .map(ContactMainPageInformationTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
