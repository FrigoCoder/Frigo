
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
        assertEquals(integral.getAverageInside(box1), 3.0);
        assertEquals(integral.getAverageInside(box2), 4.5);
    }

    @Test
    public void test_getAverageOutside () {
        assertEquals(integral.getAverageOutside(box1), 6.0);
        assertEquals(integral.getAverageOutside(box2), 0.0);
    }

    @Test
    public void test_getSumInside () {
        assertEquals(integral.getSumInside(box1), 15.0);
        assertEquals(integral.getSumInside(box2), 45.0);
    }

    @Test
    public void test_getSumOutside () {
        assertEquals(integral.getSumOutside(box1), 30.0);
        assertEquals(integral.getSumOutside(box2), 0.0);
    }

    private void assertEquals (double actual, double expected) {
        assertThat(actual, closeTo(expected, epsilon));
    }

}
