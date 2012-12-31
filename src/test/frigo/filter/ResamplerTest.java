
package frigo.filter;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ResamplerTest {

    private Resampler resampler = new Resampler(new Linear());
    private double[] source = {1, 4, 9, 16, 25, 36};

    @Test
    public void resample_same_size () {
        assertThat(resampler.resample(source, source.length), equalTo(source));
    }

    @Test
    public void resample_half_size () {
        double[] expected = {3.4285714285714284, 13.0, 28.428571428571427};
        assertThat(resampler.resample(source, expected.length), equalTo(expected));

    }

    @Test
    public void resample_double_size () {
        double[] expected = {1.0, 1.75, 3.25, 5.25, 7.75, 10.75, 14.25, 18.25, 22.75, 27.75, 33.25, 36.0};
        assertThat(resampler.resample(source, expected.length), equalTo(expected));
    }
}
