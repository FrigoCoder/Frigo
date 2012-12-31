
package frigo.math;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SimpleGaussianFitterTest {

    final double epsilon = 1.0e-15;
    final GaussianFitter fitter = new SimpleGaussianFitter();
    final double[] v = {1, 2, 3, 4, 5, 6};
    final double[] w = {1, 2, 3, 4, 5, 6, 7, 8};

    @Test
    public void testGetExpectedValue () {
        assertEquals(3.5, fitter.getExpectedValue(v), epsilon);
        assertEquals(4.5, fitter.getExpectedValue(w), epsilon);
    }

    @Test
    public void testGetStandardDeviation () {
        assertEquals(Math.sqrt(17.5 / 6.0), fitter.getStandardDeviation(v), epsilon);
        assertEquals(Math.sqrt(42.0 / 8.0), fitter.getStandardDeviation(w), epsilon);
    }

    @Test
    public void testGetVariance () {
        assertEquals(17.5 / 6.0, fitter.getVariance(v), epsilon);
        assertEquals(42.0 / 8.0, fitter.getVariance(w), epsilon);
    }
}
