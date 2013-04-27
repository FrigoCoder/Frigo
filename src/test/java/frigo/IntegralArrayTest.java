
package frigo;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IntegralArrayTest {

    private static final double epsilon = 1.0e-20;

    private double[] source = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private IntegralArray integral = new IntegralArray(source);
    private Box box1 = new Box(1, 5);
    private Box box2 = new Box(0, 9);

    @Test
    public void test_getAverageInside () {
        assertThat(integral.getAverageInside(box1), closeTo(3.0, epsilon));
        assertThat(integral.getAverageInside(box2), closeTo(4.5, epsilon));
    }

    @Test
    public void test_getAverageOutside () {
        assertThat(integral.getAverageOutside(box1), closeTo(6.0, epsilon));
        assertThat(integral.getAverageOutside(box2), closeTo(0.0, epsilon));
    }

    @Test
    public void test_getSumInside () {
        assertThat(integral.getSumInside(box1), closeTo(15.0, epsilon));
        assertThat(integral.getSumInside(box2), closeTo(45.0, epsilon));
    }

    @Test
    public void test_getSumOutside () {
        assertThat(integral.getSumOutside(box1), closeTo(30.0, epsilon));
        assertThat(integral.getSumOutside(box2), closeTo(0.0, epsilon));
    }

}
