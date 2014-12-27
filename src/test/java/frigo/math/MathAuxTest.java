
package frigo.math;

import static frigo.math.Complex.complex;
import static frigo.math.MathAux.ceil;
import static frigo.math.MathAux.floor;
import static frigo.math.MathAux.isPowerOfTwo;
import static frigo.math.MathAux.isqrt;
import static frigo.math.MathAux.sinc;
import static frigo.math.MathAux.sqr;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MathAuxTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private double epsilon = 1.0e-15;
    private float epsilonFloat = (float) 1.0e-15;

    @Test
    public void testSincDouble () {
        assertThat(sinc(-2.0), closeTo(0.0, epsilon));
        assertThat(sinc(-1.0), closeTo(0.0, epsilon));
        assertThat(sinc(0.0), closeTo(1.0, epsilon));
        assertThat(sinc(1.0), closeTo(0.0, epsilon));
        assertThat(sinc(2.0), closeTo(0.0, epsilon));
        assertThat(sinc(-1.5), closeTo(-0.21220659078919378102517835116335, epsilon));
        assertThat(sinc(-0.5), closeTo(0.63661977236758134307553505349004, epsilon));
        assertThat(sinc(0.5), closeTo(0.63661977236758134307553505349004, epsilon));
        assertThat(sinc(1.5), closeTo(-0.21220659078919378102517835116335, epsilon));
    }

    @Test
    public void testSincFloat () {
        assertThat((double) sinc(-2.0f), closeTo(0.0f, epsilonFloat));
        assertThat((double) sinc(-1.0f), closeTo(0.0f, epsilonFloat));
        assertThat((double) sinc(0.0f), closeTo(1.0f, epsilonFloat));
        assertThat((double) sinc(1.0f), closeTo(0.0f, epsilonFloat));
        assertThat((double) sinc(2.0f), closeTo(0.0f, epsilonFloat));
        assertThat((double) sinc(-1.5f), closeTo(-0.21220659078919378102517835116335f, epsilonFloat));
        assertThat((double) sinc(-0.5f), closeTo(0.63661977236758134307553505349004f, epsilonFloat));
        assertThat((double) sinc(0.5f), closeTo(0.63661977236758134307553505349004f, epsilonFloat));
        assertThat((double) sinc(1.5f), closeTo(-0.21220659078919378102517835116335f, epsilonFloat));
    }

    @Test
    public void testSqrComplex () {
        assertThat(complex(-3.0, 4.0), is(sqr(complex(1.0, 2.0))));
    }

    @Test
    public void testSqrDouble () {
        assertThat(sqr(1.0), closeTo(1.0, epsilon));
        assertThat(sqr(2.0), closeTo(4.0, epsilon));
        assertThat(sqr(3.0), closeTo(9.0, epsilon));
        assertThat(sqr(4.5), closeTo(20.25, epsilon));
    }

    @Test
    public void testSqrFloat () {
        assertThat((double) sqr(1.0f), closeTo(1.0f, epsilonFloat));
        assertThat((double) sqr(2.0f), closeTo(4.0f, epsilonFloat));
        assertThat((double) sqr(3.0f), closeTo(9.0f, epsilonFloat));
        assertThat((double) sqr(4.5f), closeTo(20.25f, epsilonFloat));
    }

    @Test
    public void testSqrInt () {
        assertThat(1l, is(sqr(1)));
        assertThat(4l, is(sqr(2)));
        assertThat(9l, is(sqr(3)));
    }

    @Test
    public void testCeil () {
        assertThat(ceil(-1.0), is(-1));
        assertThat(ceil(-0.9), is(0));
        assertThat(ceil(0.0), is(0));
        assertThat(ceil(0.1), is(1));
        assertThat(ceil(0.9), is(1));
        assertThat(ceil(1.0), is(1));
    }

    @Test
    public void testFloor () {
        assertThat(floor(-1.0), is(-1));
        assertThat(floor(-0.9), is(-1));
        assertThat(floor(0.0), is(0));
        assertThat(floor(0.1), is(0));
        assertThat(floor(0.9), is(0));
        assertThat(floor(1.0), is(1));
    }

    @Test
    public void testisPowerOfTwo () {
        for( int i = 1; i > 0; i *= 2 ){
            assertThat(i + " should be power of two", isPowerOfTwo(i), is(true));
        }
        for( int i = -1; i < 0; i *= 2 ){
            assertThat(i + " should not be power of two", isPowerOfTwo(i), is(false));
        }
        int[] someNotPowerOfTwos = {0, 3, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20};
        for( int i : someNotPowerOfTwos ){
            assertThat(i + " should not be power of two", isPowerOfTwo(i), is(false));
        }
    }

    @Test
    public void test_isqrt () {
        assertIsqrt(0, 0);
        assertIsqrt(1, 1);
        assertIsqrt(3, 1);
        assertIsqrt(4, 2);
        assertIsqrt(8, 2);
        assertIsqrt(9, 3);
        assertIsqrt(15, 3);
        assertIsqrt(16, 4);
        assertIsqrt(1_073_741_823, 32_767);
        assertIsqrt(1_073_741_824, 32_768);
        assertIsqrt(2_147_395_599, 46_339);
        assertIsqrt(2_147_395_600, 46_340);
        assertIsqrt(2_147_483_647, 46_340);
    }

    @Test
    public void test_isqrt_unsigned () {
        assertIsqrt((int) 2_147_483_648L, 46_340);
        assertIsqrt((int) 4_294_967_295L, 65_535);
    }

    private static void assertIsqrt (int x, int sqrt) {
        assertThat("Square root of " + x, isqrt(x), is(sqrt));
    }

    @Test
    public void test_isqrt_for_long () {
        assertIsqrt(0L, 0L);
        assertIsqrt(1L, 1L);
        assertIsqrt(3L, 1L);
        assertIsqrt(4L, 2L);
        assertIsqrt(8L, 2L);
        assertIsqrt(9L, 3L);
        assertIsqrt(15L, 3L);
        assertIsqrt(16L, 4L);
        assertIsqrt(1_073_741_823L, 32_767L);
        assertIsqrt(1_073_741_824L, 32_768L);
        assertIsqrt(2_147_395_599L, 46_339L);
        assertIsqrt(2_147_395_600L, 46_340L);
        assertIsqrt(2_147_483_647L, 46_340L);
        assertIsqrt(9_223_372_030_926_249_000L, 3_037_000_498L);
        assertIsqrt(9_223_372_030_926_249_001L, 3_037_000_499L);
        assertIsqrt(9_223_372_036_854_775_807L, 3_037_000_499L);
    }

    @Test
    public void test_isqrt_for_long_unsigned () {
        assertIsqrt(-9223372036854775808L, 3_037_000_499L);
        assertIsqrt(-1L, 4_294_967_295L);
    }

    private static void assertIsqrt (long x, long sqrt) {
        assertThat("Square root of " + x, isqrt(x), is(sqrt));
    }

    @Test
    public void test_log2 () {
        for( int i = 1; i < 30; i++ ){
            assertLog2(1 << i, i);
            assertLog2((1 << i) + 1, i);
            assertLog2((1 << i) - 1, i - 1);
        }
        assertLog2(2_147_483_647, 30);
    }

    @Test
    public void test_log2_unsigned () {
        assertLog2((int) 2_147_483_648L, 31);
        assertLog2((int) 4_294_967_295L, 31);
    }

    @Test
    public void test_log2_0 () {
        thrown.expect(IllegalArgumentException.class);
        MathAux.log2(0);
    }

    private void assertLog2 (int x, int log2) {
        assertThat("log2 of " + x, MathAux.log2(x), is(log2));
    }

    @Test
    public void test_log2_long () {
        for( int i = 1; i < 62; i++ ){
            assertLog2(1L << i, i);
            assertLog2((1L << i) + 1, i);
            assertLog2((1L << i) - 1, i - 1);
        }
        assertLog2(9_223_372_036_854_775_807L, 62);
    }

    @Test
    public void test_log2_long_unsigned () {
        assertLog2(-9223372036854775808L, 63);
        assertLog2(-1L, 63);
    }

    @Test
    public void test_log2_long_0 () {
        thrown.expect(IllegalArgumentException.class);
        MathAux.log2(0L);
    }

    private void assertLog2 (long x, int log2) {
        assertThat("log2 of " + x, MathAux.log2(x), is(log2));
    }

}
