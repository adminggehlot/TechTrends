package TechTrends;

import org.junit.Test;

public class DefaultDocumentParserTest {

    @Test(expected = NullPointerException.class)
    public void testParseWithNullUrl() throws Exception {
        String url = null;
        DocumentParser documentParser = new DefaultDocumentParser(url);
        documentParser.parse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseWithEmptyUrl() throws Exception {
        String url = "";
        DocumentParser documentParser = new DefaultDocumentParser(url);
        documentParser.parse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseWithMalformedUrl() throws Exception {
        String url = "abcd";
        DocumentParser documentParser = new DefaultDocumentParser(url);
        documentParser.parse();
    }




}
