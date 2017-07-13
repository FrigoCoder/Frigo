
package frigo.exploratory.mockito;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalMatchers.geq;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class ExpectationTest {

    private static interface Function {

        int evaluate (int x);
    }

    private Function f = mock(Function.class);

    @Test
    public void non_interleaving_matchers_do_not_disturb_each_other () {
        doReturn(1).when(f).evaluate(1);
        doReturn(2).when(f).evaluate(2);
        assertThat(f.evaluate(1), is(1));
        assertThat(f.evaluate(2), is(2));
    }

    @Test
    public void the_last_defined_matcher_is_valid_if_matchers_interleave () {
        doReturn(0).when(f).evaluate(anyInt());
        doReturn(1).when(f).evaluate(geq(1));
        doReturn(2).when(f).evaluate(geq(2));
        doReturn(3).when(f).evaluate(geq(3));
        assertThat(f.evaluate(0), is(0));
        assertThat(f.evaluate(1), is(1));
        assertThat(f.evaluate(2), is(2));
        assertThat(f.evaluate(3), is(3));
    }

    @Test
    public void expectation_is_rewritten_at_multiple_stubbing_call () {
        doReturn(1).when(f).evaluate(anyInt());
        doReturn(2).when(f).evaluate(anyInt());
        assertThat(f.evaluate(0), is(2));
    }

}
