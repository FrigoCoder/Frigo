
package frigo.math.fourier;

import frigo.math.Complex;

public abstract class HartleyTransform {

    /**
     * Converts from Fourier representation to Hartley representation based on <a
     * href=http://en.wikipedia.org/wiki/Hartley_transform#Relation_to_Fourier_transform>the Wikipedia page on Hartley
     * Transform</a>. Does not check whether the Fourier representation describes a real valued signal or a complex one,
     * it assumes a real one.
     **/
    public static double[] fromFourier (Complex[] F) {
        int n = F.length;
        double[] H = new double[n];
        for( int f = 0; f <= n / 2; f++ ){
            H[f] = F[f].re - F[f].im;
        }
        for( int f = 1; f <= n / 2; f++ ){
            H[n - f] = F[f].re + F[f].im;
        }
        return H;
    }

    /**
     * Converts from Hartley representation to Fourier representation based on <a
     * href=http://en.wikipedia.org/wiki/Hartley_transform#Relation_to_Fourier_transform>the Wikipedia page on Hartley
     * Transform</a>.
     **/
    public static Complex[] toFourier (double[] H) {
        int n = H.length;
        Complex[] F = new Complex[n];
        F[0] = new Complex(H[0], 0.0);
        for( int f = 1; f < n; f++ ){
            F[f] = new Complex(H[f] + H[n - f], H[n - f] - H[f]).mul(0.5);
        }
        return F;
    }

    public abstract double[] forward (double[] T);

    public abstract double[] inverse (double[] H);
}
