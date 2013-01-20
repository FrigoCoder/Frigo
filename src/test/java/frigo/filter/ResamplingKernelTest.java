
package frigo.filter;

import org.junit.Test;

public class ResamplingKernelTest extends KernelTestBase {

    private static class ResamplingKernelDummy extends ResamplingKernel {

        public ResamplingKernelDummy (double radius) {
            super(radius);
        }

        public ResamplingKernelDummy (double left, double right) {
            super(left, right);
        }

        @Override
        public double evaluate (double x) {
            return x;
        }
    }

    @Test
    public void testResamplingKernelDouble () {
        kernel = new ResamplingKernelDummy(2.0);
        checkRadiusAndDomain(kernel, 2.0, -2.0, 2.0);
    }

    @Test
    public void testResamplingKernelDoubleDouble () {
        kernel = new ResamplingKernelDummy(-1.0, 3.0);
        checkRadiusAndDomain(kernel, 3.0, -1.0, 3.0);
    }
}
