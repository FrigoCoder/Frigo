
package frigo.math;

import static org.junit.Assert.assertEquals;
import java.util.Random;
import org.junit.Test;

public class ChebyshevPolynomialTest {

    /**
     * Error limit for floating point comparison
     */
    private static final double epsilon = 0.000000000001;
    private static final int maxDegreeTested = 10;

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
                assertEquals(Math.cos(n * x), ChebyshevPolynomial.chebyshev(n).evaluate(Math.cos(x)), epsilon);
            }
        }
    }

    /**
     * Tests whether the generated Chebyshev polynomials conform to the recurrence relation
     */
    @Test
    public void testChebyshevRecurrenceRelation () {
        assertEquals(Polynomial.one, ChebyshevPolynomial.chebyshev(0));
        assertEquals(Polynomial.x, ChebyshevPolynomial.chebyshev(1));
        for( int n = 2; n < maxDegreeTested; n++ ){
            Polynomial pm2 = ChebyshevPolynomial.chebyshev(n - 2);
            Polynomial pm1 = ChebyshevPolynomial.chebyshev(n - 1);
            Polynomial p = ChebyshevPolynomial.chebyshev(n);
            assertEquals(pm1.mul(2.0).mul(Polynomial.x).sub(pm2), p);
        }
    }

    @Test
    public void testGetNormalizedChebyshevPolynomial () {
        for( int i = 0; i < maxDegreeTested; i++ ){
            final Polynomial cheby = ChebyshevPolynomial.chebyshev(i);
            final Polynomial normal = ChebyshevPolynomial.chebyshevNormalized(i);
            final double highestCoeff = cheby.getCoeff(cheby.getDegree());
            assertEquals(normal.mul(highestCoeff), cheby);
        }
    }

    @Test
    public void testGetNormalizedSecondKind () {
        for( int i = 0; i < maxDegreeTested; i++ ){
            final Polynomial secondKind = ChebyshevPolynomial.secondKind(i);
            final Polynomial normal = ChebyshevPolynomial.secondKindNormalized(i);
            final double highestCoeff = secondKind.getCoeff(secondKind.getDegree());
            assertEquals(normal.mul(highestCoeff), secondKind);
        }
    }

    /**
     * Tests whether the second kind of Chebyshev polynomials conform to the Un(x) = sin(nx) / sin(x) identity.
     */
    @Test
    public void testSecondKindIdentity () {
        final int numberOfArguments = 100;
        final double[] arguments = new double[numberOfArguments];
        final Random rand = new Random();
        for( int i = 0; i < numberOfArguments; i++ ){
            arguments[i] = rand.nextDouble();
        }
        for( int i = 0; i < numberOfArguments; i++ ){
            final double x = arguments[i];
            for( int n = 0; n <= maxDegreeTested; n++ ){
                assertEquals(Math.sin(n * x) / Math.sin(x),
                             ChebyshevPolynomial.secondKind(n).evaluate(Math.cos(x)),
                             epsilon);
            }
        }
    }

    /**
     * Tests whether the second kind of Chebyshev polynomials conform to the recurrence relation
     */
    @Test
    public void testSecondKindRecurrenceRelation () {
        assertEquals(Polynomial.zero, ChebyshevPolynomial.secondKind(0));
        assertEquals(Polynomial.one, ChebyshevPolynomial.secondKind(1));
        for( int n = 2; n < maxDegreeTested; n++ ){
            final Polynomial pm2 = ChebyshevPolynomial.secondKind(n - 2);
            final Polynomial pm1 = ChebyshevPolynomial.secondKind(n - 1);
            final Polynomial p = ChebyshevPolynomial.secondKind(n);
            assertEquals(pm1.mul(2.0).mul(Polynomial.x).sub(pm2), p);
        }
    }
}
