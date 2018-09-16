package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import maria.belyaeva.qa.addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactData().withFirstName("New").withLastName("New").withAddress("New")
                    .withHomePhone("99-99-99").withMobilePhone("78987877878").withWorkPhone("55-55-44")
                    .withEmail("newemail@test.com").withEmail2("newemail2@gmail.com").withEmail3("newemail3@gmail.com"));
        }
    }

    @Test
    public void testContactModification() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData modifiedContact  = before.iterator().next();
        ContactData contact = new ContactData().withFirstName("New").withLastName("New").withId(modifiedContact.getId())
                .withAddress("New").withHomePhone("99-99-99").withMobilePhone("78987877878").withWorkPhone("55-55-44")
                .withEmail("newemail@test.com").withEmail2("newemail2@gmail.com").withEmail3("newemail3@gmail.com");
        app.contact().modify(contact);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertThat(after.size(), CoreMatchers.equalTo(before.size()));

        assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
