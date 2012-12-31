
package frigo.filter;

import static frigo.math.MathAux.sinc;
import org.junit.Test;

public class SincTest extends KernelTestBase {

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
        compareKernels(new Sinc(), new SincNaive(3), 0.25);
    }

    @Test
    public void testSinc () {
        compareKernels(new Sinc(3), new SincNaive(3), 0.25);
    }
}
