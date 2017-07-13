
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
        Function<Double, Double> f = x -> 0.0;
        double arg = bisect(f, -1, 1);
        double value = f.apply(arg);
        assertThat(value, is(0.0));
    }

    @Test
    public void bisect_finds_root_of_linear_function () {
        assertArgumentAndValue(x -> 2 * x + 1, -2, 2, -0.5, 0);
    }

    @Test
    public void bisect_finds_root_of_half_of_quadratic_function () {
        assertArgumentAndValue(x -> x * x - 4, 0, 3, 2, 0);
    }

    @Test
    public void bisect_finds_root_of_negative_slope_function () {
        assertArgumentAndValue(x -> 4 - x * x, 0, 3, 2, 0);
    }

    @Test
    public void bisect_finds_left_boundary_value_in_increasing_function () {
        assertArgumentAndValue(x -> x == 0 ? 0.0 : 1.0, 0, 1, 0, 0);
    }

    @Test
    public void bisect_finds_left_boundary_value_in_decreasing_function () {
        assertArgumentAndValue(x -> x == 0 ? 0.0 : -1.0, 0, 1, 0, 0);
    }

    @Test
    public void bisect_finds_right_boundary_value_in_increasing_function () {
        assertArgumentAndValue(x -> x != 1.0 ? -1.0 : 0.0, 0, 1, 1, 0);
    }

    @Test
    public void bisect_finds_right_boundary_value_in_decreasing_function () {
        assertArgumentAndValue(x -> x != 1.0 ? 1.0 : 0.0, 0, 1, 1, 0);
    }

    private void assertArgumentAndValue (Function<Double, Double> function, double left, double right,
        double expectedArgument, double expectedValue) {
        double argument = bisect(function, left, right);
        double value = function.apply(argument);
        assertThat("Argument is different from expected", argument, is(expectedArgument));
        assertThat("Value is different from expected", value, is(expectedValue));
    }

}
