
package frigo.electronics;

import static frigo.math.Complex.ZERO;
import frigo.math.Complex;

public class Parallel extends Element {

    private Element[] elements;

    public Parallel (Element... elements) {
        this.elements = elements;
    }

    @Override
    public Complex impedance (double w) {
        Complex sum = ZERO;
        for( Element element : elements ){
            Complex impedanceInverse = element.impedance(w).inv();
            sum = sum.add(impedanceInverse);
        }
        return sum.inv();
    }

}
