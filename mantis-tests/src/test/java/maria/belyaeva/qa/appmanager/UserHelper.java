package maria.belyaeva.qa.appmanager;

import maria.belyaeva.qa.model.MailMessage;
import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassword(String userId) {
        click(By.cssSelector("a[href$='id=" + userId + "']"));
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void confirmPasswordInEmail(String email, String password) throws InterruptedException {
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        System.out.println("confirmation link " + confirmationLink);

        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//span[contains(text(),'Update User')]"));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        System.out.println("mailMessage " + mailMessage.text);
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
