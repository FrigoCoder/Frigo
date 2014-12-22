
package frigo.math;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

public class Whatever2 {

    public static void main(String[] args) {
        // int n = 23 * 103;
        int n = 11 * 17;
        int x = getSquareRootOf1(n);
        int k = (x * x - 1) / n;

        // int limit = MathAux.isqrt(n);
        int limit = 256;
        FactorizerSieve sieve = new FactorizerSieve(limit);
        for (int p = 2; p <= limit; p++) {
            // if (sieve.factor(p).size() != 1 || Gcd.gcd(p, n) != 1) {
            // continue;
            // }
            Set<Integer> sol = solutions(n, p, 2);
            sol.removeAll(solutions(n, p / 2, 2));
            System.out.println("p=" + p + ", k%p=" + k % p + ", sol" + sol);
        }
    }

    private static int getSquareRootOf1(int n) {
        return getSquareRootOf(1, n);
    }

    private static int getSquareRootOf(int r, int n) {
        for (int x = (int) Math.sqrt(n); x < n - 1; x++) {
            if (x * x % n == r && x * x > n) {
                return x;
            }
        }
        throw new NoSuchElementException();
    }

    private static Set<Integer> solutions(int n, int p, int power) {
        return solutions(n, p, power, 1);
    }

    private static Set<Integer> solutions(int n, int p, int power, int minus) {
        Set<Integer> result = new TreeSet<>();
        int ninv = inv(n, p);
        for (int x = 0; x < p; x++) {
            int k = (pow(x, power, p) + p - minus) * ninv % p;
            result.add(k);
        }
        return result;
    }

    private static int inv(int n, int p) {
        ExtendedEuclidean euclid = new ExtendedEuclidean(p, n % p);
        euclid.run();
        return euclid.binv();
    }

    private static int pow(int x, int p, int m) {
        int result = 1;
        for (int i = 0; i < p; i++) {
            result *= x;
            result %= m;
        }
        return result;
    }

}
