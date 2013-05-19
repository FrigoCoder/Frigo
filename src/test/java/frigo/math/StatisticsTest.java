
package frigo.math;

import static frigo.math.Statistics.average;
import static frigo.math.Statistics.euclideanDistance;
import static frigo.math.Statistics.manhattanDistance;
import static frigo.math.Statistics.norm;
import static frigo.math.Statistics.variance;
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

}
