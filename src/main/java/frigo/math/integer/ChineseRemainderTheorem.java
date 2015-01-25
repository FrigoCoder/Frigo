
package frigo.math.integer;

public class ChineseRemainderTheorem {

    public static long crt (long xp, long p, long xq, long q) {
        return MathInt.mod(xp * q * inverse(q, p) + xq * p * inverse(p, q), p * q);
    }

    private static long inverse (long x, long n) {
        ExtendedEuclidean euclidean = new ExtendedEuclidean(n, x);
        return euclidean.invUnsigned();
    }

}
