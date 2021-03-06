package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends BaseHelper {

    private Groups groupCache = null;

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupForm() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initCreateGroup() {
        click(By.name("new"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) {
        click(findElement(By.cssSelector("input[value ='" + id + "']")));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModify() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initCreateGroup();
        fillGroupForm(group);
        submitGroupForm();
        groupCache = null;
        returnToGroupsPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModify();
        groupCache = null;
        returnToGroupsPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
        groupCache = null;
        returnToGroupsPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return findElements(By.cssSelector("span.group")).size();
    }

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(element.getText()));
        }
        return new Groups(groupCache);
    }
}
