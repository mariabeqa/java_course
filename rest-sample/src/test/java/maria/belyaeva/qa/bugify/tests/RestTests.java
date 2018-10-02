package maria.belyaeva.qa.bugify.tests;

import maria.belyaeva.qa.bugify.model.Issue;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{
    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(341);
        Set<Issue> oldIssues = app.rest().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue three").withDescription("Test Description three");
        int issueId = app.rest().createIssue(newIssue);
        Set<Issue> newIssues = app.rest().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
