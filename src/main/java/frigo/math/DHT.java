
package frigo.math;

/**
 * Trivial implementation of the Discrete Hartley Transform mainly used for testing purposes
 * 
 * @author Frigo
 */
public class DHT implements HartleyTransform {

    @Override
    public double[] fromFourier (Complex[] F) {
        FFT fft = new FFT();
        Complex[] T = fft.inverse(F);
        double[] R = new double[T.length];
        for( int i = 0; i < T.length; i++ ){
            R[i] = T[i].re;
        }
        return transform(R);
    }

    @Override
    public double[] inverse (double[] H) {
        return core(H);
    }

    @Override
    public Complex[] toFourier (double[] H) {
        double[] R = inverse(H);
        Complex[] T = new Complex[R.length];
        for( int i = 0; i < T.length; i++ ){
            T[i] = new Complex(R[i]);
        }
        FFT fft = new FFT();
        return fft.transform(T);
    }

    @Override
    public double[] transform (double[] T) {
        double[] F = core(T);
        for( int i = 0; i < F.length; i++ ){
            F[i] /= F.length;
        }
        return F;
    }

    protected double[] core (double[] T) {
        int n = T.length;
        double[] F = new double[n];
        for( int f = 0; f < n; f++ ){
            double sum = 0.0;
            for( int t = 0; t < n; t++ ){
                sum += T[t] * (Math.cos(2.0 * Math.PI * f * t / n) + Math.sin(2.0 * Math.PI * f * t / n));
            }
            F[f] = sum;
        }
        return F;
    }
}
