package TechTrends;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ExtractSinglePostEndToEndTest {
    private String url = "";
    private ContentParser contentParser;
    private DocumentParser documentParser;
    private DefaultDocumentAnalyzer documentAnalyzer;

    @Before
    public void setup() {
        documentParser = new DefaultDocumentParser(url);
        documentAnalyzer = new DefaultDocumentAnalyzer();
    }
    @Test
    public void extractSinglePostWithValidURL() {
        String url = "https://news.ycombinator.com/item?id=6475879";
        contentParser = new ContentParser(url, documentParser, documentAnalyzer);
        AnalyzedDocument technologyKeywords = contentParser.getTechnologyKeywords();
        assertThat(technologyKeywords, is(notNullValue()));
    }
}
