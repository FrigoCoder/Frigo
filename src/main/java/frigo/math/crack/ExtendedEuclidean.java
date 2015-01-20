
package frigo.math.crack;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Based on http://en.wikipedia.org/wiki/Extended_Euclidean_algorithm#Description_of_the_algorithm
 */

public class ExtendedEuclidean {

    public static long nFromQCompact (List<Long> q) {
        long r0 = 0;
        long r1 = 1;
        for( int i = 0; i < q.size(); i++ ){
            long r2 = r0 + r1 * q.get(i);
            r0 = r1;
            r1 = r2;
        }
        return r1;
    }

    public static long kFromQCompact (List<Long> q) {
        long r0 = 0;
        long r1 = 1;
        for( int i = 0; i < q.size(); i++ ){
            long r2 = r0 + r1 * q.get(i);
            r0 = r1;
            r1 = r2;
        }
        return r0;
    }

    public List<Long> q;
    public List<Long> r;
    public List<Long> s;
    public List<Long> t;

    public ExtendedEuclidean (long a, long b) {
        q = Lists.newArrayList(0L);
        r = Lists.newArrayList(a, b);
        s = Lists.newArrayList(1L, 0L);
        t = Lists.newArrayList(0L, 1L);
    }

    public void run () {
        for( int i = 1; Iterables.getLast(r) != 0; i++ ){
            q.add(r.get(i - 1) / r.get(i));
            r.add(r.get(i - 1) - r.get(i) * q.get(i));
            s.add(s.get(i - 1) - s.get(i) * q.get(i));
            t.add(t.get(i - 1) - t.get(i) * q.get(i));
        }
        q.add(0L);
    }

    public long a () {
        return r.get(0);
    }

    public long b () {
        return r.get(1);
    }

    public long gcd () {
        return r.get(r.size() - 2);
    }

    public long binvSigned () {
        return t.get(t.size() - 2);
    }

    public long binv () {
        return (t.get(t.size() - 2) + a()) % a();
    }

    public long k () {
        return s.get(s.size() - 2);
    }

    public List<Long> qshort () {
        List<Long> shortq = new ArrayList<>(q);
        shortq.remove(0);
        shortq.remove(shortq.size() - 1);
        return shortq;
    }

}
