package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.GroupData;
import maria.belyaeva.qa.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if(app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1").withFooter("footer").withHeader("header"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group);
        Groups after = app.group().all();
        assertEquals(before.size(), after.size());

//        Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
        assertThat(after, equalTo(before.withoutAdded(modifiedGroup).withAdded(group)));
    }
}
