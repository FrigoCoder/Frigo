
package frigo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BoxFinderTest {

    @Test
    public void testFindBestBox () {
        double[] v = {0, 0, 0, 0, 1, 1, 1, 0, 0, 0};
        BoxFinder finder = new BoxFinder(v);
        Box box = finder.getMinimumWeightedVarianceSum();
        assertThat(box.left, is(4));
        assertThat(box.right, is(6));
    }
}
