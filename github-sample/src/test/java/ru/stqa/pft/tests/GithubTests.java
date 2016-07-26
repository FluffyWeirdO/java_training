package ru.stqa.pft.tests;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("1c7d1dabddd0279482c81306425d35b4aee4236e");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("FluffyWeirdO", "java_training")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
