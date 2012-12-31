
package frigo.filter;

import org.junit.Before;
import org.junit.Test;

public class LinearTest extends KernelTestBase {

    @Before
    public void setUp () {
        kernel = new Linear();
    }

    @Test
    public void testEvaluate () {
        checkEvaluate(kernel, -1.5, 0.0);
        checkEvaluate(kernel, -1.0, 0.0);
        checkEvaluate(kernel, -0.5, 0.5);
        checkEvaluate(kernel, 0.0, 1.0);
        checkEvaluate(kernel, 0.5, 0.5);
        checkEvaluate(kernel, 1.0, 0.0);
        checkEvaluate(kernel, 1.5, 0.0);
    }

    @Test
    public void testLinear () {
        checkRadiusAndDomain(kernel, 1.0, -1.0, 1.0);
    }
}
