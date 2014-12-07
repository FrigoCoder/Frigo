
package frigo.math;

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
    }

    private void assertFactors (int n, int... factors) {
        List<Integer> factorList = Ints.asList(factors);
        assertThat(sieve.factor(n), is(factorList));
    }
}
