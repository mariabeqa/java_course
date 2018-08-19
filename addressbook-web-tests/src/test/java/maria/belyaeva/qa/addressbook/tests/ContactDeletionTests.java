package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() {
        if(!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Maria", "Olegovna", "Belyaeva", "maria_belyaeva", "Burning Buttons", "89237600746", "maria.belyaeva.qa@gmail.com", "test1"));
        }
        app.getNavigationHelper().returnToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactDeletion();
        app.getContactHelper().confirmContactDeletion();
        app.getNavigationHelper().returnToHomePage();
    }
}
