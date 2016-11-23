package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void contactModificationThroughDetailsTest() {
        app.getNavigationHelper().gotoContactsPage();
        app.getContactHelper().viewDetailsContact();
        app.getContactHelper().modifyContactInDetailsPage();
        app.getContactHelper().fillContactForm(
                new ContactData("modifyName",
                        "modifyMiddleName",
                        "modifyLastName",
                        "modifyNickname",
                        "89211111234",
                        "modify@test.com",
                        "11",
                        "May",
                        "1988", null), false);
        app.getContactHelper().submitContactUpdate();
        app.getContactHelper().returnToContactsPage();
    }

    @Test
    public void contactModificationThroughEditButtonTest() {
        app.getNavigationHelper().gotoContactsPage();
        app.getContactHelper().modifyContact();
        app.getContactHelper().fillContactForm(
                new ContactData("modifyName",
                        "modifyMiddleName",
                        "modifyLastName",
                        "modifyNickname",
                        "89211111234",
                        "modify@test.com",
                        "10",
                        "May",
                        "1988", null), false);
        app.getContactHelper().submitContactUpdate();
        app.getContactHelper().returnToContactsPage();
    }
}
