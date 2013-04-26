
package frigo.util;

import static frigo.util.Bisection.bisect;
import static frigo.util.Bisection.bisectDecreasing;
import static frigo.util.Bisection.bisectIncreasing;
import static java.lang.Math.PI;
import static java.lang.Math.tan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        assertArgumentAndValue(f, -1, 1, 0, 0);
    }

    @Test
    public void bisect_finds_root_of_linear_function () {
        Function<Double, Double> f = new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return 2 * x + 1;
            }

        };
        assertArgumentAndValue(f, -2, 2, -0.5, 0);
    }

    @Test
    public void bisect_finds_root_of_half_of_quadratic_function () {
        Function<Double, Double> f = new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return x * x - 4;
            }

        };
        assertArgumentAndValue(f, 0, 3, 2, 0);
    }

    @Test
    public void bisect_finds_root_of_negative_slope_function () {
        Function<Double, Double> f = new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return 4 - x * x;
            }

        };
        assertArgumentAndValue(f, 0, 3, 2, 0);
    }

    @Test
    public void bisect_finds_left_boundary_value () {
        Function<Double, Double> f = new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return x == 0 ? 0.0 : 1.0;
            }

        };
        assertArgumentAndValue(f, 0, 1, 0, 0);
    }

    @Test
    public void bisect_finds_right_boundary_value () {
        Function<Double, Double> f = new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return x == 1.0 ? 0.0 : 1.0;
            }

        };
        assertArgumentAndValue(f, 0, 1, 1, 0);
    }

    private void assertArgumentAndValue (Function<Double, Double> function, double left, double right,
        double expectedArgument, double expectedValue) {
        double argument = bisect(function, left, right);
        double value = function.apply(argument);
        assertThat(argument, is(expectedArgument));
        assertThat(value, is(expectedValue));
    }

    @Test
    public void bisectIncreasing_finds_root_without_evaluating_boundaries () {
        Function<Double, Double> f = spy(new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return tan(x);
            }

        });
        double argument = bisectIncreasing(f, -PI / 2, PI / 2);
        double value = f.apply(argument);
        assertThat(argument, is(0.0));
        assertThat(value, is(0.0));
        verify(f, times(0)).apply(-PI / 2);
        verify(f, times(0)).apply(PI / 2);
    }

    @Test
    public void bisectDecreasing_finds_root_without_evaluating_boundaries () {
        Function<Double, Double> f = spy(new Function<Double, Double>() {

            @Override
            public Double apply (Double x) {
                return 1 + 1 / x;
            }

        });
        double argument = bisectDecreasing(f, -3, 0);
        double value = f.apply(argument);
        assertThat(argument, is(-1.0));
        assertThat(value, is(0.0));
        verify(f, times(0)).apply(0.0);
    }
}
