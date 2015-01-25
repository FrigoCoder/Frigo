
package frigo.math.integer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.primitives.Longs;

import frigo.math.integer.MathInt;

public class ExtendedEuclideanTest {

    @Test
    public void test_q () {
        assertQ(17, 2, 8, 2);
        assertQ(17, 3, 5, 1, 2);
        assertQ(21, 8, 2, 1, 1, 1, 2);
        assertQ(21, 13, 1, 1, 1, 1, 1, 2);
    }

    private void assertQ (long a, long b, long... expectedQ) {
        ExtendedEuclidean euclidean = new ExtendedEuclidean(a, b);
        assertThat(euclidean.q(), is(Longs.asList(expectedQ)));
    }

    @Test
    public void test_quotient () {
        assertQuotient(11, 2, 5);
        assertQuotient(-11, 2, -5);
        assertQuotient(11, -2, -5);
        assertQuotient(-11, -2, 5);
    }

    private void assertQuotient (long numerator, long denominator, long quotient) {
        assertThat(ExtendedEuclidean.quotient(numerator, denominator), is(quotient));
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
        ExtendedEuclidean euclidean = new ExtendedEuclidean(a, b);
        assertThat(euclidean.gcd(), is(gcd));
    }

    @Test
    public void test_evaluate () {
        assertEvaluate(-10, 1, 2, 3);
        assertEvaluate(7, 2, 3);
        assertEvaluate(3, 1, 2);
        assertEvaluate(187, 3, -5, 5, -3);
        assertEvaluate(67, 3, -5, 5);
        assertEvaluate(-67, -5, 5, -3);
    }

    private void assertEvaluate (long expected, long... q) {
        assertThat(ExtendedEuclidean.evaluate(Longs.asList(q)), is(expected));
    }

    @Test
    public void test_invAbs () {
        assertInvAbs(10, 7, 3);
        assertInvAbs(187, 67, 67);
        assertInvAbs(187, 25, 15);
        assertInvAbs(187, 2, 93);
    }

    private void assertInvAbs (long n, long x, long xinv) {
        ExtendedEuclidean euclidean = new ExtendedEuclidean(n, x);
        assertThat(euclidean.invAbs(), is(xinv));
    }

    @Test
    public void test_invSigned () {
        assertInvSigned(10, 7, 3);
        assertInvSigned(187, 67, 67);
        assertInvSigned(187, 25, 15);
        assertInvSigned(187, 2, -93);
    }

    private void assertInvSigned (long n, long x, long xinv) {
        ExtendedEuclidean euclidean = new ExtendedEuclidean(n, x);
        assertThat(euclidean.invSigned(), is(xinv));
    }

    @Test
    public void test_invUnsigned () {
        assertInvUnsigned(10, 7, 3);
        assertInvUnsigned(187, 67, 67);
        assertInvUnsigned(187, 25, 15);
        assertInvUnsigned(187, 2, 94);
    }

    private void assertInvUnsigned (long n, long x, long xinv) {
        ExtendedEuclidean euclidean = new ExtendedEuclidean(n, x);
        assertThat(euclidean.invUnsigned(), is(xinv));
    }

    @Test
    public void whatever () {
        long limit = 100;
        for( long x = 2; x <= limit; x++ ){
            for( long y = -limit; y <= limit; y++ ){
                ExtendedEuclidean euclidean = new ExtendedEuclidean(x, y);
                if( euclidean.gcd() != 1 ){
                    continue;
                }
                long yinv = euclidean.invUnsigned();
                assertThat("x=" + x + ", y=" + y + ", yinv=" + yinv, MathInt.mod(y * yinv, x), is(1L));
            }
        }
    }

}
