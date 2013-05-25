
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelEquals;

import org.junit.Test;

public class MitchellTest {

    @Test
    public void testMitchell () {
        assertKernelEquals(new Mitchell(), new BCCubicSpline(1.0 / 3, 1.0 / 3), 0.25);
    }
}
