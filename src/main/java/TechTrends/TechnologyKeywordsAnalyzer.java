package TechTrends;

import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TechnologyKeywordsAnalyzer implements DocumentAnalyzer {
    private static final String [] TECHNOLOGY_KEYWORDS = {"java", "ruby", "python"};
    private FrequencyDistribution frequencyDistribution;
    List<Post> postsToAnalyze;

    @Override
    public AnalyzedDocument analyze(@NonNull ParsedDocument parsedDocument) {
        postsToAnalyze = parsedDocument.fetchTopLevelPosts();
        return null;
    }

    @Override
    public FrequencyDistribution getKeywordsWithFrequency() {
        if (frequencyDistribution != null) {
            return frequencyDistribution;
        }
        frequencyDistribution = new FrequencyDistribution();
        for (Post post: postsToAnalyze) {
            FrequencyDistribution frequencyDistributionInPost = findTechnologyKeywordsInPost(post);
            frequencyDistribution = frequencyDistribution.merge(frequencyDistributionInPost);
        }
        return frequencyDistribution;
    }

    private FrequencyDistribution findTechnologyKeywordsInPost(Post post) {
        String body = post.getBody();
        return extractTechnologyKeywordsInBody(body);
    }

    private FrequencyDistribution extractTechnologyKeywordsInBody(String body) {
        FrequencyDistribution frequencyDistribution = new FrequencyDistribution();
        for(String word: body.split("\\s+")) {
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
