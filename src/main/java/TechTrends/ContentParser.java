package TechTrends;

import java.util.HashMap;
import java.util.Map;

public class ContentParser {
    private final String url;
    private boolean parseCompleted;
    private Map<String,Integer> technologyKeyWords;

    public ContentParser(String url) {
        this.url = url;
    }

    public Map<String, Integer> getTechnologyKeywords() {
        if (!parseCompleted) {
            startParsing();
        }
        return technologyKeyWords;
    }

    private void startParsing() {
        this.technologyKeyWords = new HashMap<>();
    }
}
