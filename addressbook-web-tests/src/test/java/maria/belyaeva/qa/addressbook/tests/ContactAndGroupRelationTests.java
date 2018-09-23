package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.ContactData;
import maria.belyaeva.qa.addressbook.model.Contacts;
import maria.belyaeva.qa.addressbook.model.GroupData;
import maria.belyaeva.qa.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAndGroupRelationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactData().withFirstName("New").withLastName("New").withAddress("New")
                    .withHomePhone("99-99-99").withMobilePhone("78987877878").withWorkPhone("55-55-44")
                    .withEmail("newemail@test.com").withEmail2("newemail2@gmail.com").withEmail3("newemail3@gmail.com"));
        }

        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("groupToAddTo").withFooter("footer").withHeader("header"));
        }
    }

    @Test
    public void testAddingContactToGroup() {
        Contacts contactsBefore = app.db().contacts();
        Groups groups = app.db().groups();

        //выбранный контакт
        ContactData contactToSelect  = contactsBefore.iterator().next();

        ContactData contact = new ContactData().withFirstName(contactToSelect.getFirstName()).withLastName(contactToSelect.getLastName()).withId(contactToSelect.getId())
                .withAddress(contactToSelect.getAddress()).withHomePhone(contactToSelect.getHomePhone()).withMobilePhone(contactToSelect.getMobilePhone()).withWorkPhone(contactToSelect.getWorkPhone())
                .withEmail(contactToSelect.getEmail()).withEmail2(contactToSelect.getEmail1()).withEmail3(contactToSelect.getEmail2());


        //выбранная группа
        GroupData groupToAdd = groups.iterator().next();
        //группы контакта
        Groups contactGroupsBefore = contactToSelect.getGroups();

        //есть ли выбранная группа среди групп контакта
        boolean alreadyAdded = false;

        if(!(contactGroupsBefore.size()==0)) {
            for(GroupData g : contactGroupsBefore) {
                if((g.getId()) == (groupToAdd.getId())) {
                    alreadyAdded = true;
                    break;
                }
            }
        }

        //создаем новую группу и добавляем в нее контакт если выбранная группа уже есть в списке групп контакта
        if(alreadyAdded) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("groupToAddTo").withFooter("footer").withHeader("header"));
            GroupData newGroup = new GroupData().withId(app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt())
                    .withName("groupToAddTo").withFooter("footer").withHeader("header");
            System.out.println("New group " + newGroup);
            app.goTo().homePage();
            app.contact().addToGroup(contact, newGroup);
            groupToAdd = newGroup;
        } else {
            //добавить контакт в выбранную группу, т.к. выбранной группы нет в списке уже добавленных групп
            app.goTo().homePage();
            app.contact().addToGroup(contact, groupToAdd);
        }

        Contacts contactsAfter = app.db().contacts();
        ContactData selectedContact  = contactsAfter.iterator().next();
        Groups contactGroupsAfter = selectedContact.getGroups();

        assertThat(contactGroupsAfter, CoreMatchers.equalTo(contactGroupsBefore.withAdded(groupToAdd)));
    }

    @Test
    public void testRemovingContactFromGroup() {
        Contacts contactsBefore = app.db().contacts();

        //выбранный контакт
        ContactData contactToSelect  = contactsBefore.iterator().next();

        ContactData contact = new ContactData().withFirstName(contactToSelect.getFirstName()).withLastName(contactToSelect.getLastName()).withId(contactToSelect.getId())
                .withAddress(contactToSelect.getAddress()).withHomePhone(contactToSelect.getHomePhone()).withMobilePhone(contactToSelect.getMobilePhone()).withWorkPhone(contactToSelect.getWorkPhone())
                .withEmail(contactToSelect.getEmail()).withEmail2(contactToSelect.getEmail1()).withEmail3(contactToSelect.getEmail2());


        Groups contactGroupsBefore = contactToSelect.getGroups();
        //выбираем одну из групп контакта
        GroupData groupToSelect = contactGroupsBefore.iterator().next();

        if(contactGroupsBefore.size() != 0) {
            app.group().selectGroupOnHomePage(groupToSelect);
            app.contact().removeContact(contactToSelect);

        } else {
            //добавить контакт в группу если у контакта нет ни одной группы
            Groups groups = app.db().groups();
            GroupData groupToAdd = groups.iterator().next();

            app.goTo().homePage();
            app.contact().addToGroup(contact, groupToAdd);
            groupToSelect = groupToAdd;
            app.group().selectGroupOnHomePage(groupToSelect);
            app.contact().removeContact(contactToSelect);

        }

        Contacts contactsAfter = app.db().contacts();
        ContactData selectedContact  = contactsAfter.iterator().next();
        Groups contactGroupsAfter = selectedContact.getGroups();

        assertThat(contactGroupsAfter, CoreMatchers.equalTo(contactGroupsBefore.withoutAdded(groupToSelect)));
    }
}
