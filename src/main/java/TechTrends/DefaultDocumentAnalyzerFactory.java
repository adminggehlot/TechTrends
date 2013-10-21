package TechTrends;

public class DefaultDocumentAnalyzerFactory implements DocumentAnalyzerFactory {

    @Override
    public DocumentAnalyzer getAnalyzerOfType(AnalysisType analysisType, ParsedDocument parsedDocument) {
        if (analysisType == AnalysisType.TECHNOLOGY_KEYWORDS) {
            return new TechnologyKeywordsAnalyzer(parsedDocument);
        }
        throw new IllegalArgumentException("Unknown analysis type: " + analysisType);
    }
}
