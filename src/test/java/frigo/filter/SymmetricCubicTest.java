
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelEquals;
import static java.lang.Math.abs;

import org.junit.Before;
import org.junit.Test;

public class SymmetricCubicTest {

    private static class DummySymmetricCubic extends SymmetricCubic {
    }

    private static class SymmetricCubicNaive extends SymmetricCubic {

        @Override
        public double evaluate (double signedx) {
            double x = abs(signedx);
            if( x < 1 ){
                return p * x * x * x + q * x * x + r * x + s;
            }
            if( x < 2 ){
                return t * x * x * x + u * x * x + v * x + w;
            }
            return 0;
        }
    }

    private SymmetricCubic cubic;
    private SymmetricCubic naive;

    @Before
    public void setUp () {
        cubic = new DummySymmetricCubic();
        naive = new SymmetricCubicNaive();
    }

    @Test
    public void testInnerX0 () {
        cubic.s = naive.s = 1.0;
        assertKernelEquals(cubic, naive, 0.25);
    }

    @Test
    public void testInnerX1 () {
        cubic.r = naive.r = 1.0;
        assertKernelEquals(cubic, naive, 0.25);
    }

    @Test
    public void testInnerX2 () {
        cubic.q = naive.q = 1.0;
        assertKernelEquals(cubic, naive, 0.25);
    }

    @Test
    public void testInnerX3 () {
        cubic.p = naive.p = 1.0;
        assertKernelEquals(cubic, naive, 0.25);
    }

    @Test
    public void testOuterX0 () {
        cubic.w = naive.w = 1.0;
        assertKernelEquals(cubic, naive, 0.25);
    }

    @Test
    public void testOuterX1 () {
        cubic.v = naive.v = 1.0;
        assertKernelEquals(cubic, naive, 0.25);
    }

    @Test
    public void testOuterX2 () {
        cubic.u = naive.u = 1.0;
        assertKernelEquals(cubic, naive, 0.25);
    }

    @Test
    public void testOuterX3 () {
        cubic.t = naive.t = 1.0;
        assertKernelEquals(cubic, naive, 0.25);
    }

    @Test
    public void testZero () {
        assertKernelEquals(cubic, naive, 0.25);
    }
}
