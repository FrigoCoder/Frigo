
package frigo.filter;

public class Linear extends ResamplingKernel {

    public Linear () {
        super(1.0);
    }

    @Override
    public double evaluate (double x) {
        if( !isInDomain(x) ){
            return 0.0;
        }
        return 1.0 - Math.abs(x);
    }
}
