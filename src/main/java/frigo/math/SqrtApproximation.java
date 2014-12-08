
package frigo.math;

import java.util.ArrayList;
import java.util.List;

public class SqrtApproximation {

    public static void main (String[] args) {
        int limit = 20_000;
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

    private GeneralizedContinuedFractionUnderN fraction;

    public SqrtApproximation (long n) {
        this.n = n;
        x = 2 * MathAux.isqrt(n);
        y = n - MathAux.isqrt(n) * MathAux.isqrt(n);
        fraction = new GeneralizedContinuedFractionUnderN(MathAux.isqrt(n), n);
    }

    public int run () {
        int steps = 2;
        while( !isDone() ){
            fraction.recurse(x, y);
            steps++;
        }
        return steps;
    }

    private boolean isDone () {
        long a = fraction.getA();
        long b = fraction.getB();
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
                return true;
            }
        }
        return false;
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
