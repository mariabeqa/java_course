package maria.belyaeva.qa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void login(String username, String pw) {
        type(By.name("user"),username);
        type(By.name("pass"), pw);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
