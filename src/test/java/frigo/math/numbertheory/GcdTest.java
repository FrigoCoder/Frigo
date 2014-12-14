
package frigo.math.numbertheory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GcdTest {

    @Test
    public void testGcdInt () {
        assertThat(Gcd.gcd(0, 0), is(0));
        assertThat(Gcd.gcd(0, 1), is(1));
        assertThat(Gcd.gcd(1, 0), is(1));
        assertThat(Gcd.gcd(1, 1), is(1));
        assertThat(Gcd.gcd(2, 1), is(1));
        assertThat(Gcd.gcd(2, 3), is(1));
        assertThat(Gcd.gcd(2, 4), is(2));
        assertThat(Gcd.gcd(2, 6), is(2));
        assertThat(Gcd.gcd(3, 6), is(3));
        assertThat(Gcd.gcd(2 * 3 * 5 * 7, 2 * 3 * 7 * 11), is(2 * 3 * 7));
    }

    @Test
    public void testGcdLong () {
        assertThat(Gcd.gcd(0L, 0L), is(0L));
        assertThat(Gcd.gcd(0L, 1L), is(1L));
        assertThat(Gcd.gcd(1L, 0L), is(1L));
        assertThat(Gcd.gcd(1L, 1L), is(1L));
        assertThat(Gcd.gcd(2L, 1L), is(1L));
        assertThat(Gcd.gcd(2L, 3L), is(1L));
        assertThat(Gcd.gcd(2L, 4L), is(2L));
        assertThat(Gcd.gcd(2L, 6L), is(2L));
        assertThat(Gcd.gcd(3L, 6L), is(3L));
        assertThat(Gcd.gcd(2L * 3 * 5 * 7, 2L * 3 * 7 * 11), is(2L * 3 * 7));
    }

}
