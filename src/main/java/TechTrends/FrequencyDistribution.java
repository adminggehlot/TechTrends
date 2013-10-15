package TechTrends;

import lombok.Delegate;
import lombok.NonNull;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class FrequencyDistribution {
    public interface SimpleConcurrentMap {
        AtomicLong put(String key, AtomicLong value);
        AtomicLong get(String key);
        AtomicLong putIfAbsent(String key, AtomicLong value);
        boolean isEmpty();
        Set<String> keySet();
        boolean containsKey(String key);
    }

    @Delegate(types = SimpleConcurrentMap.class)
    private final ConcurrentMap<String, AtomicLong> frequencyDistribution;

    public FrequencyDistribution() {
        frequencyDistribution = new ConcurrentHashMap<>();
    }


    public FrequencyDistribution merge(@NonNull final FrequencyDistribution frequencyDistributionInPost) {
        FrequencyDistribution mergedFrequencyDistribution = new FrequencyDistribution();

        for (String key : frequencyDistribution.keySet()) {
            mergedFrequencyDistribution.put(key, frequencyDistribution.get(key));
        }

        for (String key : frequencyDistributionInPost.keySet()) {
            if (mergedFrequencyDistribution.containsKey(key)) {
                mergedFrequencyDistribution.put(key, addValues(mergedFrequencyDistribution.get(key),
                        frequencyDistributionInPost.get(key)));
            } else {
                mergedFrequencyDistribution.put(key, frequencyDistributionInPost.get(key));
            }
        }
        return mergedFrequencyDistribution;
    }

    private AtomicLong addValues(AtomicLong atomicLong1, AtomicLong atomicLong2) {
        return new AtomicLong(atomicLong1.longValue() + atomicLong2.longValue());
    }
}
