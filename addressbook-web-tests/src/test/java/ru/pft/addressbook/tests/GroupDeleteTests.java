package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTests extends TestBase {

    @Test
    public void groupDeleteTests() {
        app.getNavigationHelper().gotoGroupsPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test_group1", "test_group2", "test_group3"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int lastIdx = before.size() - 1;
        app.getGroupHelper().selectGroup(lastIdx);
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupsPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        before.remove(lastIdx);
        Assert.assertEquals(after, before);
    }
}
