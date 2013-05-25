
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelEquals;

import org.junit.Test;

public class DirichletExplicitTest {

    @Test
    public void testDirichletExplicit () {
        assertKernelEquals(DirichletExplicit.get(), new DirichletNaive(), 0.25);
    }

    @Test
    public void testDirichletExplicit1 () {
        assertKernelEquals(DirichletExplicit.get(1), new DirichletNaive(1), 0.25);
    }

    @Test
    public void testDirichletExplicit2 () {
        assertKernelEquals(DirichletExplicit.get(2), new DirichletNaive(2), 0.25);
    }

    @Test
    public void testDirichletExplicit3 () {
        assertKernelEquals(DirichletExplicit.get(3), new DirichletNaive(3), 0.25);
    }

    @Test
    public void testDirichletExplicit4 () {
        assertKernelEquals(DirichletExplicit.get(4), new DirichletNaive(4), 0.25);
    }

    @Test
    public void testDirichletExplicit5 () {
        assertKernelEquals(DirichletExplicit.get(5), new DirichletNaive(5), 0.25);
    }
}
