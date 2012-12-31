
package frigo.filter;

import static java.lang.Math.sin;

public class Lanczos extends ResamplingKernel {

    protected final double piPerRadius;

    public Lanczos () {
        this(3.0);
    }

    public Lanczos (double radius) {
        super(radius);
        piPerRadius = Math.PI / radius;
    }

    @Override
    public double evaluate (double t) {
        if( !isInDomain(t) ){
            return 0.0;
        }
        if( t != 0.0 ){
            double x = piPerRadius * t;
            double nx = Math.PI * t;
            return sin(nx) * sin(x) / (nx * x);
        }
        return 1.0;
    }
}
