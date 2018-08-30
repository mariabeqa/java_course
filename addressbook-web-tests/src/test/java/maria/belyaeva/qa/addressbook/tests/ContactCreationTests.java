package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillInContactForm(new ContactData("Maria",  "Belyaeva", null), true);
        app.getContactHelper().submitContactInfo();
        app.getNavigationHelper().returnToHomePage();
    }
}
