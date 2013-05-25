
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelEquals;

import org.junit.Test;

public class HermiteTest {

    private static class HermiteNaive extends ResamplingKernel {

        public HermiteNaive () {
            super(1.0);
        }

        @Override
        public double evaluate (double t) {
            if( !isInDomain(t) ){
                return 0.0;
            }
            double x = Math.abs(t);
            return 2 * x * x * x - 3 * x * x + 1;
        }
    }

    @Test
    public void testHermite () {
        assertKernelEquals(new Hermite(), new HermiteNaive(), 0.25);
    }
}
