package ru.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactDetailsInformationTests extends TestBase {
//
//    @BeforeMethod
//    public void ensurePreconditions() {
//        app.goTo().newContactForm();
//        ContactData contact = new ContactData()
//                .withFirstName("testNewName")
//                .withLastName("testNewLastName")
//                .withHome("8495004422")
//                .withMobile("+79775681010")
//                .withWork("99003245")
//                .withEmail("test@newtest.com")
//                .withEmail2("test2@newtest.com")
//                .withEmail3("test3@newtest.com")
//                .withAddress("CityFoo, st. Street, b. 12, a.59");
//        app.contact().create(contact);
//
//    }

    @Test
    public void contactDetailsInfoTest() {
        app.goTo().contactsPage();
        ContactData contact = app.contact().all().stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get();
        ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

        app.contact().viewDetailsById(contact.getId());
        ContactData contactDetails = app.contact().details(contact);

        assertThat(contactDetails.getAddress(), equalTo(contactInfoEditForm.getAddress()));
        assertThat(contactDetails.getAllPhones(), equalTo(contactInfoEditForm.mergePhones()));
        assertThat(contactDetails.getAllEmails(), equalTo(contactInfoEditForm.mergeEmails()));
        assertThat(contactDetails.getFullName(), equalTo(contactInfoEditForm.mergeNames()));
    }
}
