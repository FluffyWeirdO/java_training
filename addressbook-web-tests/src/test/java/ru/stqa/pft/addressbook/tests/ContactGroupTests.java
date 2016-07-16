package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupTests extends TestBase {
    private ContactData targetContact;
    private GroupData targetGroup;

    @BeforeClass
    private void ensurePreconditions() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

        for (ContactData contact : contacts) {
            for (GroupData group : groups) {
                //If group with such name is present at least 2 times, go to next one
                if (groups.stream()
                        .filter(g -> g.getGroupName().equals(group.getGroupName()))
                        .collect(Collectors.toList()).size() > 1) {
                    continue;
                }
                //If contact is not assigned to this group, this pair can be used
                if (!contact.getGroups().contains(group)) {
                    targetContact = contact;
                    targetGroup = group;
                    return;
                }
            }
        }
        //If no pair is found, then create a new one
        targetContact = new ContactData().withFirstName("TestFirstName").withLastName("TestLastName");
        targetGroup = new GroupData().withName(String.format("Test Group (%s)", System.currentTimeMillis()));

        app.goTo().contactPage();
        app.contact().create(targetContact);
        app.goTo().groupPage();
        app.group().create(targetGroup);

        //Get fresh data from DB
        contacts = app.db().contacts();
        groups = app.db().groups();

        targetContact.withId(contacts.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        targetGroup.withId(groups.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    }

    @Test
    public void testContactAdditionToGroup() {
        app.goTo().homePage();
        Groups before = targetContact.getGroups();
        app.contact().addToGroup(targetContact, targetGroup);

        ContactData contactFromDb = app.db().getContact(targetContact.getId());
        assertThat(contactFromDb.getGroups(), hasItem(targetGroup));
        assertThat(contactFromDb.getGroups(), equalTo(before.withAdded(targetGroup)));

    }

    @Test(dependsOnMethods = "testContactAdditionToGroup")
    public void testContactRemovalFromGroup() {
        app.goTo().homePage();
        Groups before = targetContact.getGroups();
        app.contact().removeFromGroup(targetContact, targetGroup);

        ContactData contactFromDb = app.db().getContact(targetContact.getId());
        assertThat(contactFromDb.getGroups(), not(hasItem(targetGroup)));
        assertThat(contactFromDb.getGroups(), equalTo(before.without(targetGroup)));
    }
}
