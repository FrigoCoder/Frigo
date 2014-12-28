
package frigo.filter;

import static frigo.math.integer.MathAux.sqr;

public class Bell extends ResamplingKernel {

    public Bell () {
        super(1.5);
    }

    @Override
    public double evaluate (double t) {
        if( !isInDomain(t) ){
            return 0.0;
        }
        double x = Math.abs(t);
        if( x > 0.5 ){
            return 0.5 * sqr(x - 1.5);
        }
        return 0.75 - x * x;
    }
}
