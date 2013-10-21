package TechTrends;

public interface DocumentAnalyzerFactory {
    DocumentAnalyzer getAnalyzerOfType(AnalysisType analysisType, ParsedDocument parsedDocument);
}
