
package frigo.electronics;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ParallelRlcWithLoadFinderTest {

    private ParallelRlcWithLoadFinder finder = new ParallelRlcWithLoadFinder(4800, -10, 0.3, 32);
    private ParallelRlcWithLoad rlc = finder.getFilter();

    @Test
    public void generated_RLC_filter_has_proper_f0 () {
        assertThat(rlc.f0(), closeTo(4800.0, 1E-12));
    }

    @Test
    public void generated_RLC_filter_has_proper_f1 () {
        assertThat(rlc.f1(), closeTo(1329.523, 1E-4));
    }

    @Test
    public void generated_RLC_filter_has_proper_f2 () {
        assertThat(rlc.f2(), closeTo(17329.523, 1E-4));
    }

    @Test
    public void generated_RLC_filter_has_proper_gain () {
        assertThat(rlc.gain(), is(-10.0));
    }

    @Test
    public void generated_RLC_filter_has_proper_q () {
        assertThat(rlc.q(), closeTo(0.3, 1E-15));
    }

    @Test
    public void generated_RLC_filter_has_proper_response_at_f0 () {
        assertThat(rlc.response(rlc.f0()), closeTo(-10.0, 1E-14));
    }

    @Test
    public void generated_RLC_filter_has_proper_response_at_f1 () {
        assertThat(rlc.response(rlc.f1()), closeTo(-10.0 + 3.0, 1E-14));
    }

    @Test
    public void generated_RLC_filter_has_proper_response_at_f2 () {
        assertThat(rlc.response(rlc.f2()), closeTo(-10.0 + 3.0, 1E-14));
    }
}
