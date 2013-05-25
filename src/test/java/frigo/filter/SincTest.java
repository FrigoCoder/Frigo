
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelEquals;
import static frigo.math.MathAux.sinc;

import org.junit.Test;

public class SincTest {

    private static class SincNaive extends ResamplingKernel {

        public SincNaive (double radius) {
            super(radius);
        }

        @Override
        public double evaluate (double x) {
            if( !isInDomain(x) ){
                return 0;
            }
            if( x != 0 ){
                return sinc(x);
            }
            return 1;
        }
    }

    @Test
    public void testDefaultSinc () {
        assertKernelEquals(new Sinc(), new SincNaive(3), 0.25);
    }

    @Test
    public void testSinc () {
        assertKernelEquals(new Sinc(3), new SincNaive(3), 0.25);
    }
}
