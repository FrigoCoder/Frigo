
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelEquals;

import org.junit.Test;

public class DirichletTrigonometricTest {

    @Test
    public void testDirichletTrigonometric () {
        assertKernelEquals(new DirichletTrigonometric(), new DirichletNaive(), 0.25);
    }

    @Test
    public void testDirichletTrigonometric1 () {
        assertKernelEquals(new DirichletTrigonometric(1.0), new DirichletNaive(1), 0.25);
    }

    @Test
    public void testDirichletTrigonometric2 () {
        assertKernelEquals(new DirichletTrigonometric(2.0), new DirichletNaive(2), 0.25);
    }

    @Test
    public void testDirichletTrigonometric3 () {
        assertKernelEquals(new DirichletTrigonometric(3.0), new DirichletNaive(3), 0.25);
    }

    @Test
    public void testDirichletTrigonometric4 () {
        assertKernelEquals(new DirichletTrigonometric(4.0), new DirichletNaive(4), 0.25);
    }
}
