
package frigo.electronics;

import static frigo.electronics.Prefix.toUnit;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ParallelRlcWithLoadTest {

    @Test
    public void test_peak_and_midpoints_of_a_known_filter () {
        double R = toUnit("75");
        double L = toUnit("6.8m");
        double C = toUnit("150n");
        double Rload = toUnit("32");
        ParallelRlcWithLoad rlc = new ParallelRlcWithLoad(R, L, C, Rload);

        assertThat(rlc.response(4983.334570520974), is(-10.48467598730607));
        assertThat(rlc.response(1458.275899265235), is(-7.484675987306067));
        assertThat(rlc.response(17029.44103668045), is(-7.4846759873060655));
    }
}
