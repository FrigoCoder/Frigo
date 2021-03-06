
package frigo.math.crack;

import java.util.List;

import com.google.common.collect.Lists;

public class GeneralizedContinuedFractionUnderN {

    private List<Long> A;
    private List<Long> B;
    private long n;

    public GeneralizedContinuedFractionUnderN (long b0, long n) {
        A = Lists.newArrayList(1L, b0);
        B = Lists.newArrayList(0L, 1L);
        this.n = n;
    }

    public long getA () {
        return current(A);
    }

    public long getB () {
        return current(B);
    }

    public void recurse (long b, long a) {
        A.add(mod(b * current(A) + a * previous(A)));
        B.add(mod(b * current(B) + a * previous(B)));
    }

    private long mod (long x) {
        return x >= 0 ? x % n : x % n + n;
    }

    private long current (List<Long> list) {
        return list.get(list.size() - 1);
    }

    private long previous (List<Long> list) {
        return list.get(list.size() - 2);
    }

}
