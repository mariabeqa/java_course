package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initiateContactCreation();
        ContactData contact = new ContactData("New", "New", "edited");
        app.getContactHelper().fillInContactForm(contact, true);
        app.getContactHelper().submitContactInfo();
        app.goTo().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size() + 1, after.size());

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
