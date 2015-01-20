
package frigo.math.numbertheory;

import static frigo.math.numbertheory.Functions.isPrime;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FunctionsTest {

    @Test
    public void testIsPrime () {
        assertThat(isPrime(0), is(false));
        assertThat(isPrime(1), is(false));
        assertThat(isPrime(2), is(true));
        assertThat(isPrime(3), is(true));
        assertThat(isPrime(4), is(false));
        assertThat(isPrime(5), is(true));
        assertThat(isPrime(6), is(false));
        assertThat(isPrime(7), is(true));
        assertThat(isPrime(8), is(false));
        assertThat(isPrime(9), is(false));
        assertThat(isPrime(10), is(false));
    }
}
