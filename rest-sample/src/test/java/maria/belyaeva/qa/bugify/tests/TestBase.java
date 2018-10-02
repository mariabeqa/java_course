package maria.belyaeva.qa.bugify.tests;

import maria.belyaeva.qa.bugify.helpers.ApplicationManager;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import java.io.IOException;

public class TestBase {
    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeClass
    public void setUp() throws IOException {
        app.init();
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        boolean isOpen = true;
        String status = app.rest().getIssueStatus(issueId);
        if(status.equals("resolved")) {
            isOpen = false;
        }
        return isOpen;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
