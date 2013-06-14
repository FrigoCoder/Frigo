
package frigo.math;

public interface FourierTransform {

    Complex[] inverse (Complex[] F);

    Complex[] forward (Complex[] T);
}
