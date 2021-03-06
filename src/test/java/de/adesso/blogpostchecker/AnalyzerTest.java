package de.adesso.blogpostchecker;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.util.FileSystemUtils;

import java.io.File;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnalyzerTest extends BaseTest {

    @BeforeAll
    public void setup() {
        repoCloner.cloneRemoteRepo();
    }

    @AfterAll
    public void cleanUp() {
        FileSystemUtils.deleteRecursively(new File(configService.getLOCAL_REPO_PATH()));
    }

    @Test
    public void getPostMetadataFromGitShouldPass() {
        PostMetadata metadata = new PostMetadata();
        metadata.setAuthor("dariobraun");
        metadata.setCategories("Test");
        metadata.setDate("2020-04-01 13:00");
        metadata.setLayout("post, post-xml");
        metadata.setTags("Tag1, Tag2, Tag3");
        metadata.setTitle("sdfs dasd 23 dsadas & fdsf");
        Assertions.assertThat(fileAnalyzer.getMetadata().equals(metadata));
    }

    @Test
    public void getAuthorFromGitShouldPass() {
        Author author = new Author();
        author.setAuthorNickname("dariobraun");
        author.setAvatarUrl("/assets/images/avatars/dariobraun.jpg");
        author.setBio("Dario Braun ist Werkstudent bei adesso in Dortmund und im Open-Source-Bereich tätig.");
        author.setEmail("dario.braun@adesso.de");
        author.setFirstName("Dario");
        author.setGithub("https://github.com/dariobraun");
        author.setGithubUsername("dariobraun");
        author.setLastName("Braun");
        Assertions.assertThat(fileAnalyzer.getAuthor().equals(author));
    }
}
