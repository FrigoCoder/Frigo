
package frigo;

import java.util.HashSet;
import java.util.Set;

import frigo.math.ExtendedEuclidean;
import frigo.math.FactorizerSieve;

public class FindK {

    public static void main (String[] args) {
        int n = 23 * 103;
        FindK main = new FindK(n);
        main.run();
    }

    private int n;

    public FindK (int n) {
        this.n = n;
    }

    public void run () {
        FactorizerSieve sieve = new FactorizerSieve(n);
        for( int p = 3; p < 23; p++ ){
            if( sieve.factor(p).size() != 1 ){
                continue;
            }
            // int power = 1 * 2 * 4 * 6 * 10 * 12 / 32;
            // int power = 2 * 4 * 6 * 10 * 12 * 16 * 18 / 128;
            // int power = 405 * 4096;
            // int power = 405 * 2 * 2 * 2 * 2;
            int power = 405 * 2 * 2 * 2 * 2;
            Set<Integer> remainders = determineRemainders(power, p);
            System.out.println("p=" + p + ", remainders=" + remainders);
        }
    }

    private Set<Integer> determineRemainders (int power, int p) {
        Set<Integer> remainders = new HashSet<>();
        int ninv = inverseOfN(p);
        for( int x = 0; x < p; x++ ){
            int xpm1 = (powmod(x, power, p) - 1 + p) % p;
            int remainder = xpm1 * ninv % p;
            remainders.add(remainder);
        }
        return remainders;
    }

    private int powmod (int base, int power, int modulo) {
        int result = 1;
        for( int i = 0; i < power; i++ ){
            result *= base;
            result %= modulo;
        }
        return result;
    }

    private int inverseOfN (int p) {
        ExtendedEuclidean euclidean = new ExtendedEuclidean(p, n % p);
        euclidean.run();
        return euclidean.binv();
    }

}
