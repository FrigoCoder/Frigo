
package frigo.math;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Whatever {

    public static void main (String[] args) {
        int n = 23 * 103;
        for( int k = 2; k < n - 1; k++ ){
            printNK(n, k);
        }

    }

    private static void printNK (int n, int k) {
        ExtendedEuclidean euclid = new ExtendedEuclidean(n, k);
        euclid.run();
        if( euclid.gcd() != 1 ){
            return;
        }
        System.out.println("n=" + n + ", k=" + k + ", kinv=" + euclid.binv() + ", rel=" + rel(euclid) + ", q="
            + euclid.qshort());
    }

    private static int factorial (int k) {
        int result = 1;
        for( int i = 2; i <= k; i++ ){
            result *= i;
        }
        return result;
    }

    /**
     * This is almost cool.
     */

    private static int rel (ExtendedEuclidean euclid) {
        // List<Integer> leftq = euclid.qshort();
        // List<Integer> rightq = Lists.reverse(euclid.qshort());
        // return compare(ExtendedEuclidean.kFromQCompact(leftq), ExtendedEuclidean.kFromQCompact(rightq));
        return compare(euclid.binvSigned(), euclid.b());
    }

    private static List<Integer> left (List<Integer> q) {
        return q.subList(0, q.size() % 2 == 0 ? q.size() / 2 : (q.size() + 1) / 2);
    }

    private static List<Integer> right (List<Integer> q) {
        return q.subList(q.size() % 2 == 0 ? q.size() / 2 : (q.size() - 1) / 2, q.size());
    }

    private static int compare (int a, int b) {
        if( a < b ){
            return -1;
        }
        if( a > b ){
            return 1;
        }
        return 0;
    }

    private static boolean nextToSquare (int n) {
        int sqrtn = (int) (Math.sqrt(n + 1) + 0.5);
        return sqrtn * sqrtn == n;
    }

    private static void whatever (int n, int x) {
        ExtendedEuclidean euclidean = new ExtendedEuclidean(n, x);
        euclidean.run();
        if( euclidean.gcd() != 1 ){
            return;
        }

        System.out.println("n=" + n + ", x=" + x + ", xinv=" + euclidean.binv() + ", k=" + euclidean.k() + ", q="
            + euclidean.qshort());
    }

    public static int powmod2 (int a, int k, int m) {
        if( k == 0 ){
            return 1;
        }
        long t = powmod2(a, k / 2, m);
        t = t * t % m;
        if( k % 2 == 1 ){
            t = t * a % m;
        }
        return (int) t;
    }

    private static int qrel (List<Integer> q) {
        for( int i = 0, j = q.size() - 1; i <= j; i++, j-- ){
            if( q.get(i) < q.get(j) ){
                return -1;
            }
            if( q.get(i) > q.get(j) ){
                return 1;
            }
        }
        return 0;
    }

    private static boolean isBiPrime (int n) {
        List<Integer> factors = factor(n);
        return factors.size() == 2 && factors.get(0) != factors.get(1);
    }

    private static List<Integer> factor (int n) {
        List<Integer> list = new LinkedList<>();
        while( n % 2 == 0 ){
            n /= 2;
            list.add(2);
        }
        for( int x = 3; x * x <= n; x += 2 ){
            while( n % x == 0 ){
                n /= x;
                list.add(x);
            }
        }
        if( n != 1 ){
            list.add(n);
        }
        return list;
    }

    private static int getSquareRootOf (int r, int n) {
        for( int x = (int) Math.sqrt(n); x < n - 1; x++ ){
            if( x * x % n == r && x * x > n ){
                return x;
            }
        }
        throw new NoSuchElementException();
    }

}
