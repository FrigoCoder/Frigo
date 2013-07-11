
package frigo.math;

import static frigo.math.Statistics.average;
import static frigo.math.Statistics.euclideanDistance;
import static frigo.math.Statistics.manhattanDistance;
import static frigo.math.Statistics.max;
import static frigo.math.Statistics.maxAbs;
import static frigo.math.Statistics.min;
import static frigo.math.Statistics.minAbs;
import static frigo.math.Statistics.norm;
import static frigo.math.Statistics.standardDeviation;
import static frigo.math.Statistics.sum;
import static frigo.math.Statistics.variance;
import static java.lang.Math.sqrt;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StatisticsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private double[] linear = {1, 2, 3, 4, 5, 6};
    private double[] quadratic = {1, 4, 9, 16, 25, 36, 49};
    private double[] other = {1, 3, 5, 7, 9, 11};

    @Test
    public void test_average () {
        assertThat(average(linear), is(21.0 / 6));
        assertThat(average(quadratic), is(140.0 / 7));
    }

    @Test
    public void test_sum () {
        assertThat(sum(linear), is(21.0));
        assertThat(sum(quadratic), is(140.0));
    }

    @Test
    public void test_variance () {
        assertThat(variance(linear), is(17.5 / 6));
        assertThat(variance(quadratic), is(1_876.0 / 7));
    }

    @Test
    public void test_standard_deviation () {
        assertThat(standardDeviation(linear), is(sqrt(17.5 / 6)));
        assertThat(standardDeviation(quadratic), is(sqrt(1_876.0 / 7)));
    }

    @Test
    public void manhattanDistance_of_two_arrays () {
        assertThat(manhattanDistance(linear, other), is(15.0));
    }

    @Test
    public void manhattanDistance_should_throw_on_length_mismatch () {
        thrown.expect(IllegalArgumentException.class);
        manhattanDistance(linear, new double[] {});
    }

    @Test
    public void euclideanDistance_of_two_arrays () {
        assertThat(euclideanDistance(linear, other), is(7.4161984870956629487113974408007));
    }

    @Test
    public void euclideanDistance_should_throw_on_length_mismatch () {
        thrown.expect(IllegalArgumentException.class);
        euclideanDistance(linear, new double[] {});
    }

    @Test
    public void norm_harmonic_norm () {
        assertThat(norm(linear, -1), is(0.4081632653061225));
    }

    @Test
    public void norm_manhattan_norm () {
        assertThat(norm(linear, 1), is(21.0));
    }

    @Test
    public void norm_euclidean_norm () {
        assertThat(norm(linear, 2), is(9.5393920141694564915262158602323));
    }

    @Test
    public void test_max () {
        assertThat(max(linear), is(6.0));
        assertThat(max(quadratic), is(49.0));
    }

    @Test
    public void test_maxAbs () {
        assertThat(maxAbs(linear), is(6.0));
        assertThat(maxAbs(quadratic), is(49.0));
        double[] arrayWithNegativeMaxAbs = {-10, 1};
        assertThat(maxAbs(arrayWithNegativeMaxAbs), is(10.0));
    }

    @Test
    public void test_min () {
        assertThat(min(linear), is(1.0));
        assertThat(min(quadratic), is(1.0));
    }

    @Test
    public void test_minAbs () {
        assertThat(minAbs(linear), is(1.0));
        assertThat(minAbs(quadratic), is(1.0));
        double[] arrayWithNegativeMinAbs = {-1, 10};
        assertThat(minAbs(arrayWithNegativeMinAbs), is(1.0));
    }

}
