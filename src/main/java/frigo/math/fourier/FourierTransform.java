
package frigo.math.fourier;

import frigo.math.Complex;

public interface FourierTransform {

    Complex[] inverse (Complex[] F);

    Complex[] forward (Complex[] T);
}
