
package frigo.math.integer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.primitives.Ints;

public class FactorizerSieveTest {

    private static final int LIMIT = 1_000_000;

    private FactorizerSieve sieve = new FactorizerSieve(LIMIT);

    @Test
    public void test_factor () {
        assertFactors(0);
        assertFactors(1);
        assertFactors(2, 2);
        assertFactors(3, 3);
        assertFactors(4, 2, 2);
        assertFactors(5, 5);
        assertFactors(6, 2, 3);
        assertFactors(7, 7);
        assertFactors(8, 2, 2, 2);
        assertFactors(9, 3, 3);
        assertFactors(10, 2, 5);
        assertFactors(11, 11);
        assertFactors(12, 2, 2, 3);
        assertFactors(13, 13);
        assertFactors(14, 2, 7);
        assertFactors(15, 3, 5);
        assertFactors(143, 11, 13);
        assertFactors(187, 11, 17);
        assertFactors(999_999, 3, 3, 3, 7, 11, 13, 37);
        assertFactors(1_000_000, 2, 2, 2, 2, 2, 2, 5, 5, 5, 5, 5, 5);
    }

    private void assertFactors (int n, int... factors) {
        List<Integer> factorList = Ints.asList(factors);
        assertThat(sieve.factor(n), is(factorList));
    }

    @Test
    public void test_primes () {
        assertPrime(0, false);
        assertPrime(1, false);
        assertPrime(2, true);
        assertPrime(3, true);
        assertPrime(4, false);
        assertPrime(5, true);
        assertPrime(6, false);
        assertPrime(7, true);
        assertPrime(8, false);
        assertPrime(9, false);
        assertPrime(10, false);
        assertPrime(11, true);
        assertPrime(12, false);
        assertPrime(13, true);
        assertPrime(14, false);
        assertPrime(15, false);
        assertPrime(143, false);
        assertPrime(187, false);
        assertPrime(999_999, false);
        assertPrime(1_000_000, false);
    }

    private void assertPrime (int n, boolean prime) {
        assertThat(n + " should " + (prime ? "" : "not ") + "be prime", sieve.prime(n), is(prime));
    }

    @Test
    public void test_biprimes () {
        assertBiprime(0, false);
        assertBiprime(1, false);
        assertBiprime(2, false);
        assertBiprime(3, false);
        assertBiprime(4, false);
        assertBiprime(5, false);
        assertBiprime(6, true);
        assertBiprime(7, false);
        assertBiprime(8, false);
        assertBiprime(9, false);
        assertBiprime(10, true);
        assertBiprime(11, false);
        assertBiprime(12, false);
        assertBiprime(13, false);
        assertBiprime(14, true);
        assertBiprime(15, true);
        assertBiprime(143, true);
        assertBiprime(187, true);
        assertBiprime(999_999, false);
        assertBiprime(1_000_000, false);
    }

    private void assertBiprime (int n, boolean biprime) {
        assertThat(n + " should " + (biprime ? "" : "not ") + "be biprime", sieve.biprime(n), is(biprime));
    }

}
