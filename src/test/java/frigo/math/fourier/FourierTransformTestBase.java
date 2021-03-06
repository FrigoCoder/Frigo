
package frigo.math.fourier;

import static java.lang.Math.PI;

import java.util.Random;

import frigo.math.Complex;
import frigo.math.TransformTestBase;

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
        checkDifference(source, fourier.inverse(fourier.forward(source)));
    }

    protected void testForwardAgainstOtherForward () {
        Complex[] timeDomain = getRandomComplexArray(n);
        checkDifference(other.forward(timeDomain), fourier.forward(timeDomain));
    }

    protected void testForwardAgainstOtherInverse () {
        Complex[] source = getRandomComplexArray(n);
        checkDifference(source, other.inverse(fourier.forward(source)));
    }

    protected void testForwardAgainstPureFrequency () {
        Random rand = new Random();
        int frequency = rand.nextInt(n);
        Complex[] expected = new Complex[n];
        for( int i = 0; i < n; i++ ){
            expected[i] = i == frequency ? Complex.ONE : Complex.ZERO;
        }
        checkDifference(expected, fourier.forward(getFourierFrequency(frequency)));
    }

    protected void testInverseAgainstOtherInverse () {
        Complex[] frequencyDomain = getRandomComplexArray(n);
        checkDifference(other.inverse(frequencyDomain), fourier.inverse(frequencyDomain));
    }

    private Complex[] getFourierFrequency (int frequency) {
        Complex[] timeDomain = new Complex[n];
        for( int i = 0; i < n; i++ ){
            timeDomain[i] = Complex.cis(2.0 * PI * frequency * i / n);
        }
        return timeDomain;
    }
}
