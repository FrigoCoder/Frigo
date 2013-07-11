
package frigo.math.fourier;

import org.junit.Test;

import frigo.math.Complex;
import frigo.math.fourier.DFT;
import frigo.math.fourier.FFT;

public class FFTTest extends FourierTransformTestBase {

    public FFTTest () {
        super(new FFT(), new DFT(), 1024, 1.0e-20);
    }

    @Test
    public void testDFTAgainstFFT () {
        testForwardAgainstOtherForward();
    }

    @Test
    public void testDFTAgainstIDFT () {
        testForwardAgainstInverse();
    }

    @Test
    public void testDFTAgainstIFFT () {
        testForwardAgainstOtherInverse();
    }

    @Test
    public void testDFTAgainstPureFrequency () {
        testForwardAgainstPureFrequency();
    }

    @Test
    public void testIDFTAgainstIFFT () {
        testInverseAgainstOtherInverse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonPowerOfTwoArray () {
        fourier.forward(new Complex[n - 1]);
    }
}
