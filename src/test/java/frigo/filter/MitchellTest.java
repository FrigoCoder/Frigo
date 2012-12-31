
package frigo.filter;

import org.junit.Test;

public class MitchellTest extends KernelTestBase {

    @Test
    public void testMitchell () {
        compareKernels(new Mitchell(), new BCCubicSpline(1.0 / 3, 1.0 / 3), 0.25);
    }
}
