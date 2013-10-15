package TechTrends;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ExtractSinglePostEndToEndIT {
    private String url = "";
    private ContentParser contentParser;
    private DocumentParser documentParser;
    private DocumentAnalyzerFactory documentAnalyzerFactory;

    @Before
    public void setup() {
        documentParser = new DefaultDocumentParser(url);
        documentAnalyzerFactory = new DefaultDocumentAnalyzerFactory();
    }
    @Test
    public void extractSinglePostWithValidURL() {
        String url = "https://news.ycombinator.com/item?id=6475879";
        documentParser = new DefaultDocumentParser(url);
        contentParser = new ContentParser(documentParser, documentAnalyzerFactory);
        AnalyzedDocument technologyKeywords = contentParser.analyzeForTechnologyKeywords();
        assertThat(technologyKeywords, is(notNullValue()));
    }
}
