package TechTrends;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class FrequencyDistributionTest {

    @Test
    public void testMergeWithTwoDuplicateKeys() throws Exception {
        FrequencyDistribution frequencyDistribution1 = new FrequencyDistribution();
        frequencyDistribution1.put("A", new AtomicLong(1));
        frequencyDistribution1.put("B", new AtomicLong(1));

        FrequencyDistribution frequencyDistribution2 = new FrequencyDistribution();
        frequencyDistribution2.put("A", new AtomicLong(1));

        FrequencyDistribution frequencyDistribution = frequencyDistribution1.merge(frequencyDistribution2);

        assertThat(frequencyDistribution.get("A").longValue(), equalTo(2L));
        assertThat(frequencyDistribution.get("B").longValue(), equalTo(1L));
    }

    @Test
    public void testMergeWithSecondObjectEmpty() {
        FrequencyDistribution frequencyDistribution1 = new FrequencyDistribution();
        frequencyDistribution1.put("A", new AtomicLong(1));
        frequencyDistribution1.put("B", new AtomicLong(1));

        FrequencyDistribution frequencyDistribution2 = new FrequencyDistribution();

        FrequencyDistribution frequencyDistribution = frequencyDistribution1.merge(frequencyDistribution2);

        assertThat(frequencyDistribution.get("A").longValue(), equalTo(1L));
        assertThat(frequencyDistribution.get("B").longValue(), equalTo(1L));
    }

    @Test
    public void testMergeWithFirstObjectEmpty() {
        FrequencyDistribution frequencyDistribution1 = new FrequencyDistribution();

        FrequencyDistribution frequencyDistribution2 = new FrequencyDistribution();
        frequencyDistribution2.put("A", new AtomicLong(1));
        frequencyDistribution2.put("B", new AtomicLong(1));

        FrequencyDistribution frequencyDistribution = frequencyDistribution1.merge(frequencyDistribution2);

        assertThat(frequencyDistribution.get("A").longValue(), equalTo(1L));
        assertThat(frequencyDistribution.get("B").longValue(), equalTo(1L));
    }
}
