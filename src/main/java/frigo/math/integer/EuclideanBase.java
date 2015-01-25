
package frigo.math.integer;

import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public abstract class EuclideanBase {

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

    public EuclideanBase (long n, long x) {
        q = Lists.newArrayList();
        r = Lists.newArrayList(n, n == 0 ? x : MathInt.mod(x, n));
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

    protected abstract long quotient (long x, long y);

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
        return MathInt.mod(invSigned(), n());
    }

}
