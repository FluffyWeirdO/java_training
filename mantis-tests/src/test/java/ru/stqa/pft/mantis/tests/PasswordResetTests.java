package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordResetTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordReset() throws IOException, MessagingException {
        String adminUsername = app.getProperty(("web.adminLogin"));
        String adminPassword = app.getProperty("web.adminPassword");

        UserData user = app.db().users().iterator().next();
        String username = user.getUsername();
        String newPassword = "UpdatedPassword";
        String email = user.getEmail();
        System.out.println("User info: username = " + username + ", email = " + email);
        System.out.println("UserData: " + user);

        app.mantis().logIn(adminUsername, adminPassword);
        app.mantis().resetUserPassword(username);

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String resetPasswordURL = app.mail().findURL(mailMessages, email);
        System.out.println("Reset password URL: " + resetPasswordURL);

        app.mantis().setNewPassword(resetPasswordURL, newPassword);

        HttpSession session = app.newSession();
        assertTrue(session.logIn(username, newPassword));
        assertTrue(session.isLoggedInAs(username));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}