package maria.belyaeva.qa.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {
    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUsers() {
        click(By.xpath("//span[contains(text(),'Manage')]"));
        click(By.xpath("//a[contains(text(),'Manage Users')]"));
    }
}
