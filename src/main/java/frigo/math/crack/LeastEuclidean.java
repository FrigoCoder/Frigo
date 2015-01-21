
package frigo.math.crack;

import static frigo.math.integer.MathInt.sgn;
import static java.lang.Math.abs;

import java.util.List;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class LeastEuclidean {

    public static long evaluate (List<Long> q) {
        long prev = 0;
        long actual = 1;
        for( long quotient : q ){
            long next = prev - actual * quotient;
            prev = actual;
            actual = next;
        }
        return actual;
    }

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

    public List<Long> q () {
        return q;
    }

    public long gcd () {
        return Math.abs(r.get(r.size() - 2));
    }

    public long n () {
        return r.get(0);
    }

    public long x () {
        return r.get(1);
    }

    public long invAbs () {
        return Math.abs(invSigned());
    }

    public long invSigned () {
        return evaluate(q.subList(0, q.size() - 1));
    }

    public long invUnsigned () {
        return (invSigned() + n()) % n();
    }

}
