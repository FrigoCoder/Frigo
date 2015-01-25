
package frigo.math.integer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ChineseRemainderTheoremTest {

    @Test
    public void test_crt () {
        assertCrt(2, 3, 3, 4, 11);
        assertCrt(2, 3, 1, 5, 11);
        assertCrt(3, 4, 1, 5, 11);
    }

    private void assertCrt (long xp, long p, long xq, long q, long x) {
        assertThat(ChineseRemainderTheorem.crt(xp, p, xq, q), is(x));
    }

}
