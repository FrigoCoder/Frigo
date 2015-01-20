
package frigo.math.integer;

import static frigo.math.integer.MathInt.factorial;
import static frigo.math.integer.MathInt.gcd;
import static frigo.math.integer.MathInt.isPowerOfTwo;
import static frigo.math.integer.MathInt.log2;
import static frigo.math.integer.MathInt.pow;
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
    public void test_factorial () {
        assertFactorial(0, 1);
        assertFactorial(1, 1);
        assertFactorial(2, 2);
        assertFactorial(3, 6);
        assertFactorial(4, 24);
        assertFactorial(5, 120);
        assertFactorial(6, 720);
        assertFactorial(7, 5_040);
        assertFactorial(8, 40_320);
        assertFactorial(9, 362_880);
        assertFactorial(10, 3_628_800);
        assertFactorial(11, 39_916_800);
        assertFactorial(12, 479_001_600);
    }

    private void assertFactorial (int n, int factorial) {
        assertThat(factorial(n), is(factorial));
    }

    @Test
    public void test_factorial_overflow () {
        thrown.expect(IllegalArgumentException.class);
        factorial(13);
    }

    @Test
    public void test_factorial_long () {
        assertFactorial(0, 1L);
        assertFactorial(1, 1L);
        assertFactorial(2, 2L);
        assertFactorial(3, 6L);
        assertFactorial(4, 24L);
        assertFactorial(5, 120L);
        assertFactorial(6, 720L);
        assertFactorial(7, 5_040L);
        assertFactorial(8, 40_320L);
        assertFactorial(9, 362_880L);
        assertFactorial(10, 3_628_800L);
        assertFactorial(11, 39_916_800L);
        assertFactorial(12, 479_001_600L);
        assertFactorial(13, 6_227_020_800L);
        assertFactorial(14, 87_178_291_200L);
        assertFactorial(15, 1_307_674_368_000L);
        assertFactorial(16, 20_922_789_888_000L);
        assertFactorial(17, 355_687_428_096_000L);
        assertFactorial(18, 6_402_373_705_728_000L);
        assertFactorial(19, 121_645_100_408_832_000L);
        assertFactorial(20, 2_432_902_008_176_640_000L);
    }

    private void assertFactorial (long n, long factorial) {
        assertThat(factorial(n), is(factorial));
    }

    @Test
    public void test_factorial_long_overflow () {
        thrown.expect(IllegalArgumentException.class);
        factorial(21L);
    }

    @Test
    public void test_gcd () {
        assertGcd(0, 0, 0);
        assertGcd(0, 1, 1);
        assertGcd(1, 0, 1);
        assertGcd(1, 1, 1);
        assertGcd(2, 1, 1);
        assertGcd(2, 3, 1);
        assertGcd(2, 4, 2);
        assertGcd(2, 6, 2);
        assertGcd(3, 6, 3);
        assertGcd(2 * 3 * 5 * 7, 2 * 3 * 7 * 11, 2 * 3 * 7);
    }

    private void assertGcd (int a, int b, int gcd) {
        assertThat(gcd(a, b), is(gcd));
    }

    @Test
    public void test_gcd_long () {
        assertGcd(0, 0, 0L);
        assertGcd(0, 1, 1L);
        assertGcd(1, 0, 1L);
        assertGcd(1, 1, 1L);
        assertGcd(2, 1, 1L);
        assertGcd(2, 3, 1L);
        assertGcd(2, 4, 2L);
        assertGcd(2, 6, 2L);
        assertGcd(3, 6, 3L);
        assertGcd(2 * 3 * 5 * 7, 2 * 3 * 7 * 11, 2 * 3 * 7L);
    }

    private void assertGcd (long a, long b, long gcd) {
        assertThat(gcd(a, b), is(gcd));
    }

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
    public void test_pow () {
        assertPow(2, 2, 4);
        assertPow(2, 5, 32);
        assertPow(2, 30, 1_073_741_824);
        assertPow(3, 2, 9);
        assertPow(3, 3, 27);
        assertPow(46_340, 2, 2_147_395_600);
    }

    private void assertPow (int base, int exponent, int expected) {
        assertThat(pow(base, exponent), is(expected));
    }

    @Test
    public void test_pow_negative_power () {
        thrown.expect(IllegalArgumentException.class);
        pow(2, -1);
    }

    @Test
    public void test_pow_long () {
        assertPow(2, 2, 4L);
        assertPow(2, 5, 32L);
        assertPow(2, 30, 1_073_741_824L);
        assertPow(2, 32, 4_294_967_296L);
        assertPow(2, 62, 4_611_686_018_427_387_904L);
        assertPow(3, 2, 9L);
        assertPow(3, 3, 27L);
        assertPow(46_340, 2, 2_147_395_600L);
        assertPow(3_037_000_499L, 2, 9_223_372_030_926_249_001L);
    }

    private void assertPow (long base, long exponent, long expected) {
        assertThat(pow(base, exponent), is(expected));
    }

    @Test
    public void test_pow_long_negative () {
        thrown.expect(IllegalArgumentException.class);
        pow(2L, -1);
    }

    @Test
    public void test_sgn () {
        assertSgn(Integer.MIN_VALUE, -1);
        assertSgn(-1, -1);
        assertSgn(0, 0);
        assertSgn(1, 1);
        assertSgn(Integer.MAX_VALUE, 1);
    }

    private void assertSgn (int x, int sgn) {
        assertThat(MathInt.sgn(x), is(sgn));
    }

    @Test
    public void test_sgn_long () {
        assertSgn(Long.MIN_VALUE, -1);
        assertSgn((long) Integer.MIN_VALUE - 1, -1);
        assertSgn((long) Integer.MIN_VALUE, -1);
        assertSgn(-1L, -1);
        assertSgn(0L, 0);
        assertSgn(1L, 1);
        assertSgn((long) Integer.MAX_VALUE, 1);
        assertSgn((long) Integer.MAX_VALUE + 1, 1);
        assertSgn(Long.MAX_VALUE, 1);
    }

    private void assertSgn (long x, long sgn) {
        assertThat(MathInt.sgn(x), is(sgn));
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
