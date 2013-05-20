
package frigo.electronics;

import static frigo.electronics.Prefix.toUnit;
import static org.hamcrest.Matchers.closeTo;
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

        assertThat(rlc.f0(), is(4983.334570520974));
        assertThat(rlc.f1(), is(1458.2758992652357));
        assertThat(rlc.f2(), is(17029.44103668043));

        assertThat(rlc.response(rlc.f0()), closeTo(-10.48467598730607, 1E-15));
        assertThat(rlc.response(rlc.f1()), closeTo(-7.484675987306072, 1E-15));
        assertThat(rlc.response(rlc.f2()), closeTo(-7.484675987306072, 1E-15));

        assertThat(rlc.q(), is(0.3200360747922943));
    }
}
