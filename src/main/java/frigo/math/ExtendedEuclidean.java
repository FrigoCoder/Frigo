
package frigo.math;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Based on http://en.wikipedia.org/wiki/Extended_Euclidean_algorithm#Description_of_the_algorithm
 */

public class ExtendedEuclidean {

    public static int nFromQCompact (List<Integer> q) {
        int r0 = 0;
        int r1 = 1;
        for( int i = 0; i < q.size(); i++ ){
            int r2 = r0 + r1 * q.get(i);
            r0 = r1;
            r1 = r2;
        }
        return r1;
    }

    public static int kFromQCompact (List<Integer> q) {
        int r0 = 0;
        int r1 = 1;
        for( int i = 0; i < q.size(); i++ ){
            int r2 = r0 + r1 * q.get(i);
            r0 = r1;
            r1 = r2;
        }
        return r0;
    }

    public List<Integer> q;
    public List<Integer> r;
    public List<Integer> s;
    public List<Integer> t;

    public ExtendedEuclidean (int a, int b) {
        q = Lists.newArrayList(0);
        r = Lists.newArrayList(a, b);
        s = Lists.newArrayList(1, 0);
        t = Lists.newArrayList(0, 1);
    }

    public void run () {
        for( int i = 1; Iterables.getLast(r) != 0; i++ ){
            q.add(r.get(i - 1) / r.get(i));
            r.add(r.get(i - 1) - r.get(i) * q.get(i));
            s.add(s.get(i - 1) - s.get(i) * q.get(i));
            t.add(t.get(i - 1) - t.get(i) * q.get(i));
        }
        q.add(0);
    }

    public int a () {
        return r.get(0);
    }

    public int b () {
        return r.get(1);
    }

    public int gcd () {
        return r.get(r.size() - 2);
    }

    public int binvSigned () {
        return t.get(t.size() - 2);
    }

    public int binv () {
        return (t.get(t.size() - 2) + a()) % a();
    }

    public int k () {
        return s.get(s.size() - 2);
    }

    public List<Integer> qshort () {
        List<Integer> shortq = new ArrayList<>(q);
        shortq.remove(0);
        shortq.remove(shortq.size() - 1);
        return shortq;
    }

}
