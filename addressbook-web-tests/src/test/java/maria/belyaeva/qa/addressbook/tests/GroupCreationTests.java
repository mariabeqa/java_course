package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {
        @Test
        public void testGroupCreation() {
            app.getNavigationHelper().goToGroupPage();
            List<GroupData> before = app.getGroupHelper().getGroupList();
            GroupData group = new GroupData("test4", null, null);
            app.getGroupHelper().createGroup(group);
            List<GroupData> after = app.getGroupHelper().getGroupList();
            Assert.assertEquals(after.size(), before.size() + 1);

            int max=0;
            for(GroupData g : after) {
                if(group.getId()>max) {
                    max = g.getId();
                }
            }

            int max1 = after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId();
            group.setId(max1);
            before.add(group);
            Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        }
}
