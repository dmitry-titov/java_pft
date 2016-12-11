package ru.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;
import ru.pft.addressbook.model.Contacts;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ContactInGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData contactModel = new ContactData()
                .withFirstName("testName")
                .withMiddleName("testMiddleName")
                .withLastName("testLastName")
                .withNickname("testNickname")
                .withMobile("89285681010")
                .withEmail("test@test.com")
                .withBday("7")
                .withBmonth("April")
                .withByear("1977");

        if (groups.size() == 0) {
            app.goTo().groupsPage();
            app.group().create(new GroupData()
                    .withName("test_group1").withHeader("test_group2").withFooter("test_group3"));
        }
        if (contacts.size() == 0) {
            app.goTo().newContactForm();
            app.contact().create(contactModel);
        }

        for (GroupData group : groups) {
            for (ContactData contact : contacts) {
                if (!group.getContacts().contains(contact)) {
                    return;
                }
            }
        }
        app.goTo().newContactForm();
        app.contact().create(contactModel);
    }

    @Test
    public void addContactToGroupTest() {
        Groups groupsBefore = app.db().groups();
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            for (GroupData group : groupsBefore) {
                if (!group.getContacts().contains(contact)) {
                    app.contact().addInGroup(contact, group);
                    Groups groupsAfter = app.db().groups();
                    GroupData groupAfter = groupsAfter.stream().filter((g) -> g.getId() == group.getId()).iterator().next();
                    assertThat(groupAfter.getContacts(), hasItem(contact));
                    return;
                }
            }
        }
    }

    @Test(dependsOnMethods = "addContactToGroupTest")
    public void removeContactFromGroupTest() {
        Groups groupsBefore = app.db().groups();
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            for (GroupData group : groupsBefore) {
                if (group.getContacts().contains(contact)) {
                    app.contact().removeFromGroup(contact, group);
                    Groups groupsAfter = app.db().groups();
                    GroupData groupAfter = groupsAfter.stream().filter((g) -> g.getId() == group.getId()).iterator().next();
                    assertThat(groupAfter.getContacts(), not(hasItem(contact)));
                    return;
                }
            }
        }
    }
}
