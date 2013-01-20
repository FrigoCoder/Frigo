
package frigo.math;

import java.lang.ref.SoftReference;
import java.util.Vector;

import frigo.util.ArraysAux;

public class ChebyshevPolynomial {

    static SoftReference<Vector<Polynomial>> cosines;
    static SoftReference<Vector<Polynomial>> sines;

    /**
     * Generates a Chebyshev polynomial based on <a
     * href=http://en.wikipedia.org/wiki/Chebyshev_polynomial#Definition>the recurrence relation from the Wikipedia
     * page</a>. Equivalent to the polynomial representation of cos(nx) in terms of cos(x). For example, cos(2x) =
     * 2cos^2(x) - 1 =: 2c^2 - 1
     */
    public static Polynomial chebyshev (int degree) {
        Vector<Polynomial> c = getCosines();
        generateRecurrence(c, degree);
        return c.get(degree);
    }

    /**
     * Generates a Chebyshev polynomial divided by its highest coefficient
     */
    public static Polynomial chebyshevNormalized (int degree) {
        if( degree == 0 ){
            return chebyshev(0);
        }
        return chebyshev(degree).div(Math.pow(2, degree - 1));
    }

    /**
     * Generates a variation of the Chebyshev polynomial of the second kind. The index is shifted by one, so it is
     * equivalent to the polynomial representation of sin(nx)/sin(x) in terms of cos(x). For example sin(2x) / sin(x) =
     * 2cos(x) =: 2c
     */
    public static Polynomial secondKind (int degree) {
        Vector<Polynomial> s = getSines();
        generateRecurrence(s, degree);
        return s.get(degree);
    }

    /**
     * Generates a Chebyshev polynomial of the second kind divided by its highest coefficient
     */
    public static Polynomial secondKindNormalized (int degree) {
        if( degree == 0 ){
            return secondKind(0);
        }
        return secondKind(degree).div(Math.pow(2, degree - 1));
    }

    protected static void generateRecurrence (Vector<Polynomial> c, int degree) {
        for( int n = c.size(); n <= degree; n++ ){
            Polynomial Tn_1 = c.get(n - 1);
            Polynomial Tn_2 = c.get(n - 2);
            Polynomial Tn = Polynomial.X.mul(2).mul(Tn_1).sub(Tn_2);
            c.add(Tn);
        }
    }

    protected static Vector<Polynomial> getCosines () {
        Vector<Polynomial> v;
        if( cosines == null || (v = cosines.get()) == null ){
            v = ArraysAux.asVector(Polynomial.ONE, Polynomial.X);
            cosines = new SoftReference<>(v);
        }
        return v;
    }

    protected static Vector<Polynomial> getSines () {
        Vector<Polynomial> v;
        if( sines == null || (v = sines.get()) == null ){
            v = ArraysAux.asVector(Polynomial.ZERO, Polynomial.ONE);
            sines = new SoftReference<>(v);
        }
        return v;
    }
}
