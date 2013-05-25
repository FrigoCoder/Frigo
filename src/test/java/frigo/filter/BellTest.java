
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelDomainEquals;
import static frigo.filter.KernelTestUtil.assertKernelSampleEquals;

import org.junit.Test;

public class BellTest {

    private Kernel kernel = new Bell();

    @Test
    public void testBell () {
        assertKernelDomainEquals(kernel, 1.5, -1.5, 1.5);
    }

    @Test
    public void testEvaluate () {
        assertKernelSampleEquals(kernel, -2.0, 0.0);
        assertKernelSampleEquals(kernel, -1.5, 0.0);
        assertKernelSampleEquals(kernel, -1.0, 0.125);
        assertKernelSampleEquals(kernel, -0.5, 0.5);
        assertKernelSampleEquals(kernel, 0.0, 0.75);
        assertKernelSampleEquals(kernel, 0.5, 0.5);
        assertKernelSampleEquals(kernel, 1.0, 0.125);
        assertKernelSampleEquals(kernel, 1.5, 0.0);
        assertKernelSampleEquals(kernel, 2.0, 0.0);
    }
}
