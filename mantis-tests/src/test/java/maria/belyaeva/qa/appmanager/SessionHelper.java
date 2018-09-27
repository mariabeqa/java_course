package maria.belyaeva.qa.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {
    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAsAdministrator() {

        type(By.name("username"), app.getProperty("web.adminLogin"));
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[type='submit']"));
    }
}
