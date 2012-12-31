
package frigo.math;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DirichletKernelPolynomialTest {

    static double epsilon = 1.0e-11;
    protected int[] degrees = {1, 2, 3, 4, 5, 10, 15};
    protected double[] points = {-1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5};

    @Test
    public void testDirichlet () {
        for( int degree : degrees ){
            Polynomial dirichlet = DirichletKernelPolynomial.dirichlet(degree);
            for( double point : points ){
                assertEquals(naiveDirichlet(point, degree), dirichlet.evaluate(Math.cos(point)), epsilon);
            }
        }
    }

    @Test
    public void testFejer () {
        for( int degree : degrees ){
            Polynomial fejer = DirichletKernelPolynomial.fejer(degree);
            for( double point : points ){
                assertEquals(naiveFejer(point, degree), fejer.evaluate(Math.cos(point)), epsilon);
            }
        }
    }

    @Test
    public void testModifiedDirichlet () {
        for( int degree : degrees ){
            Polynomial modifiedDirichlet = DirichletKernelPolynomial.modifiedDirichlet(degree);
            for( double point : points ){
                assertEquals(naiveModifiedDirichlet(point, degree),
                             modifiedDirichlet.evaluate(Math.cos(point)),
                             epsilon);
            }
        }
    }

    protected double naiveDirichlet (double x, int n) {
        double result = 0.5;
        for( int k = 1; k <= n; k++ ){
            result += Math.cos(k * x);
        }
        return result * 2;
    }

    protected double naiveFejer (double x, int n) {
        double result = 0.0;
        for( int k = 0; k < n; k++ ){
            result += naiveDirichlet(x, k);
        }
        return result / n;
    }

    protected double naiveModifiedDirichlet (double x, int n) {
        double result = 0.5;
        for( int k = 1; k < n; k++ ){
            result += Math.cos(k * x);
        }
        result += 0.5 * Math.cos(n * x);
        return result / n;
    }
}
