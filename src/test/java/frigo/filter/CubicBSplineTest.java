
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelEquals;

import org.junit.Test;

public class CubicBSplineTest {

    @Test
    public void testCubicBSpline () {
        assertKernelEquals(new CubicBSpline(), new BCCubicSpline(1, 0), 0.25);
    }
}
