
package frigo.electronics;

import frigo.math.Complex;

public class Series extends Element {

    private Element[] elements;

    public Series (Element... elements) {
        this.elements = elements;
    }

    @Override
    public Complex impedance (double w) {
        Complex sum = Complex.ZERO;
        for( Element element : elements ){
            sum = sum.add(element.impedance(w));
        }
        return sum;
    }

}
