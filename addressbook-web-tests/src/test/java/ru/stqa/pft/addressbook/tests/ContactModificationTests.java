package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("UpdatedFirst", "UpdatedMiddle", "UpdatedLast",
                "UpdatedNick", "UpdatedTitle", "UpdatedCompany", "UpdatedAddress", "UpdatedPhone", "28", "May", "2016",
                null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
