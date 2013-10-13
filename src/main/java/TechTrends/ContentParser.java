package TechTrends;

import javax.inject.Inject;

public class ContentParser {
    private final String url;
    private final DocumentAnalyzer documentAnalyzer;
    private final DocumentParser documentParser;
    private ParsedDocument parsedDocument;

    @Inject
    public ContentParser(String url, DocumentParser documentParser, DocumentAnalyzer documentAnalyzer) {
        this.url = url;
        this.documentParser = new DocumentParser(url);
        this.documentAnalyzer = new DocumentAnalyzer();
    }

    public AnalyzedDocument getTechnologyKeywords() {
        if (parsedDocument == null) {
            startParsing();
        }

        AnalyzedDocument technologyKeyWords = documentAnalyzer.analyze(parsedDocument, AnalysisType.TECHNOLOGY_KEYWORDS);
        return technologyKeyWords;
    }

    private void startParsing() {
        DocumentParser documentParser = new DocumentParser(url);
        parsedDocument = documentParser.parse(url);
    }
}
