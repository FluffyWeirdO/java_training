package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsPageTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() > 0) {
            app.contact().deleteAll();
        }
    }

    @Test
    public void testContactDetailsPage() {
        app.goTo().contactPage();
        app.contact().create(new ContactData().withFirstName("FirstNameForDetailsCheck").withLastName("LastNameForDetailsCheck")
                .withHomePhone("+1(11)").withMobilePhone("222-222").withWorkPhone("333 333").withEmail1("test1@gmail.com")
                .withEmail3("test3@gmail.com").withAddress("AddressForDetailsCheck"));
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromDetailsPage = app.contact().infoFromDetailsPage(contact);

        assertThat(mergeAllDetails(contactInfoFromEditForm), equalTo((contactInfoFromDetailsPage.getAllDetails())));
    }

    private String mergeAllDetails(ContactData contact) {
        String homePhone = "H: " + contact.getHomePhone();
        String mobilePhone = "M: " + contact.getMobilePhone();
        String workPhone = "W: " + contact.getWorkPhone();

        return Arrays.asList(contact.getFirstName() + " " + contact.getLastName(), contact.getAddress() + "\n",
                homePhone, mobilePhone, workPhone + "\n", contact.getAllEmails())
                .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }
}