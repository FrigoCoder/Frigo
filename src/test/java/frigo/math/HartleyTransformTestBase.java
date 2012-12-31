
package frigo.math;

import static frigo.util.ArraysAux.getRandomDoubleArray;
import java.util.Random;

public class HartleyTransformTestBase extends TransformTestBase {

    private final HartleyTransform hartley;
    private final HartleyTransform other;

    protected HartleyTransformTestBase (HartleyTransform hartley, HartleyTransform other, int n, double epsilon) {
        super(n, epsilon);
        this.hartley = hartley;
        this.other = other;
    }

    protected void testForwardAgainstInverse () {
        double[] t = getRandomDoubleArray(n);
        checkDifference(t, hartley.inverse(hartley.transform(t)));
    }

    protected void testForwardAgainstOtherForward () {
        double[] t = getRandomDoubleArray(n);
        checkDifference(other.transform(t), hartley.transform(t));
    }

    protected void testForwardAgainstOtherInverse () {
        double[] t = getRandomDoubleArray(n);
        checkDifference(t, other.inverse(hartley.transform(t)));
    }

    protected void testForwardAgainstPureFrequency () {
        Random rand = new Random();
        int frequency = rand.nextInt(n);
        double[] expected = new double[n];
        for( int i = 0; i < n; i++ ){
            expected[i] = i == frequency ? 1.0 : 0.0;
        }
        checkDifference(expected, hartley.transform(getHartleyFrequency(frequency)));
    }

    protected void testFromFourier () {
        double[] R = getRandomDoubleArray(n);
        Complex[] T = new Complex[n];
        for( int i = 0; i < n; i++ ){
            T[i] = new Complex(R[i]);
        }
        FFT fft = new FFT();
        Complex[] F = fft.transform(T);
        checkDifference(hartley.fromFourier(F), other.fromFourier(F));
    }

    protected void testInverseAgainstOtherInverse () {
        double[] frequencyDomain = getRandomDoubleArray(n);
        checkDifference(other.inverse(frequencyDomain), hartley.inverse(frequencyDomain));
    }

    protected void testToFourier () {
        double[] H = getRandomDoubleArray(n);
        checkDifference(hartley.toFourier(H), other.toFourier(H));
    }

    private double[] getHartleyFrequency (int frequency) {
        double[] timeDomain = new double[n];
        for( int i = 0; i < n; i++ ){
            timeDomain[i] = Math.cos(2.0 * Math.PI * frequency * i / n) + Math.sin(2.0 * Math.PI * frequency * i / n);
        }
        return timeDomain;
    }
}
