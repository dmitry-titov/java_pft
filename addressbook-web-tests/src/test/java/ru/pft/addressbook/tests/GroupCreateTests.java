package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

public class GroupCreateTests extends TestBase {

    @Test
    public void groupCreateTest() {
        app.getNavigationHelper().gotoGroupsPage();
        app.getGroupHelper().createGroup(new GroupData("test_group1", "test_group2", "test_group3"));
    }

}
