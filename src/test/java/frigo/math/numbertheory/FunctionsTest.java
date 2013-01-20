
package frigo.math.numbertheory;

import static frigo.math.numbertheory.Functions.MAX_FACTORIAL_INPUT;
import static frigo.math.numbertheory.Functions.MAX_FACTORIAL_LONG_INPUT;
import static frigo.math.numbertheory.Functions.factorial;
import static frigo.math.numbertheory.Functions.factorialLong;
import static frigo.math.numbertheory.Functions.isPrime;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FunctionsTest {

    @Test
    public void factorialGivesCorrectAnswers () {
        assertThat(factorial(0), is(1));
        int cumulative = 1;
        for( int i = 1; i <= MAX_FACTORIAL_INPUT; i++ ){
            cumulative *= i;
            assertThat(factorial(i), is(cumulative));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void factorialShouldNotOverflow () {
        factorial(MAX_FACTORIAL_INPUT + 1);
    }

    @Test
    public void longFactorialGivesCorrectAnswers () {
        assertThat(factorialLong(0), is(1L));
        long cumulative = 1;
        for( int i = 1; i <= MAX_FACTORIAL_LONG_INPUT; i++ ){
            cumulative *= i;
            assertThat(factorialLong(i), is(cumulative));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void longFactorialShouldNotOverflow () {
        factorialLong(MAX_FACTORIAL_LONG_INPUT + 1);
    }

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
