
package frigo.filter;

import org.junit.Test;

public class GaussTest extends KernelTestBase {

    class GaussNaive extends Gauss {

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
        compareKernels(new Gauss(), new GaussNaive(0.5, 2.5), 0.25);
    }

    @Test
    public void testGauss0_75 () {
        compareKernels(new Gauss(0.75), new GaussNaive(0.75, 0.75 * 5), 0.25);
    }

    @Test
    public void testGauss1_5_100_0 () {
        compareKernels(new Gauss(1.5, 100), new GaussNaive(1.5, 100), 0.25);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGaussNegativeSigma () {
        kernel = new Gauss(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGaussZeroSigma () {
        kernel = new Gauss(0);
    }
}
