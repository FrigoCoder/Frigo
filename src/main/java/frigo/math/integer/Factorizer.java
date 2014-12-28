
package frigo.math.integer;

import java.util.Set;
import java.util.TreeSet;

import frigo.math.numbertheory.Gcd;

public class Factorizer {

    private long n;
    private long[] u;
    private Set<Long> set = new TreeSet<>();

    public Factorizer (long n) {
        this.n = n;
        u = new long[MathAux.log2(n / 4) + 1];
        for( int i = 0; i < u.length; i++ ){
            u[i] = n * (1L << i);
        }
        set.add(1L);
    }

    public void factor () {
        for( int i = 0; i < u.length; i++ ){
            long p = 1L << i;
            Set<Long> qr = quadraticResidues(p);

            Set<Long> next = new TreeSet<>();
            for( long mul = 0; mul < 2; mul++ ){
                for( long sum : set ){
                    long x = sum + u[i] * mul;
                    if( qr.contains(x % p) ){
                        next.add(x);
                    }
                }
            }
            set = next;
            System.out.println("p=" + p + ", |S|=" + set.size() + ", S=" + set);

            checkFactors(set);
        }
    }

    private Set<Long> quadraticResidues (long p) {
        Set<Long> residues = new TreeSet<>();
        for( long x = 0; x < p; x++ ){
            residues.add(x * x % p);
        }
        return residues;
    }

    private void checkFactors (Set<Long> set) {
        for( long x2 : set ){
            long x = MathAux.isqrt(x2);
            checkFactor(x2, x + 1);
            checkFactor(x2, x - 1);
        }
    }

    private void checkFactor (long x2, long m) {
        long p = Gcd.gcd(m, n);
        if( p != 1 && p != n ){
            System.out.println("Found factor " + p + ", sum is " + x2);
        }
    }

}
