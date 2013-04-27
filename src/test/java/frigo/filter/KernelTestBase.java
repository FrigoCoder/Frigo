
package frigo.filter;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class KernelTestBase {

    protected double epsilon = 1.0e-15;
    protected Kernel kernel;

    public void checkEvaluate (Kernel kern, double point, double expected) {
        assertThat(kern.evaluate(point), closeTo(expected, epsilon));
    }

    public void checkRadiusAndDomain (Kernel kern, double radius, double left, double right) {
        assertThat(kern.radius, is(radius));
        assertThat(kern.left, is(left));
        assertThat(kern.right, is(right));
    }

    public void compareKernels (Kernel actual, Kernel expected, double granularity) {
        checkRadiusAndDomain(actual, expected.radius, expected.left, expected.right);
        for( double point = actual.left - granularity; point <= actual.right + granularity; point += granularity ){
            checkEvaluate(actual, point, expected.evaluate(point));
        }
    }
}
