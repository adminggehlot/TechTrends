package TechTrends;

import org.junit.Test;

public class DefaultDocumentParserTest {

    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseWithNullUrl() throws Exception {
        String url = null;
        DocumentParser documentParser = new DefaultDocumentParser(url);
        documentParser.parse(url);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseWithEmptyUrl() throws Exception {
        String url = "";
        DocumentParser documentParser = new DefaultDocumentParser(url);
        documentParser.parse(url);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseWithMalformedUrl() throws Exception {
        String url = "abcd";
        DocumentParser documentParser = new DefaultDocumentParser(url);
        documentParser.parse(url);
    }




}
