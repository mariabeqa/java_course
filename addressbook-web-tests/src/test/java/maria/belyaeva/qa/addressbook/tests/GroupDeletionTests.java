package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if(app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().deleteById(deletedGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
//        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
