package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {
        @Test
        public void testGroupCreation() {
            app.goToGroupPage();
            app.initGroupCreation();
            app.fillGroupForm(new GroupData("Test group", "Test header", "Test footer"));
            app.submitGroupCreation();
            app.returnToGroupPage();
        }
}
