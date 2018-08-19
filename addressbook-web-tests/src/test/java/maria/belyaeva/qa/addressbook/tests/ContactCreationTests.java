package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillInContactForm(new ContactData("Maria", "Olegovna", "Belyaeva", "maria_belyaeva", "Burning Buttons", "89237600746", "maria.belyaeva.qa@gmail.com", "test1"), true);
        app.getContactHelper().submitContactInfo();
        app.getNavigationHelper().returnToHomePage();
    }
}
