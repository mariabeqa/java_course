package maria.belyaeva.qa.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration() {
        app.registration().start("user2", "user2@localhost.localdomain");
    }
}
