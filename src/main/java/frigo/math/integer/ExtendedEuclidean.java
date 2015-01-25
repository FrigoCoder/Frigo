
package frigo.math.integer;

public class ExtendedEuclidean extends EuclideanBase {

    public ExtendedEuclidean (long n, long x) {
        super(n, x);
    }

    @Override
    protected long quotient (long x, long y) {
        return x / y;
    }

}
