package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Set<ContactData> before = app.contact().all();
        app.goTo().contactPage();
        ContactData contact = new ContactData().withFirstName("TestName").withMiddleName("TestMiddleName")
                .withLastName("TestLastName").withNickname("TestNickname").withTitle("Mr").withCompany("TestCompany")
                .withAddress("TestAddress1").withPhone("+380634759784").withBirthDate("10").withBirthMonth("January")
                .withBirthYear("1988");

        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}