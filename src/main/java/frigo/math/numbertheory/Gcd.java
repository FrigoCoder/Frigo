
package frigo.math.numbertheory;

public class Gcd {

    public static int gcd (int u, int v) {
        return v == 0 ? u : gcd(v, u % v);
    }

    public static long gcd (long u, long v) {
        return v == 0 ? u : gcd(v, u % v);
    }

}
