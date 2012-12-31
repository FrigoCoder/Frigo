
package frigo.projecteuler;

import static frigo.math.numbertheory.Functions.MAX_FACTORIAL_LONG_INPUT;
import static frigo.math.numbertheory.Functions.factorialLong;
import static frigo.projecteuler.Problem383.T5;
import static frigo.projecteuler.Problem383.f5;
import static frigo.projecteuler.Problem383.f5Factorial;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class Problem383Test {

    @Test
    public void testF5 () {
        assertThat(f5(0), is(0L));
        assertThat(f5(4), is(0L));
        assertThat(f5(5), is(1L));
        assertThat(f5(10), is(1L));
        assertThat(f5(25), is(2L));
        assertThat(f5(625000), is(7L));
    }

    @Test
    public void testF5Factorial () {
        for( long n = 0; n <= MAX_FACTORIAL_LONG_INPUT; n++ ){
            assertThat(f5Factorial(n), is(f5(factorialLong(n))));
        }
    }

    @Test
    public void testT5_1K () {
        assertThat(T5(1_000), is(68L));
    }

    @Test
    public void testT5_1M () {
        assertThat(T5(1_000_000_000), is(2408210L));
    }
}
