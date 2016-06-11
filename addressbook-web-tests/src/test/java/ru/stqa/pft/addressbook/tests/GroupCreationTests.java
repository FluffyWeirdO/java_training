package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        int countBefore = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("TestGroup", "TestHeader", "TestFooter"));
        int countAfter = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(countAfter, countBefore + 1);
    }
}
