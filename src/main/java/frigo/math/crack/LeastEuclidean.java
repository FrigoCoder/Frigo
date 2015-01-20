
package frigo.math.crack;

import static java.lang.Math.abs;

import java.util.List;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class LeastEuclidean {

    private List<Long> r;
    private List<Long> q;

    public LeastEuclidean (long a, long b) {
        r = Lists.newArrayList(a, b);
        q = Lists.newArrayList();
        run();
    }

    private void run () {
        for( int i = 1; Iterables.getLast(r) != 0; i++ ){
            long quotient = quotient(r.get(i - 1), r.get(i));
            long remainder = r.get(i - 1) - r.get(i) * quotient;
            q.add(quotient);
            r.add(remainder);
        }
    }

    @VisibleForTesting
    static long quotient (long x, long y) {
        long q1 = x / y;
        long q2 = q1 + sgn(x) * sgn(y);
        long r1 = abs(x - y * q1);
        long r2 = abs(x - y * q2);
        if( r1 < r2 || r1 == r2 && sgn(x) >= 0 ){
            return q1;
        }
        return q2;
    }

    private static long sgn (long x) {
        return x == 0 ? 0 : x > 0 ? 1 : -1;
    }

    public List<Long> q () {
        return q;
    }

    public long gcd () {
        return Math.abs(r.get(r.size() - 2));
    }

}
