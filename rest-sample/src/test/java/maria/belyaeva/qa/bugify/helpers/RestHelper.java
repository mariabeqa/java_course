package maria.belyaeva.qa.bugify.helpers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import maria.belyaeva.qa.bugify.model.Issue;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.util.Set;

public class RestHelper {
    private final ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get(app.getProperty("basic.url") + "/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public Executor getExecutor() {
        return Executor.newInstance().auth(app.getProperty("api.key"), "");
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post(app.getProperty("basic.url") + "/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public String getIssueStatus(int issueId) throws IOException {
        String id = String.valueOf(issueId);
        String json = getExecutor().execute(Request.Get(app.getProperty("basic.url") + "/issues/" + id +".json"))
                .returnContent().asString();

        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement issue = issues.getAsJsonArray().get(0);
        JsonElement parsedIssue = new JsonParser().parse(String.valueOf(issue.getAsJsonObject()));
        JsonElement status = parsedIssue.getAsJsonObject().get("state_name");

        return status.toString();
    }
}
