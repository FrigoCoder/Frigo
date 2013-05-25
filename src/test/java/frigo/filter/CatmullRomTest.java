
package frigo.filter;

import static frigo.filter.KernelTestUtil.assertKernelEquals;

import org.junit.Test;

public class CatmullRomTest {

    @Test
    public void testCatmullRom () {
        assertKernelEquals(new CatmullRom(), new BCCubicSpline(0, 0.5), 0.25);
    }
}
