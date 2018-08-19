package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        if(!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Maria", "Olegovna", "Belyaeva", "maria_belyaeva", "Burning Buttons", "89237600746", "maria.belyaeva.qa@gmail.com", "test1"));
        }
        app.getNavigationHelper().returnToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillInContactForm(new ContactData("Maria", "Olegovna", "Belyaeva", "maria_belyaeva", "Burning Buttons", "89237600746", "maria.belyaeva.qa@gmail.com", null), false);
        app.getContactHelper().saveContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}
