
package frigo.filter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class KernelTest extends KernelTestBase {

    private class DummyKernel extends Kernel {

        public DummyKernel (double radius) {
            super(radius);
        }

        public DummyKernel (double left, double right) {
            super(left, right);
        }

        @Override
        public double evaluate (double x) {
            return x;
        }
    }

    @Test
    public void testIsInDomain () {
        kernel = new DummyKernel(1.5);
        assertThat(kernel.isInDomain(-2.0), is(false));
        assertThat(kernel.isInDomain(-1.5), is(false));
        assertThat(kernel.isInDomain(-1.0), is(true));
        assertThat(kernel.isInDomain(0.0), is(true));
        assertThat(kernel.isInDomain(1.0), is(true));
        assertThat(kernel.isInDomain(1.5), is(false));
        assertThat(kernel.isInDomain(2.0), is(false));
    }

    @Test
    public void testKernelDouble () {
        kernel = new DummyKernel(1.5);
        checkRadiusAndDomain(kernel, 1.5, -1.5, 1.5);
    }

    @Test
    public void testKernelDoubleDouble () {
        kernel = new DummyKernel(-0.5, 2.0);
        checkRadiusAndDomain(kernel, 2.0, -0.5, 2.0);
        kernel = new DummyKernel(-2.0, 0.5);
        checkRadiusAndDomain(kernel, 2.0, -2.0, 0.5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKernelDoubleDoubleNegativeRight () {
        kernel = new DummyKernel(-2.0, -1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKernelDoubleDoublePositiveLeft () {
        kernel = new DummyKernel(1.0, 2.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKernelDoubleNegativeRadius () {
        kernel = new DummyKernel(-1.0);
    }
}
