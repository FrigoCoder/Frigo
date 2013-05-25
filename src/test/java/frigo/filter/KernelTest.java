
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelDomainEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class KernelTest {

    private static class DummyKernel extends Kernel {

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

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Kernel kernel;

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
        assertKernelDomainEquals(kernel, 1.5, -1.5, 1.5);
    }

    @Test
    public void testKernelDoubleDouble () {
        kernel = new DummyKernel(-0.5, 2.0);
        assertKernelDomainEquals(kernel, 2.0, -0.5, 2.0);
        kernel = new DummyKernel(-2.0, 0.5);
        assertKernelDomainEquals(kernel, 2.0, -2.0, 0.5);
    }

    @Test
    public void testKernelDoubleDoubleNegativeRight () {
        thrown.expect(IllegalArgumentException.class);
        kernel = new DummyKernel(-2.0, -1.0);
    }

    @Test
    public void testKernelDoubleDoublePositiveLeft () {
        thrown.expect(IllegalArgumentException.class);
        kernel = new DummyKernel(1.0, 2.0);
    }

    @Test
    public void testKernelDoubleNegativeRadius () {
        thrown.expect(IllegalArgumentException.class);
        kernel = new DummyKernel(-1.0);
    }
}
