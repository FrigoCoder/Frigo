
package frigo.filter;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class KernelTestUtil {

    public static void assertKernelEquals (Kernel actual, Kernel expected, double granularity) {
        assertKernelDomainEquals(actual, expected.radius, expected.left, expected.right);
        for( double point = actual.left - granularity; point <= actual.right + granularity; point += granularity ){
            assertKernelSampleEquals(actual, point, expected.evaluate(point));
        }
    }

    public static void assertKernelDomainEquals (Kernel kern, double radius, double left, double right) {
        assertThat(kern.radius, is(radius));
        assertThat(kern.left, is(left));
        assertThat(kern.right, is(right));
    }

    public static void assertKernelSampleEquals (Kernel kern, double point, double expected) {
        assertThat(kern.evaluate(point), closeTo(expected, 1.0e-15));
    }

}
