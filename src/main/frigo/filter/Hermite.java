
package frigo.filter;

public class Hermite extends ResamplingKernel {

    public Hermite () {
        super(1.0);
    }

    @Override
    public double evaluate (double t) {
        if( !isInDomain(t) ){
            return 0.0;
        }
        double x = Math.abs(t);
        return (x + x - 3.0) * x * x + 1.0;
    }
}
