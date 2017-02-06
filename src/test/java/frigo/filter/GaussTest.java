
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GaussTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static class GaussNaive extends Gauss {

        private final double a;
        private final double c;

        public GaussNaive (double sigma, double radius) {
            super(sigma, radius);
            a = 1 / (sigma * Math.sqrt(2 * Math.PI));
            c = sigma;
        }

        @Override
        public double evaluate (double x) {
            if( !isInDomain(x) ){
                return 0;
            }
            return a * Math.exp(-(x * x) / (2 * c * c));
        }
    }

    @Test
    public void testGauss () {
        assertKernelEquals(new Gauss(), new GaussNaive(0.5, 2.5), 0.25);
    }

    @Test
    public void testGauss0_75 () {
        assertKernelEquals(new Gauss(0.75), new GaussNaive(0.75, 0.75 * 5), 0.25);
    }

    @Test
    public void testGauss1_5_100_0 () {
        assertKernelEquals(new Gauss(1.5, 100), new GaussNaive(1.5, 100), 0.25);
    }

    @SuppressWarnings("unused")
    @Test
    public void testGaussNegativeSigma () {
        thrown.expect(IllegalArgumentException.class);
        new Gauss(-1, 0);
    }

    @SuppressWarnings("unused")
    @Test
    public void testGaussZeroSigma () {
        thrown.expect(IllegalArgumentException.class);
        new Gauss(0);
    }
}
