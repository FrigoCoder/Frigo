
package frigo.filter;

import org.junit.Test;

public class DirichletExplicitTest extends KernelTestBase {

    @Test
    public void testDirichletExplicit () {
        compareKernels(DirichletExplicit.get(), new DirichletNaive(), 0.25);
    }

    @Test
    public void testDirichletExplicit1 () {
        compareKernels(DirichletExplicit.get(1), new DirichletNaive(1), 0.25);
    }

    @Test
    public void testDirichletExplicit2 () {
        compareKernels(DirichletExplicit.get(2), new DirichletNaive(2), 0.25);
    }

    @Test
    public void testDirichletExplicit3 () {
        compareKernels(DirichletExplicit.get(3), new DirichletNaive(3), 0.25);
    }

    @Test
    public void testDirichletExplicit4 () {
        compareKernels(DirichletExplicit.get(4), new DirichletNaive(4), 0.25);
    }

    @Test
    public void testDirichletExplicit5 () {
        compareKernels(DirichletExplicit.get(5), new DirichletNaive(5), 0.25);
    }
}
