
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelEquals;
import static frigo.math.MathAux.sinc;

import org.junit.Test;

public class LanczosTest {

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
        assertKernelEquals(new Lanczos(), new LanczosNaive(3.0), 0.25);
    }

    @Test
    public void testLanczos1 () {
        assertKernelEquals(new Lanczos(1.0), new LanczosNaive(1.0), 0.25);
    }

    @Test
    public void testLanczos2 () {
        assertKernelEquals(new Lanczos(2.0), new LanczosNaive(2.0), 0.25);
    }

    @Test
    public void testLanczos3 () {
        assertKernelEquals(new Lanczos(3.0), new LanczosNaive(3.0), 0.25);
    }

    @Test
    public void testLanczos4 () {
        assertKernelEquals(new Lanczos(1.0), new LanczosNaive(1.0), 0.25);
    }
}
