package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if(app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withLastName("Test").withFirstName("Test"));
        }
    }

    @Test
    public void testContactDeletion() throws InterruptedException {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        System.out.println(deletedContact);
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
