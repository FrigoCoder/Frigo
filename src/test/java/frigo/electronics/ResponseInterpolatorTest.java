
package frigo.electronics;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ResponseInterpolatorTest {

    private ResponseInterpolator interpolator = new ResponseInterpolator("response.txt");

    @Test
    public void returns_lowest_for_lower_frequencies () {
        assertThat(interpolator.response(-10), is(0.0));
    }

    @Test
    public void returns_highest_for_higher_frequencies () {
        assertThat(interpolator.response(48000), is(65.455));
    }

    @Test
    public void returns_exact_value_at_exact_sample () {
        assertThat(interpolator.response(0.732), is(107.2688));
        assertThat(interpolator.response(22000.488), is(76.1700));
    }

    @Test
    public void returns_interpolated_value_between_samples () {
        assertThat(interpolator.response(1.0), closeTo(113.1113, 1E-4));
        assertThat(interpolator.response(22000.8545), closeTo(74.6222, 1E-4));
    }
}