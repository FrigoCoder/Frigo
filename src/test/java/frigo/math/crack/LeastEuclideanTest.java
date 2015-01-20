
package frigo.math.crack;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.primitives.Longs;

public class LeastEuclideanTest {

    @Test
    public void test_q () {
        assertQ(17, 2, 8, 2);
        assertQ(17, 3, 6, -3);
        assertQ(21, 8, 3, -3, 3);
        assertQ(21, 13, 2, -3, 3, -2);
    }

    private void assertQ (long a, long b, long... expectedQ) {
        LeastEuclidean euclidean = new LeastEuclidean(a, b);
        assertThat(euclidean.q(), is(Longs.asList(expectedQ)));
    }

    @Test
    public void test_quotient () {
        assertQuotient(11, 2, 5);
        assertQuotient(-11, 2, -6);
        assertQuotient(11, -2, -5);
        assertQuotient(-11, -2, 6);
    }

    private void assertQuotient (long numerator, long denominator, long quotient) {
        assertThat(LeastEuclidean.quotient(numerator, denominator), is(quotient));
    }

    @Test
    public void test_gcd () {
        assertGcd(0, 0, 0);
        assertGcd(1, 0, 1);
        assertGcd(1, 1, 1);
        assertGcd(2, 1, 1);
        assertGcd(3, 2, 1);
        assertGcd(4, 2, 2);
        assertGcd(6, 2, 2);
        assertGcd(6, 3, 3);
        assertGcd(2 * 3 * 7 * 11, 2 * 3 * 5 * 7, 2 * 3 * 7);
    }

    @Test
    public void test_gcd_with_smaller_parameter_first () {
        assertGcd(0, 1, 1);
        assertGcd(1, 2, 1);
        assertGcd(2, 3, 1);
        assertGcd(2, 4, 2);
        assertGcd(2, 6, 2);
        assertGcd(3, 6, 3);
        assertGcd(2 * 3 * 5 * 7, 2 * 3 * 7 * 11, 2 * 3 * 7);
    }

    private void assertGcd (long a, long b, long gcd) {
        LeastEuclidean euclidean = new LeastEuclidean(a, b);
        assertThat(euclidean.gcd(), is(gcd));
    }

}
