package TechTrends;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TechnologyKeywordsAnalyzerTest {

    private DocumentAnalyzer documentAnalyzer;
    private static Post POST_WITH_JAVA_KEYWORD;
    private static Post POST_WITH_JAVA_AND_RUBY_KEYWORD;
    private static Post POST_WITH_NO_KEYWORDS;

    @Before
    public void setup() {
        documentAnalyzer = new DefaultDocumentAnalyzerFactory().getAnalyzerOfType(AnalysisType.TECHNOLOGY_KEYWORDS);
        POST_WITH_JAVA_KEYWORD = new Post.PostBuilder().withAuthor("author").withBody("I really like java").withDate(new Date()).build();
        POST_WITH_JAVA_AND_RUBY_KEYWORD = new Post.PostBuilder().withAuthor("author").withBody("I really love ruby and java").withDate(new Date()).build();
        POST_WITH_NO_KEYWORDS = new Post.PostBuilder().withAuthor("author").withBody("I really like  blue sky").withDate(new Date()).build();

    }

    @Test(expected = NullPointerException.class)
    public void testAnalyzeWithNullDocument() throws Exception {
        documentAnalyzer.analyze(null);
    }

    @Test
    public void testParsedDocumentWithNoPosts() {
        ParsedDocument parsedDocument = mock(ParsedDocument.class);
        List<Post> topLevelPosts = new ArrayList<>();
        when(parsedDocument.fetchTopLevelPosts()).thenReturn(topLevelPosts);
        documentAnalyzer.analyze(parsedDocument);
        assertThat(documentAnalyzer.getKeywordsWithFrequency().isEmpty(), is(true));
    }

    @Test
    public void testParsedDocumentWithOnePost() {
        ParsedDocument parsedDocument = mock(ParsedDocument.class);
        List<Post> topLevelPosts = new ArrayList<>();
        topLevelPosts.add(POST_WITH_JAVA_KEYWORD);
        when(parsedDocument.fetchTopLevelPosts()).thenReturn(topLevelPosts);
        documentAnalyzer.analyze(parsedDocument);
        assertThat(documentAnalyzer.getKeywordsWithFrequency().keySet().size(), equalTo(1));
        assertThat(documentAnalyzer.getKeywordsWithFrequency().get("java").longValue(), equalTo(1L));
    }

    @Test
    public void testParsedDocumentWithTwoPosts() {
        ParsedDocument parsedDocument = mock(ParsedDocument.class);
        List<Post> topLevelPosts = new ArrayList<>();
        topLevelPosts.add(POST_WITH_JAVA_KEYWORD);
        topLevelPosts.add(POST_WITH_JAVA_AND_RUBY_KEYWORD);

        when(parsedDocument.fetchTopLevelPosts()).thenReturn(topLevelPosts);
        documentAnalyzer.analyze(parsedDocument);
        assertThat(documentAnalyzer.getKeywordsWithFrequency().keySet().size(), equalTo(2));
        assertThat(documentAnalyzer.getKeywordsWithFrequency().get("java").longValue(), equalTo(2L));
        assertThat(documentAnalyzer.getKeywordsWithFrequency().get("ruby").longValue(), equalTo(1L));
    }

    @Test
    public void testParsedDocumentWithNoTechKeywords() {
        ParsedDocument parsedDocument = mock(ParsedDocument.class);
        List<Post> topLevelPosts = new ArrayList<>();
        topLevelPosts.add(POST_WITH_NO_KEYWORDS);

        when(parsedDocument.fetchTopLevelPosts()).thenReturn(topLevelPosts);
        documentAnalyzer.analyze(parsedDocument);
        assertThat(documentAnalyzer.getKeywordsWithFrequency().keySet().size(), equalTo(0));
    }
}
