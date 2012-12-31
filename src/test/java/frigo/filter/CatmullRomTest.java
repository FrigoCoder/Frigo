
package frigo.filter;

import org.junit.Test;

public class CatmullRomTest extends KernelTestBase {

    @Test
    public void testCatmullRom () {
        compareKernels(new CatmullRom(), new BCCubicSpline(0, 0.5), 0.25);
    }
}
