package maria.belyaeva.qa.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().editFieldInContactForm(By.name("lastname"), "Murashkina");
        app.getContactHelper().saveContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}
