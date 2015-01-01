
package frigo.math.integer;

import java.util.Set;
import java.util.TreeSet;

import frigo.math.numbertheory.Gcd;

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
            Set<Long> qr = quadraticResidues(p);

            Set<Long> next = new TreeSet<>();
            for( long digit = 0; digit <= radix.highestDigit(j); digit++ ){
                for( long k0 : set ){
                    long k = k0 + digit * radix.placeValue(j);
                    if( qr.contains((k * n + 1) % p) ){
                        next.add(k);
                    }
                }
            }
            set = next;
            System.out.println("p=" + p + ", |S|=" + set.size() + ", S=" + set);
            // checkFactors();
        }
    }

    private Set<Long> quadraticResidues (long p) {
        Set<Long> residues = new TreeSet<>();
        for( long x = 0; x < p; x++ ){
            residues.add(x * x % p);
        }
        return residues;
    }

    private void checkFactors () {
        for( long k : set ){
            long x = MathAux.isqrt(k * n + 1);
            checkFactor(x + 1, k);
            checkFactor(x - 1, k);
        }
    }

    private void checkFactor (long xpm1, long k) {
        long p = Gcd.gcd(xpm1, n);
        if( p != 1 && p != n ){
            System.out.println("Found factor " + p + ", k is " + k);
        }
    }

}
