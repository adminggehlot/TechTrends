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
        this.documentParser = documentParser;
        this.documentAnalyzer = documentAnalyzer;
    }

    public AnalyzedDocument getTechnologyKeywords() {
        if (parsedDocument == null) {
            startParsing();
        }

        AnalyzedDocument technologyKeyWords = documentAnalyzer.analyze(parsedDocument, AnalysisType.TECHNOLOGY_KEYWORDS);
        return technologyKeyWords;
    }

    private void startParsing() {
        parsedDocument = documentParser.parse(url);
    }
}
