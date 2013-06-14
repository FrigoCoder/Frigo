
package frigo.math;

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
        for( int i = 0; i <= n / 2; i++ ){
            H[i] = F[i].re - F[i].im;
        }
        for( int i = 1; i <= n / 2; i++ ){
            H[n - i] = F[i].re + F[i].im;
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
        for( int i = 1; i < n; i++ ){
            F[i] = new Complex(H[i] + H[n - i], H[n - i] - H[i]).mul(0.5);
        }
        return F;
    }

    public abstract double[] forward (double[] T);

    public abstract double[] inverse (double[] H);
}
