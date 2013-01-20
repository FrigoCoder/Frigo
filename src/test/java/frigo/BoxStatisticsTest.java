
package frigo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BoxStatisticsTest {

    private Box box;
    private double epsilon;
    private BoxStatistics integral;
    private BoxStatistics naive;
    private double[] source;

    @Before
    public void setUp () {
        box = new Box(1, 4);
        epsilon = 1.0e-10;
        source = getTestArray();
        integral = new BoxStatisticsByIntegralArray(source);
        naive = new BoxStatisticsNaive(source);
    }

    @Test
    public void testGetVariance () {
        assertEquals(integral.getVariance(box), naive.getVariance(box), epsilon);
    }

    @Test
    public void testGetVarianceOutside () {
        assertEquals(integral.getVarianceOutside(box), naive.getVarianceOutside(box), epsilon);
    }

    @Test
    public void testGetWeightedVariance () {
        assertEquals(integral.getWeightedVariance(box), naive.getWeightedVariance(box), epsilon);
    }

    @Test
    public void testGetWeightedVarianceOutside () {
        assertEquals(integral.getWeightedVarianceOutside(box), naive.getWeightedVarianceOutside(box), epsilon);
    }

    private double[] getTestArray () {
        double[] result = new double[10];
        for( int i = 0; i < result.length; i++ ){
            result[i] = i;
        }
        return result;
    }
}
