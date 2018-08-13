package maria.belyaeva.qa.addressbook.tests;

import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().saveGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }

}
