package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        int countBefore = app.getContactHelper().getContactCount();
        app.getNavigationHelper().gotoContactCreationPage();
        app.getContactHelper().createContact(new ContactData("TestName", "TestMiddleName", "TestLastName",
                "TestNickname", "Mr", "TestCompany", "TestAddress1", "+380634759784", "10", "January", "1988",
                "TestGroup"));
        int countAfter = app.getContactHelper().getContactCount();
        Assert.assertEquals(countAfter, countBefore + 1);
    }
}
