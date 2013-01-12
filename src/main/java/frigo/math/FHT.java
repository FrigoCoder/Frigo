
package frigo.math;

import static com.google.common.base.Preconditions.checkArgument;
import static frigo.math.MathAux.isPowerOfTwo;
import static java.lang.Integer.numberOfLeadingZeros;

/**
 * Fast Hartley Transform with Decimation In Frequency based on <a
 * href=http://vault.embedded.com/2000/0009/0009feat3.htm>Doing Hartley Smartly</a>
 **/
public class FHT implements HartleyTransform {

    /**
     * Converts from Fourier representation to Hartley representation based on <a
     * href=http://en.wikipedia.org/wiki/Hartley_transform#Relation_to_Fourier_transform>the Wikipedia page on Hartley
     * Transform</a>. Does not check whether the Fourier representation describes a real valued signal or a complex one,
     * it assumes a real one.
     **/
    @Override
    public double[] fromFourier (Complex[] F) {
        int n = F.length;
        double[] H = new double[n];
        for( int i = 0; i <= n / 2; i++ ){
            H[i] = F[i].re - F[i].im;
        }
        for( int i = 1; i <= n / 2; i++ ){
            H[n - i] = F[i].re + F[i].im;
        }
        return H;
    }

    @Override
    public double[] inverse (double[] H) {
        double[] T = H.clone();
        core(T);
        return T;
    }

    /**
     * Converts from Hartley representation to Fourier representation based on <a
     * href=http://en.wikipedia.org/wiki/Hartley_transform#Relation_to_Fourier_transform>the Wikipedia page on Hartley
     * Transform</a>.
     **/
    @Override
    public Complex[] toFourier (double[] H) {
        int n = H.length;
        Complex[] F = new Complex[n];
        F[0] = new Complex(H[0], 0.0);
        for( int i = 1; i < n; i++ ){
            F[i] = new Complex(H[i] + H[n - i], H[n - i] - H[i]).mul(0.5);
        }
        return F;
    }

    @Override
    public double[] transform (double[] T) {
        double[] H = T.clone();
        core(H);
        normalize(H);
        return H;
    }

    private void bitReverse (double[] v, int n) {
        int p = numberOfLeadingZeros(n - 1);
        for( int i = 0; i < n; i++ ){
            int j = Integer.reverse(i) >>> p;
            if( i < j ){
                swap(v, i, j);
            }
        }
    }

    private void butterfly2 (double[] v, int start) {
        double e = v[start];
        double f = v[start + 2 / 2];
        v[start] = e + f;
        v[start + 2 / 2] = e - f;
    }

    private void butterfly4 (double[] v, int start) {
        for( int index = 0; index <= 4 / 4; index++ ){
            double e = v[start + index];
            double f = v[start + 4 / 2 + index];
            v[start + index] = e + f;
            v[start + 4 / 2 + index] = e - f;
        }
    }

    private void butterflyBig (double[] v, int start, int blockSize, Complex mul) {
        Complex act = new Complex(1.0, 0.0);
        for( int index = 0; index <= blockSize / 4; index++ ){
            double e = v[start + index];
            double f = v[start + blockSize / 2 + index];
            if( index == 0 || index == blockSize / 4 ){
                v[start + index] = e + f;
                v[start + blockSize / 2 + index] = e - f;
            }else{
                double g = v[start + blockSize / 2 - index];
                double h = v[start + blockSize - index];
                Complex t = new Complex(e - f, h - g).mul(act);
                v[start + index] = e + f;
                v[start + blockSize / 2 + index] = t.re;
                v[start + blockSize / 2 - index] = g + h;
                v[start + blockSize - index] = t.im;
            }
            act = act.mul(mul);
        }
    }

    private void checkPowerOfTwo (double[] v) {
        checkArgument(isPowerOfTwo(v.length), "Array length must be power of two");
    }

    private void core (double[] v) {
        checkPowerOfTwo(v);
        for( int blockSize = v.length; blockSize >= 8; blockSize /= 2 ){
            coreBig(v, blockSize);
        }
        if( v.length >= 4 ){
            core4(v);
        }
        if( v.length >= 2 ){
            core2(v);
        }
        bitReverse(v, v.length);
    }

    private void core2 (double[] v) {
        for( int start = 0; start < v.length; start += 2 ){
            butterfly2(v, start);
        }
    }

    private void core4 (double[] v) {
        for( int start = 0; start < v.length; start += 4 ){
            butterfly4(v, start);
        }
    }

    private void coreBig (double[] v, int blockSize) {
        Complex mul = Complex.cis(2.0 * Math.PI / blockSize);
        for( int start = 0; start < v.length; start += blockSize ){
            butterflyBig(v, start, blockSize, mul);
        }
    }

    private void normalize (double[] H) {
        for( int f = 0; f < H.length; f++ ){
            H[f] /= H.length;
        }
    }

    private void swap (double[] v, int i, int j) {
        double t = v[i];
        v[i] = v[j];
        v[j] = t;
    }
}
