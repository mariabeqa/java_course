package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {
        @Test
        public void testGroupCreation() {
            app.goToGroupPage();
            app.getGroupHelper().initGroupCreation();
            app.getGroupHelper().fillGroupForm(new GroupData("Test group", "Test header", "Test footer"));
            app.getGroupHelper().submitGroupCreation();
            app.getGroupHelper().returnToGroupPage();
        }
}
