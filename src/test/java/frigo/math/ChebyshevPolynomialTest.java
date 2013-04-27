
package frigo.math;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
                assertThat(ChebyshevPolynomial.chebyshev(n).evaluate(Math.cos(x)), closeTo(Math.cos(n * x), epsilon));
            }
        }
    }

    /**
     * Tests whether the generated Chebyshev polynomials conform to the recurrence relation
     */
    @Test
    public void testChebyshevRecurrenceRelation () {
        assertThat(ChebyshevPolynomial.chebyshev(0), is(Polynomial.ONE));
        assertThat(ChebyshevPolynomial.chebyshev(1), is(Polynomial.X));
        for( int n = 2; n < maxDegreeTested; n++ ){
            Polynomial pm2 = ChebyshevPolynomial.chebyshev(n - 2);
            Polynomial pm1 = ChebyshevPolynomial.chebyshev(n - 1);
            Polynomial p = ChebyshevPolynomial.chebyshev(n);
            assertThat(p, is(pm1.mul(2.0).mul(Polynomial.X).sub(pm2)));
        }
    }

    @Test
    public void testGetNormalizedChebyshevPolynomial () {
        for( int i = 0; i < maxDegreeTested; i++ ){
            final Polynomial cheby = ChebyshevPolynomial.chebyshev(i);
            final Polynomial normal = ChebyshevPolynomial.chebyshevNormalized(i);
            final double highestCoeff = cheby.getCoeff(cheby.getDegree());
            assertThat(cheby, is(normal.mul(highestCoeff)));
        }
    }

    @Test
    public void testGetNormalizedSecondKind () {
        for( int i = 0; i < maxDegreeTested; i++ ){
            final Polynomial secondKind = ChebyshevPolynomial.secondKind(i);
            final Polynomial normal = ChebyshevPolynomial.secondKindNormalized(i);
            final double highestCoeff = secondKind.getCoeff(secondKind.getDegree());
            assertThat(secondKind, is(normal.mul(highestCoeff)));
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
                assertThat(ChebyshevPolynomial.secondKind(n).evaluate(Math.cos(x)),
                    closeTo(Math.sin(n * x) / Math.sin(x), epsilon));
            }
        }
    }

    /**
     * Tests whether the second kind of Chebyshev polynomials conform to the recurrence relation
     */
    @Test
    public void testSecondKindRecurrenceRelation () {
        assertThat(ChebyshevPolynomial.secondKind(0), is(Polynomial.ZERO));
        assertThat(ChebyshevPolynomial.secondKind(1), is(Polynomial.ONE));
        for( int n = 2; n < maxDegreeTested; n++ ){
            final Polynomial pm2 = ChebyshevPolynomial.secondKind(n - 2);
            final Polynomial pm1 = ChebyshevPolynomial.secondKind(n - 1);
            final Polynomial p = ChebyshevPolynomial.secondKind(n);
            assertThat(p, is(pm1.mul(2.0).mul(Polynomial.X).sub(pm2)));
        }
    }

}
