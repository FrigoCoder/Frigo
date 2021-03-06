
package frigo.math;

import static java.lang.Math.cos;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DirichletKernelPolynomialTest {

    private static double epsilon = 1.0e-11;
    private int[] degrees = {1, 2, 3, 4, 5, 10, 15};
    private double[] points = {-1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5};

    @Test
    public void testDirichlet () {
        for( int degree : degrees ){
            Polynomial dirichlet = DirichletKernelPolynomial.dirichlet(degree);
            for( double point : points ){
                assertThat(dirichlet.evaluate(cos(point)), closeTo(naiveDirichlet(point, degree), epsilon));
            }
        }
    }

    @Test
    public void testFejer () {
        for( int degree : degrees ){
            Polynomial fejer = DirichletKernelPolynomial.fejer(degree);
            for( double point : points ){
                assertThat(fejer.evaluate(cos(point)), closeTo(naiveFejer(point, degree), epsilon));
            }
        }
    }

    @Test
    public void testModifiedDirichlet () {
        for( int degree : degrees ){
            Polynomial modifiedDirichlet = DirichletKernelPolynomial.modifiedDirichlet(degree);
            for( double point : points ){
                assertThat(modifiedDirichlet.evaluate(cos(point)),
                    closeTo(naiveModifiedDirichlet(point, degree), epsilon));
            }
        }
    }

    private double naiveDirichlet (double x, int n) {
        double result = 0.5;
        for( int k = 1; k <= n; k++ ){
            result += cos(k * x);
        }
        return result * 2;
    }

    private double naiveFejer (double x, int n) {
        double result = 0.0;
        for( int k = 0; k < n; k++ ){
            result += naiveDirichlet(x, k);
        }
        return result / n;
    }

    private double naiveModifiedDirichlet (double x, int n) {
        double result = 0.5;
        for( int k = 1; k < n; k++ ){
            result += cos(k * x);
        }
        result += 0.5 * cos(n * x);
        return result / n;
    }
}
