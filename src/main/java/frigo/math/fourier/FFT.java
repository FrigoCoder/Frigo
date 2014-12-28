
package frigo.math.fourier;

import static com.google.common.base.Preconditions.checkArgument;
import static frigo.math.integer.MathAux.isPowerOfTwo;
import static java.lang.Math.PI;
import frigo.math.Complex;

/**
 * Radix-2 Decimation In Frequency Fast Fourier Transform. Implemented based on the ideas of the Fast Hartley Transform.
 */
public class FFT implements FourierTransform {

    @Override
    public Complex[] forward (Complex[] T) {
        Complex[] F = T.clone();
        core(F, -1.0);
        normalize(F);
        return F;
    }

    @Override
    public Complex[] inverse (Complex[] F) {
        Complex[] T = F.clone();
        core(T, 1.0);
        return T;
    }

    private void core (Complex[] v, double sign) {
        checkArgument(isPowerOfTwo(v.length), "Array length must be power of two");
        for( int blockSize = v.length; blockSize > 1; blockSize /= 2 ){
            Complex root = Complex.cis(sign * 2.0 * PI / blockSize);
            Complex twiddle = new Complex(1.0, 0.0);
            for( int i = 0; i < blockSize / 2; i++ ){
                for( int x1 = i; x1 < v.length; x1 += blockSize ){
                    int x2 = x1 + blockSize / 2;
                    Complex e = v[x1];
                    Complex f = v[x2];
                    v[x1] = e.add(f);
                    v[x2] = e.sub(f).mul(twiddle);
                }
                twiddle = twiddle.mul(root);
            }
        }
        bitReverse(v);
    }

    private void bitReverse (Complex[] v) {
        int p = Integer.numberOfLeadingZeros(v.length - 1);
        for( int i = 0; i < v.length; i++ ){
            int j = Integer.reverse(i) >>> p;
            if( i < j ){
                swap(v, i, j);
            }
        }
    }

    private void swap (Complex[] v, int i, int j) {
        Complex t = v[i];
        v[i] = v[j];
        v[j] = t;
    }

    private void normalize (Complex[] F) {
        for( int f = 0; f < F.length; f++ ){
            F[f] = F[f].div(F.length);
        }
    }

}
