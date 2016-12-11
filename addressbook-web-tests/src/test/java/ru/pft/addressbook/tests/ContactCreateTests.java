package ru.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreateTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupsPage();
            app.group().create(new GroupData()
                    .withName("test_group1").withHeader("test_group2").withFooter("test_group3"));
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void contactCreateTestsFromJson(ContactData contact) {
        Contacts before = app.db().contacts();
        app.goTo().newContactForm();
        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
        verifyContactListInUI();
    }

    @Test(dataProvider = "validContactsFromXml")
    public void contactCreateTestsFromXml(ContactData contact) {
        Contacts before = app.db().contacts();
        app.goTo().newContactForm();
        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
        verifyContactListInUI();
    }

    @Test
    public void contactCreateTests() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
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
                .withByear("1977")
                .inGroup(groups.iterator().next());
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(contacts.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(contacts
                .withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
        verifyContactListInUI();
    }
}
