package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreateTests extends TestBase {

    @Test
    public void groupCreateTest() {
        app.getNavigationHelper().gotoGroupsPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test_group1", "test_group2", "test_group3");
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        before.sort((g1, g2) -> Integer.compare(g1.getId(), g2.getId()));
        after.sort((g1, g2) -> Integer.compare(g1.getId(), g2.getId()));
        before.add(group);
        Assert.assertEquals(after, before);
    }

}
