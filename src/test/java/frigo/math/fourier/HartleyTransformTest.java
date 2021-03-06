
package frigo.math.fourier;

import org.junit.Test;

import frigo.math.Complex;
import frigo.math.TransformTestBase;

public class HartleyTransformTest extends TransformTestBase {

    public HartleyTransformTest () {
        super(1024, 1.0e-20);
    }

    @Test
    public void test_fromFourier () {
        double[] R = getRandomDoubleArray(n);
        Complex[] T = new Complex[n];
        for( int i = 0; i < n; i++ ){
            T[i] = new Complex(R[i]);
        }
        FFT fft = new FFT();
        Complex[] F = fft.forward(T);

        FHT fht = new FHT();
        double[] H = fht.forward(R);

        checkDifference(HartleyTransform.fromFourier(F), H);
    }

    @Test
    public void test_toFourier () {
        double[] R = getRandomDoubleArray(n);
        Complex[] T = new Complex[n];
        for( int i = 0; i < n; i++ ){
            T[i] = new Complex(R[i]);
        }
        FFT fft = new FFT();
        Complex[] F = fft.forward(T);

        FHT fht = new FHT();
        double[] H = fht.forward(R);

        checkDifference(HartleyTransform.toFourier(H), F);
    }

}
