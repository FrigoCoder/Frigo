
package frigo.filter;

import static frigo.math.MathAux.sinc;

import org.junit.Test;

public class LanczosTest extends KernelTestBase {

    private static class LanczosNaive extends Lanczos {

        public LanczosNaive (double radius) {
            super(radius);
        }

        @Override
        public double evaluate (double x) {
            if( !isInDomain(x) ){
                return 0.0;
            }
            return sinc(x) * sinc(x / radius);
        }
    }

    @Test
    public void testLanczos () {
        compareKernels(new Lanczos(), new LanczosNaive(3.0), 0.25);
    }

    @Test
    public void testLanczos1 () {
        compareKernels(new Lanczos(1.0), new LanczosNaive(1.0), 0.25);
    }

    @Test
    public void testLanczos2 () {
        compareKernels(new Lanczos(2.0), new LanczosNaive(2.0), 0.25);
    }

    @Test
    public void testLanczos3 () {
        compareKernels(new Lanczos(3.0), new LanczosNaive(3.0), 0.25);
    }

    @Test
    public void testLanczos4 () {
        compareKernels(new Lanczos(1.0), new LanczosNaive(1.0), 0.25);
    }
}
