package maria.belyaeva.qa.mantis.tests;

import maria.belyaeva.qa.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() throws IOException, InterruptedException {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws InterruptedException, IOException {
        String email = "test333test@gmail.com";
        String user = "test333test";
        String password = "password";
        app.registration().start(user, email);
        List<MailMessage> mailMessages =  app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        System.out.println("confirmation link " + confirmationLink);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m)->m.to.equals("test333test@gmail.com")).findFirst().get();
        System.out.println("mailMessage " + mailMessage.text);
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
