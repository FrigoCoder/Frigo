
package frigo.math;

import org.junit.Test;

public class DFTTest extends FourierTransformTestBase {

    public DFTTest () {
        super(new DFT(), new FFT(), 1024, 1.0e-20);
    }

    @Test
    public void testFFTAgainstDFT () {
        testForwardAgainstOtherForward();
    }

    @Test
    public void testFFTAgainstIDFT () {
        testForwardAgainstOtherInverse();
    }

    @Test
    public void testFFTAgainstIFFT () {
        testForwardAgainstInverse();
    }

    @Test
    public void testFFTAgainstPureFrequency () {
        testForwardAgainstPureFrequency();
    }

    @Test
    public void testIFFTAgainstIDFT () {
        testInverseAgainstOtherInverse();
    }
}
