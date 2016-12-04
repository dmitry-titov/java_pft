package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void groupModificationTest() {
        app.getNavigationHelper().gotoGroupsPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test_group1", "test_group2", "test_group3"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int lastIdx = before.size() - 1;
        app.getGroupHelper().selectGroup(lastIdx);
        app.getGroupHelper().initGroupModification();
        GroupData group = new GroupData(before.get(lastIdx).getId(), "modify_group1", "modify_group2", "modify_group3");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModify();
        app.getGroupHelper().returnToGroupsPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();

        before.remove(lastIdx);
        before.add(group);
        before.sort((g1, g2) -> Integer.compare(g1.getId(), g2.getId()));
        after.sort((g1, g2) -> Integer.compare(g1.getId(), g2.getId()));
        Assert.assertEquals(after, before);
    }
}
