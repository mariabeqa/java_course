package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        if(!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Maria", "Belyaeva", null));
        }
        app.getNavigationHelper().returnToHomePage();
        //app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillInContactForm(new ContactData("Maria", "Belyaeva", null), false);
        app.getContactHelper().saveContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}
