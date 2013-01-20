
package frigo.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class PolishEvaluatorTest {

    private PolishEvaluator polish = new PolishEvaluator();

    @Test
    public void testEvaluate () {
        assertThat(polish.evaluate("0"), is(0.0));
        assertThat(polish.evaluate("7"), is(7.0));
        assertThat(polish.evaluate("+2"), is(2.0));
        assertThat(polish.evaluate("+ 1 2"), is(3.0));
        assertThat(polish.evaluate("- 2 1"), is(1.0));
        assertThat(polish.evaluate("* + 2 1 / 4 2"), is(6.0));
        assertThat(polish.evaluate("* + / 2 1 4 2"), is(12.0));
    }
}
