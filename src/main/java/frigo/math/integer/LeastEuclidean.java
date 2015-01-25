
package frigo.math.integer;

import static frigo.math.integer.MathInt.sgn;
import static java.lang.Math.abs;

public class LeastEuclidean extends EuclideanBase {

    public LeastEuclidean (long n, long x) {
        super(n, x);
    }

    @Override
    protected long quotient (long x, long y) {
        long q1 = x / y;
        long q2 = q1 + sgn(x) * sgn(y);
        long r1 = abs(x - y * q1);
        long r2 = abs(x - y * q2);
        if( r1 < r2 || r1 == r2 && sgn(x) >= 0 ){
            return q1;
        }
        return q2;
    }

}
