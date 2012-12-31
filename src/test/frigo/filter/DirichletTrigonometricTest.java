
package frigo.filter;

import org.junit.Test;

public class DirichletTrigonometricTest extends KernelTestBase {

    @Test
    public void testDirichletTrigonometric () {
        compareKernels(new DirichletTrigonometric(), new DirichletNaive(), 0.25);
    }

    @Test
    public void testDirichletTrigonometric1 () {
        compareKernels(new DirichletTrigonometric(1.0), new DirichletNaive(1), 0.25);
    }

    @Test
    public void testDirichletTrigonometric2 () {
        compareKernels(new DirichletTrigonometric(2.0), new DirichletNaive(2), 0.25);
    }

    @Test
    public void testDirichletTrigonometric3 () {
        compareKernels(new DirichletTrigonometric(3.0), new DirichletNaive(3), 0.25);
    }

    @Test
    public void testDirichletTrigonometric4 () {
        compareKernels(new DirichletTrigonometric(4.0), new DirichletNaive(4), 0.25);
    }
}
