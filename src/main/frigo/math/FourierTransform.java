
package frigo.math;

public interface FourierTransform {

    Complex[] inverse (Complex[] F);

    Complex[] transform (Complex[] T);
}
