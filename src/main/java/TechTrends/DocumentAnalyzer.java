package TechTrends;

public interface DocumentAnalyzer {
    AnalyzedDocument analyze(ParsedDocument parsedDocument);

    FrequencyDistribution getKeywordsWithFrequency();
}
