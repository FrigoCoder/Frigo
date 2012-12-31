
package frigo;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class IntegralArrayTest {

    private double epsilon;
    private IntegralArray integral;
    private double[] source;

    @Before
    public void setUp () {
        epsilon = 1.0e-20;
        source = getTestArray();
        integral = new IntegralArray(source);
    }

    @Test
    public void testGetAverage () {
        assertEquals(4.5, integral.getAverage(new Box(0, 9)), epsilon);
        assertEquals(3.0, integral.getAverage(new Box(1, 5)), epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAverageException1 () {
        integral.getAverage(new Box(-2, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAverageException2 () {
        integral.getAverage(new Box(-1, -2));
    }

    @Test
    public void testGetAverageOutside () {
        assertEquals(0.0, integral.getAverageOutside(new Box(0, 9)), epsilon);
        assertEquals(6.0, integral.getAverageOutside(new Box(1, 5)), epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAverageOutsideException1 () {
        integral.getAverageOutside(new Box(-2, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAverageOutsideException2 () {
        integral.getAverageOutside(new Box(-1, -2));
    }

    @Test
    public void testGetSum () {
        assertEquals(45.0, integral.getSum(new Box(0, 9)), epsilon);
        assertEquals(15.0, integral.getSum(new Box(1, 5)), epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSumException1 () {
        integral.getSum(new Box(-2, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSumException2 () {
        integral.getSum(new Box(-1, -2));
    }

    @Test
    public void testGetSumOutside () {
        assertEquals(0.0, integral.getSumOutside(new Box(0, 9)), epsilon);
        assertEquals(30.0, integral.getSumOutside(new Box(1, 5)), epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSumOutsideException1 () {
        integral.getSumOutside(new Box(-2, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSumOutsideException2 () {
        integral.getSumOutside(new Box(-1, -2));
    }

    private double[] getTestArray () {
        double[] result = new double[10];
        for( int i = 0; i < result.length; i++ ){
            result[i] = i;
        }
        return result;
    }
}
