
package frigo.math;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Random;

import org.junit.Test;

public class ChebyshevPolynomialTest {

    private static double epsilon = 0.000000000001;
    private static int maxDegreeTested = 10;

    private ChebyshevPolynomial chebyshev = new ChebyshevPolynomial();

    /**
     * Tests whether the generated Chebyshev polynomials conform to the Tn(cos(x)) == cos(nx) identity.
     */
    @Test
    public void testChebyshevIdentity () {
        int numberOfArguments = 100;
        double[] arguments = new double[numberOfArguments];
        Random rand = new Random();
        for( int i = 0; i < numberOfArguments; i++ ){
            arguments[i] = rand.nextDouble();
        }
        for( int i = 0; i < numberOfArguments; i++ ){
            double x = arguments[i];
            for( int n = 0; n <= maxDegreeTested; n++ ){
                assertThat(chebyshev.chebyshev(n).evaluate(Math.cos(x)), closeTo(Math.cos(n * x), epsilon));
            }
        }
    }

    /**
     * Tests whether the generated Chebyshev polynomials conform to the recurrence relation
     */
    @Test
    public void testChebyshevRecurrenceRelation () {
        assertThat(chebyshev.chebyshev(0), is(Polynomial.ONE));
        assertThat(chebyshev.chebyshev(1), is(Polynomial.X));
        for( int n = 2; n < maxDegreeTested; n++ ){
            Polynomial pm2 = chebyshev.chebyshev(n - 2);
            Polynomial pm1 = chebyshev.chebyshev(n - 1);
            Polynomial p = chebyshev.chebyshev(n);
            assertThat(p, is(pm1.mul(2.0).mul(Polynomial.X).sub(pm2)));
        }
    }

    @Test
    public void testGetNormalizedChebyshevPolynomial () {
        for( int i = 0; i < maxDegreeTested; i++ ){
            Polynomial cheby = chebyshev.chebyshev(i);
            Polynomial normal = chebyshev.chebyshevNormalized(i);
            double highestCoeff = cheby.getCoeff(cheby.getDegree());
            assertThat(cheby, is(normal.mul(highestCoeff)));
        }
    }

    @Test
    public void testGetNormalizedSecondKind () {
        for( int i = 0; i < maxDegreeTested; i++ ){
            Polynomial secondKind = chebyshev.secondKind(i);
            Polynomial normal = chebyshev.secondKindNormalized(i);
            double highestCoeff = secondKind.getCoeff(secondKind.getDegree());
            assertThat(secondKind, is(normal.mul(highestCoeff)));
        }
    }

    /**
     * Tests whether the second kind of Chebyshev polynomials conform to the Un(x) = sin(nx) / sin(x) identity.
     */
    @Test
    public void testSecondKindIdentity () {
        int numberOfArguments = 100;
        double[] arguments = new double[numberOfArguments];
        Random rand = new Random();
        for( int i = 0; i < numberOfArguments; i++ ){
            arguments[i] = rand.nextDouble();
        }
        for( int i = 0; i < numberOfArguments; i++ ){
            double x = arguments[i];
            for( int n = 0; n <= maxDegreeTested; n++ ){
                assertThat(chebyshev.secondKind(n).evaluate(Math.cos(x)),
                    closeTo(Math.sin(n * x) / Math.sin(x), epsilon));
            }
        }
    }

    /**
     * Tests whether the second kind of Chebyshev polynomials conform to the recurrence relation
     */
    @Test
    public void testSecondKindRecurrenceRelation () {
        assertThat(chebyshev.secondKind(0), is(Polynomial.ZERO));
        assertThat(chebyshev.secondKind(1), is(Polynomial.ONE));
        for( int n = 2; n < maxDegreeTested; n++ ){
            Polynomial pm2 = chebyshev.secondKind(n - 2);
            Polynomial pm1 = chebyshev.secondKind(n - 1);
            Polynomial p = chebyshev.secondKind(n);
            assertThat(p, is(pm1.mul(2.0).mul(Polynomial.X).sub(pm2)));
        }
    }

}
