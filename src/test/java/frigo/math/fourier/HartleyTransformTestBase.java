
package frigo.math.fourier;

import java.util.Random;

import frigo.math.TransformTestBase;
import frigo.math.fourier.HartleyTransform;

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
        checkDifference(t, hartley.inverse(hartley.forward(t)));
    }

    protected void testForwardAgainstOtherForward () {
        double[] t = getRandomDoubleArray(n);
        checkDifference(other.forward(t), hartley.forward(t));
    }

    protected void testForwardAgainstOtherInverse () {
        double[] t = getRandomDoubleArray(n);
        checkDifference(t, other.inverse(hartley.forward(t)));
    }

    protected void testForwardAgainstPureFrequency () {
        Random rand = new Random();
        int frequency = rand.nextInt(n);
        double[] expected = new double[n];
        for( int i = 0; i < n; i++ ){
            expected[i] = i == frequency ? 1.0 : 0.0;
        }
        checkDifference(expected, hartley.forward(getHartleyFrequency(frequency)));
    }

    protected void testInverseAgainstOtherInverse () {
        double[] frequencyDomain = getRandomDoubleArray(n);
        checkDifference(other.inverse(frequencyDomain), hartley.inverse(frequencyDomain));
    }

    private double[] getHartleyFrequency (int frequency) {
        double[] timeDomain = new double[n];
        for( int i = 0; i < n; i++ ){
            timeDomain[i] = Math.cos(2.0 * Math.PI * frequency * i / n) + Math.sin(2.0 * Math.PI * frequency * i / n);
        }
        return timeDomain;
    }

}
