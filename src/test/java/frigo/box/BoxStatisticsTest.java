
package frigo.box;

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
        assertThat(integral.getVarianceInside(box), closeTo(1.25, EPSILON));
    }

    @Test
    public void test_getVarianceOutside () {
        assertThat(integral.getVarianceOutside(box), closeTo(9.387755102040813, EPSILON));
    }

    @Test
    public void test_getWeightedVarianceInside () {
        assertThat(integral.getWeightedVarianceInside(box), closeTo(5.0, EPSILON));
    }

    @Test
    public void test_getWeightedVarianceOutside () {
        assertThat(integral.getWeightedVarianceOutside(box), closeTo(65.7142857142857, EPSILON));
    }

    @Test
    public void test_getWeightedVarianceSum () {
        assertThat(integral.getWeightedVarianceSum(box), closeTo(70.7142857142857, EPSILON));
    }

}
