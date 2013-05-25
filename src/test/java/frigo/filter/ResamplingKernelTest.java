
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelDomainEquals;

import org.junit.Test;

public class ResamplingKernelTest {

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
        assertKernelDomainEquals(new ResamplingKernelDummy(2.0), 2.0, -2.0, 2.0);
    }

    @Test
    public void testResamplingKernelDoubleDouble () {
        assertKernelDomainEquals(new ResamplingKernelDummy(-1.0, 3.0), 3.0, -1.0, 3.0);
    }
}
