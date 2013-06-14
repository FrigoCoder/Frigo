
package frigo.math;

/**
 * Discrete Fourier Transform implemented according to <a
 * href=http://en.wikipedia.org/wiki/Discrete_Fourier_transform#Definition>Wikipedia</a>. The forward transform
 * normalizes the result by 1/n, so the transform of an array of [1.0, 1.0, ...] will result in an array of [1.0, 0.0,
 * ...]. The inverse transform does not normalize anything.
 */
public class DFT implements FourierTransform {

    @Override
    public Complex[] inverse (Complex[] F) {
        return core(F, 1.0);
    }

    @Override
    public Complex[] transform (Complex[] T) {
        Complex[] F = core(T, -1.0);
        for( int f = 0; f < F.length; f++ ){
            F[f] = F[f].div(F.length);
        }
        return F;
    }

    private Complex[] core (Complex[] T, double sign) {
        int n = T.length;
        Complex[] F = new Complex[n];
        for( int f = 0; f < n; f++ ){
            Complex root = Complex.cis(sign * 2.0 * Math.PI * f / n);
            Complex twiddle = new Complex(1.0, 0.0);
            F[f] = new Complex(0.0, 0.0);
            for( int t = 0; t < n; t++ ){
                F[f] = F[f].add(T[t].mul(twiddle));
                twiddle = twiddle.mul(root);
            }
        }
        return F;
    }
}
