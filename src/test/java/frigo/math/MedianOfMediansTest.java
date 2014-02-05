
package frigo.math;

import static frigo.math.MedianOfMedians.approximateMedian;
import static frigo.math.MedianOfMedians.median;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MedianOfMediansTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void approximate_median_of_empty_array() {
        thrown.expect(IllegalArgumentException.class);
        approximateMedian(createShuffledLinearData(0));
    }

    @Test
    public void approximate_median_of_small_lists() {
        for (int n = 1; n <= 5; n++) {
            List<Double> list = createShuffledLinearData(n);
            assertThat("Failed for list of size " + n, approximateMedian(list), is(getMedian(list)));
        }
    }

    @Test
    public void median_of_empty_array() {
        thrown.expect(IllegalArgumentException.class);
        median(createShuffledLinearData(0));
    }

    @Test
    public void median_of_small_and_large_lists() {
        for (int n = 1; n <= 256; n++) {
            List<Double> list = createShuffledLinearData(n);
            assertThat("Failed for list of size " + n, median(list), is(getMedian(list)));
        }
    }

    private List<Double> createShuffledLinearData(int n) {
        List<Double> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i + 1.0);
        }
        Collections.shuffle(list);
        return list;
    }

    @Test
    public void approximate_median_of_same_element_small_lists() {
        for (int n = 1; n <= 5; n++) {
            List<Double> list = createSameElementData(n);
            assertThat("Failed for list of size " + n, approximateMedian(list), is(getMedian(list)));
        }

    }

    @Test
    public void median_of_same_element_small_and_large_lists() {
        for (int n = 1; n <= 256; n++) {
            List<Double> list = createSameElementData(n);
            assertThat("Failed for list of size " + n, median(list), is(getMedian(list)));
        }
    }

    private List<Double> createSameElementData(int n) {
        List<Double> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(0.0);
        }
        return list;
    }

    private double getMedian(List<Double> list) {
        Collections.sort(list);
        return list.get((list.size() - 1) / 2);
    }

}
