
package frigo.filter;

import static java.lang.Math.abs;

public abstract class SymmetricCubic extends ResamplingKernel {

    protected double p, q, r, s;
    protected double t, u, v, w;

    public SymmetricCubic () {
        super(2.0);
    }

    @Override
    public double evaluate (double signedx) {
        if( !isInDomain(signedx) ){
            return 0;
        }
        double x = abs(signedx);
        if( x < 1 ){
            return ((p * x + q) * x + r) * x + s;
        }
        return ((t * x + u) * x + v) * x + w;
    }
}
