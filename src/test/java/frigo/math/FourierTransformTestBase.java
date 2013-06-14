
package frigo.math;

import java.util.Random;

public class FourierTransformTestBase extends TransformTestBase {

    protected final FourierTransform fourier;
    protected final FourierTransform other;

    protected FourierTransformTestBase (FourierTransform fourier, FourierTransform other, int n, double epsilon) {
        super(n, epsilon);
        this.fourier = fourier;
        this.other = other;
    }

    protected void testForwardAgainstInverse () {
        Complex[] source = getRandomComplexArray(n);
        checkDifference(source, fourier.inverse(fourier.transform(source)));
    }

    protected void testForwardAgainstOtherForward () {
        Complex[] timeDomain = getRandomComplexArray(n);
        checkDifference(other.transform(timeDomain), fourier.transform(timeDomain));
    }

    protected void testForwardAgainstOtherInverse () {
        Complex[] source = getRandomComplexArray(n);
        checkDifference(source, other.inverse(fourier.transform(source)));
    }

    protected void testForwardAgainstPureFrequency () {
        Random rand = new Random();
        int frequency = rand.nextInt(n);
        Complex[] expected = new Complex[n];
        for( int i = 0; i < n; i++ ){
            expected[i] = i == frequency ? Complex.ONE : Complex.ZERO;
        }
        checkDifference(expected, fourier.transform(getFourierFrequency(frequency)));
    }

    protected void testInverseAgainstOtherInverse () {
        Complex[] frequencyDomain = getRandomComplexArray(n);
        checkDifference(other.inverse(frequencyDomain), fourier.inverse(frequencyDomain));
    }

    private Complex[] getFourierFrequency (int frequency) {
        Complex[] timeDomain = new Complex[n];
        for( int i = 0; i < n; i++ ){
            timeDomain[i] = Complex.cis(2.0 * Math.PI * frequency * i / n);
        }
        return timeDomain;
    }
}
