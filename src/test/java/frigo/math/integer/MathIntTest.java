
package frigo.math.integer;

import static frigo.math.integer.MathInt.isPowerOfTwo;
import static frigo.math.integer.MathInt.log2;
import static frigo.math.integer.MathInt.sqr;
import static frigo.math.integer.MathInt.sqrt;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MathIntTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test_isPowerOfTwo () {
        for( int i = 0; i <= 30; i++ ){
            assertPowerOfTwo(1 << i, true);
        }
        for( int i = 0; i <= 31; i++ ){
            assertPowerOfTwo(-(1 << i), false);
        }
        int[] notPowerOfTwos = {0, 3, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20};
        for( int i : notPowerOfTwos ){
            assertPowerOfTwo(i, false);
        }
    }

    private void assertPowerOfTwo (int x, boolean expected) {
        assertThat(x + " should " + (expected ? "" : "not ") + "be power of two", isPowerOfTwo(x), is(expected));
    }

    @Test
    public void test_isPowerOfTwo_long () {
        for( int i = 0; i <= 62; i++ ){
            assertPowerOfTwo(1L << i, true);
        }
        for( int i = 0; i <= 63; i++ ){
            assertPowerOfTwo(-(1L << i), false);
        }
        long[] notPowerOfTwos = {0, 3, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20};
        for( long i : notPowerOfTwos ){
            assertPowerOfTwo(i, false);
        }
    }

    private void assertPowerOfTwo (long x, boolean expected) {
        assertThat(x + " should " + (expected ? "" : "not ") + "be power of two", isPowerOfTwo(x), is(expected));
    }

    @Test
    public void test_log2 () {
        for( int i = 1; i <= 30; i++ ){
            assertLog2(1 << i, i);
            assertLog2((1 << i) + 1, i);
            assertLog2((1 << i) - 1, i - 1);
        }
        assertLog2(2_147_483_647, 30);
    }

    private void assertLog2 (int x, int log2) {
        assertThat(log2(x), is(log2));
    }

    @Test
    public void test_log2_0 () {
        thrown.expect(IllegalArgumentException.class);
        log2(0);
    }

    @Test
    public void test_log2_negative () {
        thrown.expect(IllegalArgumentException.class);
        log2(-1);
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

    private void assertLog2 (long x, int log2) {
        assertThat(log2(x), is(log2));
    }

    @Test
    public void test_log2_long_0 () {
        thrown.expect(IllegalArgumentException.class);
        log2(0L);
    }

    @Test
    public void test_log2_long_negative () {
        thrown.expect(IllegalArgumentException.class);
        log2(-1L);
    }

    @Test
    public void test_sqr_int () {
        assertSqr(-2_147_483_648, 4_611_686_018_427_387_904L);
        assertSqr(-3, 9L);
        assertSqr(-2, 4L);
        assertSqr(-1, 1L);
        assertSqr(0, 0L);
        assertSqr(1, 1L);
        assertSqr(2, 4L);
        assertSqr(3, 9L);
        assertSqr(2_147_483_647, 4_611_686_014_132_420_609L);
    }

    private void assertSqr (int x, long expected) {
        assertThat(sqr(x), is(expected));
    }

    @Test
    public void test_sqr_long () {
        assertSqr(-3_037_000_499L, 9_223_372_030_926_249_001L);
        assertSqr(-2_147_483_648L, 4_611_686_018_427_387_904L);
        assertSqr(-3L, 9L);
        assertSqr(-2L, 4L);
        assertSqr(-1L, 1L);
        assertSqr(0L, 0L);
        assertSqr(1L, 1L);
        assertSqr(2L, 4L);
        assertSqr(3L, 9L);
        assertSqr(2_147_483_647L, 4_611_686_014_132_420_609L);
        assertSqr(3_037_000_499L, 9_223_372_030_926_249_001L);
    }

    private void assertSqr (long x, long expected) {
        assertThat(sqr(x), is(expected));
    }

    @Test
    public void test_sqrt () {
        assertSqrt(0, 0);
        assertSqrt(1, 1);
        assertSqrt(3, 1);
        assertSqrt(4, 2);
        assertSqrt(8, 2);
        assertSqrt(9, 3);
        assertSqrt(15, 3);
        assertSqrt(16, 4);
        assertSqrt(1_073_741_823, 32_767);
        assertSqrt(1_073_741_824, 32_768);
        assertSqrt(2_147_395_599, 46_339);
        assertSqrt(2_147_395_600, 46_340);
        assertSqrt(2_147_483_647, 46_340);
    }

    private static void assertSqrt (int x, int sqrt) {
        assertThat(sqrt(x), is(sqrt));
    }

    @Test
    public void test_sqrt_negative () {
        thrown.expect(IllegalArgumentException.class);
        sqrt(-1);
    }

    @Test
    public void test_sqrt_long () {
        assertSqrt(0L, 0L);
        assertSqrt(1L, 1L);
        assertSqrt(3L, 1L);
        assertSqrt(4L, 2L);
        assertSqrt(8L, 2L);
        assertSqrt(9L, 3L);
        assertSqrt(15L, 3L);
        assertSqrt(16L, 4L);
        assertSqrt(1_073_741_823L, 32_767L);
        assertSqrt(1_073_741_824L, 32_768L);
        assertSqrt(2_147_395_599L, 46_339L);
        assertSqrt(2_147_395_600L, 46_340L);
        assertSqrt(2_147_483_647L, 46_340L);
        assertSqrt(9_223_372_030_926_249_000L, 3_037_000_498L);
        assertSqrt(9_223_372_030_926_249_001L, 3_037_000_499L);
        assertSqrt(9_223_372_036_854_775_807L, 3_037_000_499L);
    }

    private static void assertSqrt (long x, long sqrt) {
        assertThat(sqrt(x), is(sqrt));
    }

    @Test
    public void test_sqrt_long_negative () {
        thrown.expect(IllegalArgumentException.class);
        sqrt(-1L);
    }

}
