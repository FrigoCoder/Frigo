
package frigo.filter;

import org.junit.Test;

public class CubicBSplineTest extends KernelTestBase {

    @Test
    public void testCubicBSpline () {
        compareKernels(new CubicBSpline(), new BCCubicSpline(1, 0), 0.25);
    }
}
