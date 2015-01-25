
package frigo.math.crack;

import java.util.List;
import java.util.NoSuchElementException;

import frigo.math.integer.LeastEuclidean;

public class EvaluatorLeast {

    public static void main (String[] args) {
        // long n = 11 * 17;
        // long n = 7 * 11;
        // long n = 3 * 5;
        long n = 3 * 7;
        EvaluatorLeast whatever = new EvaluatorLeast(n);
        whatever.run();
    }

    private long n;
    private long sqrt1;

    public EvaluatorLeast (long n) {
        this.n = n;
        sqrt1 = findSquareRootOf1();
        System.out.println("n=" + n + ", sqrt1=" + sqrt1 + ", k=" + (sqrt1 * sqrt1 - 1) / n);
    }

    private long findSquareRootOf1 () {
        for( long x = (long) Math.sqrt(n) + 1; x <= n / 2 + 1; x++ ){
            if( x * x % n == 1 ){
                return x;
            }
        }
        throw new NoSuchElementException();
    }

    private void run () {
        for( long x = 1; x < n; x++ ){
            LeastEuclidean euclid = new LeastEuclidean(n, x);
            if( euclid.gcd() != 1 ){
                continue;
            }
            long xinv = euclid.invPrecise();
            xinv = Math.min(xinv, n - xinv);
            long product = x * xinv;
            long quotient = product / n;
            long remainder = product - n * quotient;
            System.out.println("x=" + x + ", xinv=" + xinv + ", x*xinv=" + quotient + "n+" + remainder + ", evaluateQ="
                + scream(evaluate(euclid)) + ", q=" + euclid.q());
        }
    }

    private long evaluate (LeastEuclidean euclid) {
        List<Long> q = euclid.q();
        long x = LeastEuclidean.evaluate(q.subList(0, q.size() - 1));
        x = (x + n) % n;
        x = Math.min(x, n - x);
        long y = LeastEuclidean.evaluate(q.subList(1, q.size()));
        y = (y + n) % n;
        y = Math.min(y, n - y);
        return x - y;

        // long x = euclid.x();
        // long xinv = euclid.invPrecise();
        // xinv = Math.min(xinv, n - xinv);
        // return xinv - x;

        // List<Long> q = euclid.q();
        // for( int i = 0, j = q.size() - 1; i <= j; i++, j-- ){
        // if( Math.abs(q.get(i)) > Math.abs(q.get(j)) ){
        // return +1;
        // }
        // if( Math.abs(q.get(i)) < Math.abs(q.get(j)) ){
        // return -1;
        // }
        // }
        // return 0;

        // long a = LeastEuclidean.evaluate(q.subList(0, q.size() - 1));
        // long b = LeastEuclidean.evaluate(q.subList(1, q.size()));
        //
        // return Math.abs(a) - Math.abs(b);

        // long inv = euclid.invUnsigned();
        // return MathInt.sgn(euclid.x() - inv);
        // long inv = Math.min(euclid.binv(), euclid.a() - euclid.binv());
        // return (long) Math.signum(euclid.b() - inv);

    }

    private String scream (long signum) {
        return signum == 0 ? "ZERO" : signum > 0 ? "MORE" : "LESS";
    }

}
