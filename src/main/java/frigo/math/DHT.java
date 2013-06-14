
package frigo.math;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class DHT extends HartleyTransform {

    @Override
    public double[] forward (double[] T) {
        double[] H = core(T);
        normalize(H);
        return H;
    }

    @Override
    public double[] inverse (double[] H) {
        return core(H);
    }

    protected double[] core (double[] T) {
        int n = T.length;
        double[] F = new double[n];
        for( int f = 0; f < n; f++ ){
            double sum = 0.0;
            for( int t = 0; t < n; t++ ){
                sum += T[t] * (cos(2.0 * PI * f * t / n) + sin(2.0 * PI * f * t / n));
            }
            F[f] = sum;
        }
        return F;
    }

    private void normalize (double[] H) {
        for( int i = 0; i < H.length; i++ ){
            H[i] /= H.length;
        }
    }

}
