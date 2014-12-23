
package frigo.math;

import java.util.NoSuchElementException;

import frigo.math.numbertheory.JacobiSymbol;

public class Whatever {

    public static void main (String[] args) {
        long n = 11 * 17;
        Whatever whatever = new Whatever(n);
        whatever.run();
    }

    private long n;
    private long sqrt1;

    public Whatever (long n) {
        this.n = n;
        sqrt1 = findSquareRootOf1();
        System.out.println("n=" + n + ", sqrt1=" + sqrt1);
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
            euclid.run();
            if( euclid.gcd() != 1 ){
                continue;
            }
            System.out.println("x=" + x + ", xinv=" + euclid.binv() + ", evaluateQ=" + scream(evaluate(euclid))
                + ", jacobi=" + scream(JacobiSymbol.jacobi(x, n)) + ", q=" + euclid.qshort());
        }
    }

    private long evaluate (ExtendedEuclidean euclid) {
        long inv = Math.min(euclid.binv(), euclid.a() - euclid.binv());
        return (long) Math.signum(euclid.b() - inv);
    }

    private String scream (long signum) {
        if( signum == 0 ){
            return "ZERO";
        }
        return signum > 0 ? "POSITIVE" : "NEGATIVE";
    }

}
