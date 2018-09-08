package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if(app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withFirstName("New").withLastName("New"));
        }
    }

    @Test
    public void testContactModification() throws InterruptedException {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact  = before.iterator().next();
        ContactData contact = new ContactData().withFirstName("Test1").withLastName("Test1").withId(modifiedContact.getId());
        app.contact().modify(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
