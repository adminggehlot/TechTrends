package TechTrends;

import lombok.NonNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TechnologyKeywordsAnalyzer implements DocumentAnalyzer {
    private static final String[] TECHNOLOGY_KEYWORDS = {"java", "ruby", "python"};
    private final ParsedDocument parsedDocument;
    private AnalyzedDocument analyzedDocument;
    List<Post> postsToAnalyze;

    public TechnologyKeywordsAnalyzer(@NonNull ParsedDocument parsedDocument) {
        this.parsedDocument = parsedDocument;
        this.postsToAnalyze = parsedDocument.fetchTopLevelPosts();
    }

    @Override
    public AnalyzedDocument prepareAnalysis() {
        if (analyzedDocument != null) {
            return analyzedDocument;
        }
        FrequencyDistribution frequencyDistribution = new FrequencyDistribution();
        for (Post post : postsToAnalyze) {
            FrequencyDistribution frequencyDistributionInPost = findTechnologyKeywordsInPost(post);
            frequencyDistribution = frequencyDistribution.merge(frequencyDistributionInPost);
        }

        analyzedDocument = new AnalyzedDocument(frequencyDistribution, new Date(), new Date());
        return analyzedDocument;
    }

    private FrequencyDistribution findTechnologyKeywordsInPost(Post post) {
        String body = post.getBody();
        return extractTechnologyKeywordsInBody(body);
    }

    private FrequencyDistribution extractTechnologyKeywordsInBody(String body) {
        FrequencyDistribution frequencyDistribution = new FrequencyDistribution();
        for (String word : body.split("\\s+")) {
            if (isATechnologyKeyWord(word)) {
                long newCount = findNewCount(frequencyDistribution, word);
                frequencyDistribution.put(word, new AtomicLong(newCount));
            }
        }
        return frequencyDistribution;
    }

    private long findNewCount(FrequencyDistribution frequencyDistribution, String word) {
        frequencyDistribution.putIfAbsent(word, new AtomicLong(0));
        return frequencyDistribution.get(word).incrementAndGet();
    }


    private boolean isATechnologyKeyWord(String word) {
        if (Arrays.asList(TECHNOLOGY_KEYWORDS).contains(word.toLowerCase())) {
            return true;
        }
        return false;
    }
}
