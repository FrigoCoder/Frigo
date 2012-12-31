
package frigo.math;

public interface HartleyTransform {

    double[] fromFourier (Complex[] F);

    double[] inverse (double[] H);

    Complex[] toFourier (double[] H);

    double[] transform (double[] T);
}
