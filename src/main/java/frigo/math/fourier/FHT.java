
package frigo.math.fourier;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Integer.numberOfLeadingZeros;
import static java.lang.Math.PI;

import frigo.math.Complex;
import frigo.math.integer.MathInt;

/**
 * Fast Hartley Transform with Decimation In Frequency based on <a
 * href=http://vault.embedded.com/2000/0009/0009feat3.htm>Doing Hartley Smartly</a>.
 **/
public class FHT extends HartleyTransform {

    @Override
    public double[] forward (double[] T) {
        double[] H = T.clone();
        core(H);
        normalize(H);
        return H;
    }

    @Override
    public double[] inverse (double[] H) {
        double[] T = H.clone();
        core(T);
        return T;
    }

    private void core (double[] v) {
        checkArgument(MathInt.isPowerOfTwo(v.length), "Array length must be power of two");
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

    private void coreBig (double[] v, int blockSize) {
        Complex mul = Complex.cis(2.0 * PI / blockSize);
        for( int start = 0; start < v.length; start += blockSize ){
            butterflyBig(v, start, blockSize, mul);
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

    private void core4 (double[] v) {
        for( int start = 0; start < v.length; start += 4 ){
            butterfly4(v, start);
        }
    }

    private void butterfly4 (double[] v, int start) {
        for( int index = 0; index <= 4 / 4; index++ ){
            double e = v[start + index];
            double f = v[start + 4 / 2 + index];
            v[start + index] = e + f;
            v[start + 4 / 2 + index] = e - f;
        }
    }

    private void core2 (double[] v) {
        for( int start = 0; start < v.length; start += 2 ){
            butterfly2(v, start);
        }
    }

    private void butterfly2 (double[] v, int start) {
        double e = v[start];
        double f = v[start + 2 / 2];
        v[start] = e + f;
        v[start + 2 / 2] = e - f;
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

    private void swap (double[] v, int i, int j) {
        double t = v[i];
        v[i] = v[j];
        v[j] = t;
    }

    private void normalize (double[] H) {
        for( int f = 0; f < H.length; f++ ){
            H[f] /= H.length;
        }
    }

}
