
package frigo.math;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

import frigo.math.numbertheory.Gcd;

public class Whatever2 {

    public static void main (String[] args) {
        long n = 23 * 103;
        // long n = 11 * 17;
        // long n = 13 * 103;
        // long n = 10142789312725007L;
        long x = getSquareRootOf1(n);
        long k = (x * x - 1) / n;
        System.out.println("n=" + n + ", x=" + x + ", k=" + k);

        int m = 1;
        for( int p = 2; m <= n; p++ ){
            m *= p;
            if( Gcd.gcd(m, n) != 1 ){
                continue;
            }
            printSol(n, k, m);
        }
    }

    private static void printSol (long n, long k, long m) {
        Set<Long> sol = solutions(n, m);
        System.out.println("m=" + m + ", k%m=" + k % m + ", |sol|=" + sol.size() + ", sol=" + sol);
        if( !sol.contains(k % m) ){
            throw new RuntimeException();
        }
    }

    private static long getSquareRootOf1 (long n) {
        for( long x = 2; x < n - 1; x++ ){
            if( x * x % n == 1 ){
                return x;
            }
        }
        throw new NoSuchElementException();
    }

    private static Set<Long> solutions (long n, long m) {
        Set<Long> result = new TreeSet<>();
        long ninv = inv(n, m);
        for( long x = 0; x <= m - x; x++ ){
            long k = (pow(x, 2, m) + m - 1) * ninv % m;
            result.add(k);
        }
        return result;
    }

    private static long inv (long n, long p) {
        ExtendedEuclidean euclid = new ExtendedEuclidean(p, n % p);
        euclid.run();
        return euclid.binv();
    }

    private static long pow (long x, long p, long m) {
        long result = 1;
        for( long i = 0; i < p; i++ ){
            result *= x;
            result %= m;
        }
        return result;
    }

}
