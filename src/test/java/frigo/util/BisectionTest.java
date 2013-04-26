
package frigo.util;

import static frigo.util.Bisection.bisect;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.base.Function;

public class BisectionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void bisect_finds_root_of_constant_zero_function () {
        Function<Double, Double> f = new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return 0.0;
            }
        };
        double arg = bisect(-1, 1, f);
        assertThat(arg, is(0.0));
    }

    @Test
    public void bisect_finds_root_of_linear_function () {
        Function<Double, Double> f = new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return 2 * x + 1;
            }

        };
        double arg = bisect(-2, +2, f);
        assertThat(arg, is(-0.5));
    }

    @Test
    public void bisect_finds_root_of_half_of_quadratic_function () {
        Function<Double, Double> f = new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return x * x - 4;
            }

        };
        double arg = bisect(0, 3, f);
        assertThat(arg, is(2.0));
    }

    @Test
    public void bisect_finds_root_of_negative_slope_function () {
        Function<Double, Double> f = new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return 4 - x * x;
            }

        };
        double arg = bisect(0, 3, f);
        assertThat(arg, is(2.0));
    }

    @Test
    public void bisect_finds_left_boundary_value () {
        Function<Double, Double> f = new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return x == 0.0 ? 0.0 : 1.0;
            }

        };
        double arg = bisect(0, 1, f);
        assertThat(arg, is(0.0));
    }

    @Test
    public void bisect_finds_right_boundary_value () {
        Function<Double, Double> f = new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return x == 1.0 ? 0.0 : 1.0;
            }

        };
        double arg = bisect(0, 1, f);
        assertThat(arg, is(1.0));
    }

}
