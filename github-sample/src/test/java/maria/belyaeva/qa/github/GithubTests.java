package maria.belyaeva.qa.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("70e69b20f79fa2a1686b4c5b82fd18bbfdf8c63d");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("mariabeqa", "java_course")).commits();
        for(RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
