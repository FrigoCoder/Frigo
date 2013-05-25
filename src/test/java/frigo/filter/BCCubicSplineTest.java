
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelEquals;

import org.junit.Test;

public class BCCubicSplineTest {

    private static class CubicNaive extends SymmetricCubic {

        public CubicNaive (double b, double c) {
            p = (12 - 9 * b - 6 * c) / 6;
            q = (-18 + 12 * b + 6 * c) / 6;
            r = 0;
            s = (6 - 2 * b) / 6;
            t = (-b - 6 * c) / 6;
            u = (6 * b + 30 * c) / 6;
            v = (-12 * b - 48 * c) / 6;
            w = (8 * b + 24 * c) / 6;
        }
    }

    @Test
    public void testCatmullRom () {
        assertKernelEquals(new BCCubicSpline(0, 0.5), new CubicNaive(0, 0.5), 0.25);
    }

    @Test
    public void testCubicBSpline () {
        assertKernelEquals(new BCCubicSpline(1, 0), new CubicNaive(1, 0), 0.25);
    }

    @Test
    public void testMitchell () {
        assertKernelEquals(new BCCubicSpline(1.0 / 3.0, 1.0 / 3.0), new CubicNaive(1.0 / 3.0, 1.0 / 3.0), 0.25);
    }
}
