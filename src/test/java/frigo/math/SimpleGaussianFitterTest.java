
package frigo.math;

import static java.lang.Math.sqrt;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SimpleGaussianFitterTest {

    private static final double epsilon = 1.0e-15;
    private static final double[] v = {1, 2, 3, 4, 5, 6};
    private static final double[] w = {1, 2, 3, 4, 5, 6, 7, 8};

    private GaussianFitter fitter = new SimpleGaussianFitter();

    @Test
    public void testGetExpectedValue () {
        assertThat(fitter.getExpectedValue(v), closeTo(3.5, epsilon));
        assertThat(fitter.getExpectedValue(w), closeTo(4.5, epsilon));
    }

    @Test
    public void testGetStandardDeviation () {
        assertThat(fitter.getStandardDeviation(v), closeTo(sqrt(17.5 / 6.0), epsilon));
        assertThat(fitter.getStandardDeviation(w), closeTo(sqrt(42.0 / 8.0), epsilon));
    }

    @Test
    public void testGetVariance () {
        assertThat(fitter.getVariance(v), closeTo(17.5 / 6.0, epsilon));
        assertThat(fitter.getVariance(w), closeTo(42.0 / 8.0, epsilon));
    }
}
