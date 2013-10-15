package TechTrends;

import javax.inject.Inject;

public class ContentParser {
    private final DocumentAnalyzerFactory documentAnalyzerFactory;
    private final DocumentParser documentParser;
    private ParsedDocument parsedDocument;

    @Inject
    public ContentParser(DocumentParser documentParser, DocumentAnalyzerFactory documentAnalyzerFactory) {
        this.documentParser = documentParser;
        this.documentAnalyzerFactory = documentAnalyzerFactory;
    }

    public AnalyzedDocument analyzeForTechnologyKeywords() {
        if (parsedDocument == null) {
            startParsing();
        }

        DocumentAnalyzer documentAnalyzer = documentAnalyzerFactory.getAnalyzerOfType(AnalysisType.TECHNOLOGY_KEYWORDS);
        AnalyzedDocument technologyKeyWords = documentAnalyzer.analyze(parsedDocument);
        return technologyKeyWords;
    }

    private void startParsing() {
        parsedDocument = documentParser.parse();
    }
}
