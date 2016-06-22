package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.contact().list();
        app.goTo().contactPage();
        ContactData contact = new ContactData().withFirstName("TestName").withMiddleName("TestMiddleName")
                .withLastName("TestLastName").withNickname("TestNickname").withTitle("Mr").withCompany("TestCompany")
                .withAddress("TestAddress1").withPhone("+380634759784").withBirthDate("10").withBirthMonth("January")
                .withBirthYear("1988");

        app.contact().create(contact);

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }
}