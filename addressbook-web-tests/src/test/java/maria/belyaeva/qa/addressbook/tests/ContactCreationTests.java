package maria.belyaeva.qa.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import maria.belyaeva.qa.addressbook.model.ContactData;
import maria.belyaeva.qa.addressbook.model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((c)-> new Object[] {c}).collect(Collectors.toList()).iterator();
    }


    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        Contacts before = app.contact().all();
        app.contact().initiateContactCreation();
        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)->(c.getId())).max().getAsInt()))));
    }
}
