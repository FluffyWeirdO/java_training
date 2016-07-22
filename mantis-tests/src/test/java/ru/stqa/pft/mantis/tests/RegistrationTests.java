package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String user = String.format("user%s", now);
        String password = "password";
        app.mantis().initiateSignUp(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationURL = app.mail().findURL(mailMessages, email);
        app.mantis().setNewPassword(confirmationURL, password);
        assertTrue(app.newSession().logIn(user, password));
    }

    //@Test
    public void testRegistrationJames() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String user = String.format("user%s", now);
        String password = "password";
        app.james().createUser(user, password);
        app.mantis().initiateSignUp(user, email);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationURL = app.mail().findURL(mailMessages, email);
        app.mantis().setNewPassword(confirmationURL, password);
        assertTrue(app.newSession().logIn(user, password));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
