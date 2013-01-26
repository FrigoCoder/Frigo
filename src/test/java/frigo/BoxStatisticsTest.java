
package frigo;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BoxStatisticsTest {

    private static final double EPSILON = 1e-13;

    private Box box = new Box(1, 4);
    private double[] source = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private BoxStatistics integral = new BoxStatistics(source);

    @Test
    public void test_getVarianceInside () {
        assertEquals(integral.getVarianceInside(box), 1.25);
    }

    @Test
    public void test_getVarianceOutside () {
        assertEquals(integral.getVarianceOutside(box), 9.387755102040813);
    }

    @Test
    public void test_getWeightedVarianceInside () {
        assertEquals(integral.getWeightedVarianceInside(box), 5.0);
    }

    @Test
    public void test_getWeightedVarianceOutside () {
        assertEquals(integral.getWeightedVarianceOutside(box), 65.7142857142857);
    }

    @Test
    public void test_getWeightedVarianceSum () {
        assertEquals(integral.getWeightedVarianceSum(box), 70.7142857142857);
    }

    private void assertEquals (double actual, double expected) {
        assertThat(actual, closeTo(expected, EPSILON));
    }

}
