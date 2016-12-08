package ru.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeleteTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupsPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData()
                    .withName("test_group1").withHeader("test_group2").withFooter("test_group3"));
        }
    }

    @Test
    public void groupDeleteTests() {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
