
package frigo.filter;

import org.junit.Before;
import org.junit.Test;

public class BellTest extends KernelTestBase {

    @Before
    public void setUp () {
        kernel = new Bell();
    }

    @Test
    public void testBell () {
        checkRadiusAndDomain(kernel, 1.5, -1.5, 1.5);
    }

    @Test
    public void testEvaluate () {
        checkEvaluate(kernel, -2.0, 0.0);
        checkEvaluate(kernel, -1.5, 0.0);
        checkEvaluate(kernel, -1.0, 0.125);
        checkEvaluate(kernel, -0.5, 0.5);
        checkEvaluate(kernel, 0.0, 0.75);
        checkEvaluate(kernel, 0.5, 0.5);
        checkEvaluate(kernel, 1.0, 0.125);
        checkEvaluate(kernel, 1.5, 0.0);
        checkEvaluate(kernel, 2.0, 0.0);
    }
}
