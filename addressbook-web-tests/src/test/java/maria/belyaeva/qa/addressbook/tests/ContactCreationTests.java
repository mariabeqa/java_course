package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import maria.belyaeva.qa.addressbook.model.Contacts;
import org.testng.annotations.Test;


import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @Test()
    public void testContactCreation() {
        Contacts before = app.contact().all();
        app.contact().initiateContactCreation();
        File photo = new File("src/test/resources/pic.jpg");
        ContactData contact = new ContactData().withFirstName("New").withLastName("New").withGroup("test1").withPhoto(photo);
        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)->(c.getId())).max().getAsInt()))));
    }
}
