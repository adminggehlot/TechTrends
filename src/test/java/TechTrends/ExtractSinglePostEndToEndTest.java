package TechTrends;

import org.junit.Test;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Map;

public class ExtractSinglePostEndToEndTest {
    private ContentParser contentParser;
    @Test
    public void extractSinglePostWithValidURL() {
        String url = "https://news.ycombinator.com/item?id=6475879";
        contentParser = new ContentParser(url);
        Map<String, Integer> technologyKeywords = contentParser.getTechnologyKeywords();
        assertThat(technologyKeywords, is(notNullValue()));
        assertThat(technologyKeywords.isEmpty(), is(equalTo(false)));
    }
}
