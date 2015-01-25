
package frigo.math.crack;

import java.util.List;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import frigo.math.integer.MathInt;

public class ExtendedEuclidean {

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

    private List<Long> q;
    private List<Long> r;

    public ExtendedEuclidean (long n, long x) {
        q = Lists.newArrayList();
        r = Lists.newArrayList(n, x);
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
        return x / y;
    }

    public List<Long> q () {
        return q;
    }

    public long gcd () {
        return r.get(r.size() - 2);
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
        return MathInt.mod(invSigned(), n());
    }

}
