package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() {
        app.addNewContact();
        app.fillInContactForm(new ContactData("Maria", "Olegovna", "Belyaeva", "maria_belyaeva", "Burning Buttons", "89237600746", "maria.belyaeva.qa@gmail.com"));
        app.submitContactInfo();
        app.returnToHomePage();
    }
}