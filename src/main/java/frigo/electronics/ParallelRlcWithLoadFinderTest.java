
package frigo.electronics;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ParallelRlcWithLoadFinderTest {

    private ParallelRlcWithLoadFinder finder = new ParallelRlcWithLoadFinder(4800, -10, 0.3, 32);

    @Test
    public void testR () {
        assertThat(finder.R, is(69.19288512538813));
    }

    @Test
    public void testLC () {
        assertThat(finder.LC, is(0.0));
    }

    @Test
    public void testLowerCutoffFrequency () {
        assertThat(finder.f1, is(1329.5230317524802));
    }

    @Test
    public void testUpperCutoffFrequency () {
        assertThat(finder.f2, is(17329.52303175248));
    }

}
