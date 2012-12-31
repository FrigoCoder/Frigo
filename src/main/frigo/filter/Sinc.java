
package frigo.filter;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class Sinc extends ResamplingKernel {

    public Sinc () {
        this(3.0);
    }

    public Sinc (double radius) {
        super(radius);
    }

    @Override
    public double evaluate (double t) {
        if( !isInDomain(t) ){
            return 0.0;
        }
        if( t != 0.0 ){
            double nx = PI * t;
            return sin(nx) / nx;
        }
        return 1.0;
    }
}
