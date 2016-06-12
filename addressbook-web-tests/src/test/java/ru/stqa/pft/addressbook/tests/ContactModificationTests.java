package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoContactCreationPage();
            app.getContactHelper().createContact(new ContactData("TestName", "TestMiddleName", "TestLastName",
                    "TestNickname", "Mr", "TestCompany", "TestAddress1", "+380634759784", "10", "January", "1988",
                    "TestGroup"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("UpdatedFirst", "UpdatedMiddle", "UpdatedLast",
                "UpdatedNick", "UpdatedTitle", "UpdatedCompany", "UpdatedAddress", "UpdatedPhone", "28", "May", "2016",
                null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}
