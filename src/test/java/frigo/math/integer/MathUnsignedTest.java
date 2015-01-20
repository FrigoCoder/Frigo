
package frigo.math.integer;

import static frigo.math.integer.MathUnsigned.isPowerOfTwoUnsigned;
import static frigo.math.integer.MathUnsigned.log2Unsigned;
import static frigo.math.integer.MathUnsigned.sqrtUnsigned;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MathUnsignedTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test_isPowerOfTwo () {
        for( int i = 0; i <= 31; i++ ){
            assertPowerOfTwoUnsigned(1 << i, true);
        }
        for( int i = 0; i <= 30; i++ ){
            assertPowerOfTwoUnsigned(-(1 << i), false);
        }
        int[] notPowerOfTwos = {0, 3, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20};
        for( int i : notPowerOfTwos ){
            assertPowerOfTwoUnsigned(i, false);
        }
    }

    private void assertPowerOfTwoUnsigned (int x, boolean expected) {
        assertThat(x + " should " + (expected ? "" : "not ") + "be power of two", isPowerOfTwoUnsigned(x), is(expected));
    }

    @Test
    public void test_isPowerOfTwo_long () {
        for( int i = 0; i <= 63; i++ ){
            assertPowerOfTwoUnsigned(1L << i, true);
        }
        for( int i = 0; i <= 62; i++ ){
            assertPowerOfTwoUnsigned(-(1L << i), false);
        }
        long[] notPowerOfTwos = {0, 3, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20};
        for( long i : notPowerOfTwos ){
            assertPowerOfTwoUnsigned(i, false);
        }
    }

    private void assertPowerOfTwoUnsigned (long x, boolean expected) {
        assertThat(x + " should " + (expected ? "" : "not ") + "be power of two", isPowerOfTwoUnsigned(x), is(expected));
    }

    @Test
    public void test_log2Unsigned () {
        for( int i = 1; i <= 30; i++ ){
            assertLog2Unsigned(1 << i, i);
            assertLog2Unsigned((1 << i) + 1, i);
            assertLog2Unsigned((1 << i) - 1, i - 1);
        }
        assertLog2Unsigned(2_147_483_647, 30);
        assertLog2Unsigned((int) 2_147_483_648L, 31);
        assertLog2Unsigned((int) 4_294_967_295L, 31);
    }

    private void assertLog2Unsigned (int x, int log2) {
        assertThat(log2Unsigned(x), is(log2));
    }

    @Test
    public void test_log2Unsigned_0 () {
        thrown.expect(IllegalArgumentException.class);
        log2Unsigned(0);
    }

    @Test
    public void test_log2Unsigned_long () {
        for( int i = 1; i < 62; i++ ){
            assertLog2Unsigned(1L << i, i);
            assertLog2Unsigned((1L << i) + 1, i);
            assertLog2Unsigned((1L << i) - 1, i - 1);
        }
        assertLog2Unsigned(9_223_372_036_854_775_807L, 62);
        assertLog2Unsigned(-9_223_372_036_854_775_808L, 63);
        assertLog2Unsigned(-1L, 63);
    }

    private void assertLog2Unsigned (long x, int log2) {
        assertThat(log2Unsigned(x), is(log2));
    }

    @Test
    public void test_log2Unsigned_long_0 () {
        thrown.expect(IllegalArgumentException.class);
        log2Unsigned(0L);
    }

    @Test
    public void test_sqrtUnsigned () {
        assertSqrtUnsigned(0, 0);
        assertSqrtUnsigned(1, 1);
        assertSqrtUnsigned(3, 1);
        assertSqrtUnsigned(4, 2);
        assertSqrtUnsigned(8, 2);
        assertSqrtUnsigned(9, 3);
        assertSqrtUnsigned(15, 3);
        assertSqrtUnsigned(16, 4);
        assertSqrtUnsigned(1_073_741_823, 32_767);
        assertSqrtUnsigned(1_073_741_824, 32_768);
        assertSqrtUnsigned(2_147_395_599, 46_339);
        assertSqrtUnsigned(2_147_395_600, 46_340);
        assertSqrtUnsigned(2_147_483_647, 46_340);
        assertSqrtUnsigned((int) 2_147_483_648L, 46_340);
        assertSqrtUnsigned((int) 4_294_967_295L, 65_535);
    }

    private void assertSqrtUnsigned (int x, int sqrt) {
        assertThat(sqrtUnsigned(x), is(sqrt));
    }

    @Test
    public void test_sqrtUnsigned_long () {
        assertSqrtUnsigned(0L, 0L);
        assertSqrtUnsigned(1L, 1L);
        assertSqrtUnsigned(3L, 1L);
        assertSqrtUnsigned(4L, 2L);
        assertSqrtUnsigned(8L, 2L);
        assertSqrtUnsigned(9L, 3L);
        assertSqrtUnsigned(15L, 3L);
        assertSqrtUnsigned(16L, 4L);
        assertSqrtUnsigned(1_073_741_823L, 32_767L);
        assertSqrtUnsigned(1_073_741_824L, 32_768L);
        assertSqrtUnsigned(2_147_395_599L, 46_339L);
        assertSqrtUnsigned(2_147_395_600L, 46_340L);
        assertSqrtUnsigned(2_147_483_647L, 46_340L);
        assertSqrtUnsigned(9_223_372_030_926_249_000L, 3_037_000_498L);
        assertSqrtUnsigned(9_223_372_030_926_249_001L, 3_037_000_499L);
        assertSqrtUnsigned(9_223_372_036_854_775_807L, 3_037_000_499L);
        assertSqrtUnsigned(-9_223_372_036_854_775_808L, 3_037_000_499L);
        assertSqrtUnsigned(-1L, 4_294_967_295L);
    }

    private void assertSqrtUnsigned (long x, long sqrt) {
        assertThat(sqrtUnsigned(x), is(sqrt));
    }

}
