package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() {
        int countBefore = app.getContactHelper().checkContactCount();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        int countAfter = app.getContactHelper().checkContactCount();
        Assert.assertTrue(countAfter < countBefore);
    }
}
