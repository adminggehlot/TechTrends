package TechTrends;

public class DefaultDocumentAnalyzerFactory implements DocumentAnalyzerFactory {

    @Override
    public DocumentAnalyzer getAnalyzerOfType(AnalysisType analysisType) {
        if (analysisType == AnalysisType.TECHNOLOGY_KEYWORDS) {
            return new TechnologyKeywordsAnalyzer();
        }
        throw new IllegalArgumentException("Unknown analysis type: " + analysisType);
    }
}
