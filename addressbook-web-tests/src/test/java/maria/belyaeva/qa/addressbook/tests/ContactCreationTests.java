package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        Set<ContactData> before = app.contact().all();
        app.contact().initiateContactCreation();
        ContactData contact = new ContactData().withFirstName("New").withLastName("New").withGroup("test1");
        app.contact().create(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(before.size() + 1, after.size());

        contact.withId(after.stream().mapToInt((c)->(c.getId())).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
