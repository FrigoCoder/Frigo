
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelDomainEquals;
import static frigo.filter.KernelTestUtil.assertKernelSampleEquals;

import org.junit.Test;

public class LinearTest {

    private Kernel kernel = new Linear();

    @Test
    public void testLinear () {
        assertKernelDomainEquals(kernel, 1.0, -1.0, 1.0);
    }

    @Test
    public void testEvaluate () {
        assertKernelSampleEquals(kernel, -1.5, 0.0);
        assertKernelSampleEquals(kernel, -1.0, 0.0);
        assertKernelSampleEquals(kernel, -0.5, 0.5);
        assertKernelSampleEquals(kernel, 0.0, 1.0);
        assertKernelSampleEquals(kernel, 0.5, 0.5);
        assertKernelSampleEquals(kernel, 1.0, 0.0);
        assertKernelSampleEquals(kernel, 1.5, 0.0);
    }

}
