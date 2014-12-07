
package frigo.math;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class SqrtApproximation {

    public static void main (String[] args) {
        int limit = 100_000;
        FactorizerSieve sieve = new FactorizerSieve(limit);

        double max = 0;
        double sum = 0;
        int count = 0;
        for( long n = 6; n < limit; n++ ){
            List<Integer> factors = sieve.factor((int) n);
            if( factors.size() == 2 && factors.get(0) != factors.get(1) ){
                SqrtApproximation approx = new SqrtApproximation(n);
                int steps = approx.run();
                System.out.println("n=" + n + ", steps=" + steps);
                max = Math.max(max, (double) steps / n);
                sum += (double) steps / n;
                count++;
            }
        }
        System.out.println("Largest=" + max);
        System.out.println("Average=" + sum / count);
    }

    private long n;
    private long x;
    private long y;
    private List<Long> A;
    private List<Long> B;

    public SqrtApproximation (long n) {
        this.n = n;
        x = 2 * MathAux.isqrt(n);
        y = n - x * x / 4;
        // System.out.println("n=" + n + " = " + x / 2 + "*" + x / 2 + "+" + y);
        A = Lists.newArrayList(1L, x / 2);
        B = Lists.newArrayList(0L, 1L);
        print(0);
        print(1);
    }

    private void print (int i) {
        long a = A.get(i);
        long a2 = a * a % n;
        long b = B.get(i);
        long b2 = b * b % n;
        // System.out.println("i=" + i + ", A=" + a + ", B=" + b + ", A2=" + a2 + ", B2=" + b2);
    }

    private long current (List<Long> list) {
        return list.get(list.size() - 1);
    }

    private long previous (List<Long> list) {
        return list.get(list.size() - 2);
    }

    public int run () {
        while( !isDone() ){
            recurse();
            print(A.size() - 1);
        }
        return A.size();
    }

    private boolean isDone () {
        long a = current(A);
        long b = current(B);
        List<Long> gcds = new ArrayList<>();
        gcds.add(gcd(n, a + 1));
        gcds.add(gcd(n, a - 1));
        gcds.add(gcd(n, b + 1));
        gcds.add(gcd(n, b - 1));
        gcds.add(gcd(n, a + b));
        gcds.add(gcd(n, Math.abs(a - b)));
        for( int i = 0; i < gcds.size(); i++ ){
            long gcd = gcds.get(i);
            if( gcd != 1 && gcd != n ){
                // System.out.println("gcd[" + i + "]=" + gcd);
                return true;
            }
        }
        return false;
    }

    private void recurse () {
        A.add((x * current(A) + y * previous(A)) % n);
        B.add((x * current(B) + y * previous(B)) % n);
    }

    private static long gcd (long u, long v) {
        if( u < 0 || v < 0 ){
            return gcd(Math.abs(u), Math.abs(v));
        }
        if( u == 0 || u == v ){
            return v;
        }
        if( v == 0 ){
            return u;
        }
        if( even(u) ){
            return even(v) ? gcd(u >> 1, v >> 1) : gcd(u >> 1, v);
        }
        if( even(v) ){
            return gcd(u, v >> 1);
        }
        return u > v ? gcd(u - v >> 1, v) : gcd(u, v - u >> 1);
    }

    private static boolean even (long u) {
        return (u & 1) == 0;
    }

}
