package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Set;

public class GroupCreationTests extends TestBase {
        @Test
        public void testGroupCreation() {
            app.goTo().groupPage();
            Set<GroupData> before = app.group().all();
            GroupData group = new GroupData().withName("test2").withFooter("footer").withHeader("header");
            app.group().create(group);
            Set<GroupData> after = app.group().all();
            Assert.assertEquals(after.size(), before.size() + 1);
//            int max1 = after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId();
//            group.setId(max1);
            group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
            before.add(group);
//            Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
//            before.sort(byId);
//            after.sort(byId);
            Assert.assertEquals(before, after);
        }
}
