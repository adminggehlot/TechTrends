package TechTrends;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class ParsedDocumentTest {
    public static final String TEST_FILE_NAME = "/sample_post_oct_13.htm";
    public File TEST_FILE;
    private ParsedDocument parsedDocument;

    @Before
    public void setup() throws Exception {
        URL url = this.getClass().getResource(TEST_FILE_NAME);
        String fileName = url.getFile();
        TEST_FILE = new File(fileName);
        Document document = Jsoup.parse(TEST_FILE, StandardCharsets.UTF_8.name());
        parsedDocument = new ParsedDocument(document);
    }

    @Test
    public void testGetTitle() throws Exception {
        assertThat(parsedDocument.getTitle(), equalTo("Ask HN: Who is hiring? (October 2013) | Hacker News"));
    }

    @Test
    public void testFetchTopLevelPosts() throws Exception {
        assertThat(parsedDocument.fetchTopLevelPosts().size(), greaterThan(0));
    }
}
