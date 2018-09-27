package maria.belyaeva.qa.mantis.tests;

import maria.belyaeva.qa.model.UserData;
import maria.belyaeva.qa.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class PasswordResetTests extends TestBase {

    @BeforeMethod
    public void startMailServer() throws IOException, InterruptedException {
        app.mail().start();
    }

    @Test
    public void testPasswordReset() throws InterruptedException, IOException {
        long now = System.currentTimeMillis();
        String newPassword = String.valueOf(now);

        app.session().loginAsAdministrator();
        app.goTo().manageUsers();
        Users users = app.db().users();

        UserData user = users.iterator().next();
        UserData selectedUser = new UserData().withId(user.getId()).withEmail(user.getEmail()).withUsername(user.getEmail());

        app.user().resetPassword(String.valueOf(selectedUser.getId()));

        app.user().confirmPasswordInEmail(selectedUser.getEmail(), newPassword);

        assertTrue(app.newSession().login(selectedUser.getUsername(), newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}


