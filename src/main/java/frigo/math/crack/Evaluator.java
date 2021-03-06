
package frigo.math.crack;

import java.util.NoSuchElementException;

import frigo.math.integer.ExtendedEuclidean;

public class Evaluator {

    public static void main (String[] args) {
        // long n = 11 * 17;
        long n = 7 * 11;
        Evaluator whatever = new Evaluator(n);
        whatever.run();
    }

    private long n;
    private long sqrt1;

    public Evaluator (long n) {
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
        for( long x = 2; x <= n / 2; x++ ){
            ExtendedEuclidean euclid = new ExtendedEuclidean(n, x);
            if( euclid.gcd() != 1 ){
                continue;
            }
            System.out.println("x=" + x + ", xinv=" + euclid.invUnsigned() + ", evaluateQ=" + scream(evaluate(euclid))
                + ", q=" + euclid.q());
        }
    }

    private long evaluate (ExtendedEuclidean euclid) {
        long inv = Math.min(euclid.invUnsigned(), euclid.n() - euclid.invUnsigned());
        return (long) Math.signum(euclid.x() - inv);
    }

    private String scream (long signum) {
        return signum == 0 ? "ZERO" : signum > 0 ? "MORE" : "LESS";
    }

}
