package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        app.goTo().contactPage();
        ContactData contact = new ContactData().withFirstName("TestName").withMiddleName("TestMiddleName")
                .withLastName("TestLastName").withNickname("TestNickname").withTitle("Mr").withCompany("TestCompany")
                .withAddress("TestAddress1").withPhone("+380634759784").withBirthDate("10").withBirthMonth("January")
                .withBirthYear("1988");

        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}