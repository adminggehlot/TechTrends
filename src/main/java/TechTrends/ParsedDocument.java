package TechTrends;

import org.jsoup.nodes.Document;

import javax.inject.Inject;

public class ParsedDocument {
    private final Document document;

    @Inject
    public ParsedDocument(Document document) {
        this.document = document;
    }
}
