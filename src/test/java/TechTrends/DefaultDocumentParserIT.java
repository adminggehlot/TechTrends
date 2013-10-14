package TechTrends;

import org.junit.Test;

import java.net.UnknownHostException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class DefaultDocumentParserIT {
    @Test
    public void testTitleMatchForAValidUrl() {
        String url = "http://www.yahoo.com";
        DocumentParser documentParser = new DefaultDocumentParser(url);
        ParsedDocument parsedDocument = documentParser.parse();
        assertThat(parsedDocument, is(notNullValue()));
        assertThat(parsedDocument.getTitle(), equalTo("Yahoo"));
    }

    @Test (expected = DocumentParseException.class)
    public void testNonExistingUrl() {
        String url = "http://www.non-existing-url.in";
        DocumentParser documentParser = new DefaultDocumentParser(url);
        ParsedDocument parsedDocument = documentParser.parse();
    }
}
