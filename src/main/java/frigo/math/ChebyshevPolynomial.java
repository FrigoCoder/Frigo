
package frigo.math;

import static java.lang.Math.pow;

import java.util.ArrayList;
import java.util.List;

public class ChebyshevPolynomial {

    private List<Polynomial> cosines = new ArrayList<>();
    private List<Polynomial> sines = new ArrayList<>();

    public ChebyshevPolynomial () {
        cosines.add(Polynomial.ONE);
        cosines.add(Polynomial.X);
        sines.add(Polynomial.ZERO);
        sines.add(Polynomial.ONE);
    }

    /**
     * Generates a Chebyshev polynomial based on <a
     * href=http://en.wikipedia.org/wiki/Chebyshev_polynomial#Definition>the recurrence relation from the Wikipedia
     * page</a>. Equivalent to the polynomial representation of cos(nx) in terms of cos(x). For example, cos(2x) =
     * 2cos^2(x) - 1 =: 2c^2 - 1.
     */
    public Polynomial chebyshev (int degree) {
        List<Polynomial> c = cosines;
        generateRecurrence(c, degree);
        return c.get(degree);
    }

    /**
     * Generates a Chebyshev polynomial divided by its highest coefficient.
     */
    public Polynomial chebyshevNormalized (int degree) {
        return degree == 0 ? chebyshev(0) : chebyshev(degree).div(pow(2, degree - 1));
    }

    /**
     * Generates a variation of the Chebyshev polynomial of the second kind. The index is shifted by one, so it is
     * equivalent to the polynomial representation of sin(nx)/sin(x) in terms of cos(x). For example sin(2x) / sin(x) =
     * 2cos(x) =: 2c.
     */
    public Polynomial secondKind (int degree) {
        List<Polynomial> s = sines;
        generateRecurrence(s, degree);
        return s.get(degree);
    }

    /**
     * Generates a Chebyshev polynomial of the second kind divided by its highest coefficient.
     */
    public Polynomial secondKindNormalized (int degree) {
        return degree == 0 ? secondKind(0) : secondKind(degree).div(pow(2, degree - 1));
    }

    protected void generateRecurrence (List<Polynomial> c, int degree) {
        for( int n = c.size(); n <= degree; n++ ){
            Polynomial Tn_1 = c.get(n - 1);
            Polynomial Tn_2 = c.get(n - 2);
            Polynomial Tn = Polynomial.X.mul(2).mul(Tn_1).sub(Tn_2);
            c.add(Tn);
        }
    }

}
