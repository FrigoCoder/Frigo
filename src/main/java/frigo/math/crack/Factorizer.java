
package frigo.math.crack;

import java.util.Set;
import java.util.TreeSet;

import frigo.math.integer.JacobiSymbol;
import frigo.math.integer.MathInt;

public class Factorizer {

    public static void main (String[] args) {
        // long n = 7 * 17;
        long n = 11 * 17;
        // long n = 23 * 103;
        // long n = 124989250231L;
        // long n = 103 * 499979L;

        Factorizer factorizer = new Factorizer(n);
        factorizer.factor();
    }

    private long n;
    private FactorialNumberSystem radix;
    private Set<Long> set = new TreeSet<>();

    public Factorizer (long n) {
        this.n = n;
        radix = new FactorialNumberSystem(n / 4);
        set.add(0L);
    }

    public void factor () {
        for( int j = 0; j < radix.size() - 1; j++ ){
            long p = radix.placeValue(j + 1);

            Set<Long> next = new TreeSet<>();
            for( long digit = 0; digit <= radix.highestDigit(j); digit++ ){
                for( long k0 : set ){
                    long k = k0 + digit * radix.placeValue(j);
                    long x2 = k * n + 1;
                    // if( isQuadraticResidue(x2, p) && isQuadraticResidue(x2, p * n) ){
                    if( isQuadraticResidue(x2, p) && isQuadraticResidue(x2, p * n) ){
                        next.add(k);
                    }
                }
            }
            set = next;
            System.out.println("p=" + p + ", |S|=" + set.size() + ", S=" + set);
            checkFactors();
        }
    }

    private void checkFactors () {
        for( long k : set ){
            long x = ceilSqrt(k * n + 1);
            checkFactor(x + 1, k);
            checkFactor(x - 1, k);
        }
    }

    private long ceilSqrt (long x2) {
        long x = MathInt.sqrt(x2);
        return x * x == x2 ? x : x + 1;
    }

    private void checkFactor (long xpm1, long k) {
        long p = MathInt.gcd(xpm1, n);
        if( p != 1 && p != n ){
            System.out.println("Found factor " + p + ", k is " + k);
        }
    }

    private boolean isQuadraticResidue (long x, long p) {
        long odd = p;
        while( odd % 2 == 0 ){
            odd /= 2;
        }
        return JacobiSymbol.jacobi(x, odd) == 1;
    }

}
