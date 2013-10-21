package TechTrends;

import lombok.Value;

import java.util.Date;

@Value
public class AnalyzedDocument {
    FrequencyDistribution frequencyDistribution;
    Date dateOfAnalysis;
    Date dateOfPosting;
}
