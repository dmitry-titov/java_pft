package ru.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase {

    @Test
    public void contactDeleteThroughContactsListTest() {
        app.getNavigationHelper().gotoContactsPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initDeleteContact();
        app.getContactHelper().confirmDeleteContact();
    }

    @Test
    public void contactDeleteThroughContactFormTest() {
        app.getNavigationHelper().gotoContactsPage();
        app.getContactHelper().modifyContact();
        app.getContactHelper().initDeleteContact();
    }
}
