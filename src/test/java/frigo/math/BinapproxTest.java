
package frigo.math;

import static frigo.math.Binapprox.binapprox;
import static frigo.math.Statistics.standardDeviation;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BinapproxTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test_empty_array () {
        double[] v = {};
        thrown.expect(IllegalArgumentException.class);
        binapprox(v);
    }

    @Test
    public void test_constant () {
        double[] v = {1, 1, 1, 1, 1, 1};
        assertThat(binapprox(v), is(1.0));
    }

    @Test
    public void test_linear_even () {
        double[] v = {1, 2, 3, 4, 5, 6};
        assertThat(binapprox(v), is(3.5));
    }

    @Test
    public void test_linear_odd () {
        double[] v = {1, 2, 3, 4, 5, 6, 7};
        assertMedianClose(v, 4, 1000);
    }

    @Test
    public void test_quadratic_even () {
        double[] v = {1, 4, 9, 16, 25, 36};
        assertMedianClose(v, 12.5, 1000);
    }

    @Test
    public void test_quadratic_odd () {
        double[] v = {1, 4, 9, 16, 25, 36, 49};
        assertMedianClose(v, 16, 1000);
    }

    private void assertMedianClose (double[] v, double expected, int bins) {
        assertThat(binapprox(v), closeTo(expected, standardDeviation(v) / bins));
    }

}
