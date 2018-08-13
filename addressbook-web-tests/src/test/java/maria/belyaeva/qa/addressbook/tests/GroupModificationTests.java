package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("edited", "edited", "edited"));
        app.getGroupHelper().saveGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }

}
