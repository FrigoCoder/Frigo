
package frigo.math;

import static frigo.math.Statistics.average;
import static frigo.math.Statistics.variance;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StatisticsTest {

    private double[] linear = {1, 2, 3, 4, 5, 6};
    private double[] quadratic = {1, 4, 9, 16, 25, 36, 49};

    @Test
    public void average_of_linear_array () {
        assertThat(average(linear), is(21.0 / 6));
    }

    @Test
    public void average_of_quadratic_array () {
        assertThat(average(quadratic), is(140.0 / 7));
    }

    @Test
    public void variance_of_linear_array () {
        assertThat(variance(linear), is(17.5 / 6));

    }

    @Test
    public void variance_of_quadratic_array () {
        assertThat(variance(quadratic), is(1_876.0 / 7));
    }

}
