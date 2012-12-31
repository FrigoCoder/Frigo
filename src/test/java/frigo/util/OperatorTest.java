
package frigo.util;

import static frigo.util.Operator.ADD;
import static frigo.util.Operator.DIV;
import static frigo.util.Operator.MUL;
import static frigo.util.Operator.SUB;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class OperatorTest {

    @Test
    public void testEvaluateAdd () {
        assertThat(ADD.evaluate(1.0, 1.0), is(2.0));
        assertThat(ADD.evaluate(2.0, 1.0), is(3.0));
        assertThat(ADD.evaluate(11.0, 5.0), is(16.0));
    }

    @Test
    public void testEvaluateDiv () {
        assertThat(DIV.evaluate(1.0, 1.0), is(1.0));
        assertThat(DIV.evaluate(2.0, 1.0), is(2.0));
        assertThat(DIV.evaluate(11.0, 5.0), is(2.2));
        assertThat(DIV.evaluate(0.0, 0.0), is(Double.NaN));
        assertThat(DIV.evaluate(1.0, 0.0), is(Double.POSITIVE_INFINITY));
        assertThat(DIV.evaluate(-1.0, 0.0), is(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testEvaluateMul () {
        assertThat(MUL.evaluate(1.0, 1.0), is(1.0));
        assertThat(MUL.evaluate(2.0, 1.0), is(2.0));
        assertThat(MUL.evaluate(11.0, 5.0), is(55.0));
    }

    @Test
    public void testEvaluateSub () {
        assertThat(SUB.evaluate(1.0, 1.0), is(0.0));
        assertThat(SUB.evaluate(2.0, 1.0), is(1.0));
        assertThat(SUB.evaluate(11.0, 5.0), is(6.0));
    }

    @Test
    public void testIsOperator () {
        assertThat(Operator.isOperator("+"), is(true));
        assertThat(Operator.isOperator("-"), is(true));
        assertThat(Operator.isOperator("*"), is(true));
        assertThat(Operator.isOperator("/"), is(true));
        assertThat(Operator.isOperator("blah"), is(false));
        assertThat(Operator.isOperator("**"), is(false));
        assertThat(Operator.isOperator("/*"), is(false));
        assertThat(Operator.isOperator("//"), is(false));
        assertThat(Operator.isOperator("++"), is(false));
        assertThat(Operator.isOperator("--"), is(false));
    }

    @Test
    public void testOperator () {
        assertThat(ADD.token, is("+"));
        assertThat(DIV.token, is("/"));
        assertThat(MUL.token, is("*"));
        assertThat(SUB.token, is("-"));
    }

    @Test
    public void testParse () {
        assertThat(Operator.parse("+"), is(ADD));
        assertThat(Operator.parse("-"), is(SUB));
        assertThat(Operator.parse("*"), is(MUL));
        assertThat(Operator.parse("/"), is(DIV));
    }

    @Test(expected = RuntimeException.class)
    public void testParseUnknown1 () {
        Operator.parse("blah");
    }

    @Test(expected = RuntimeException.class)
    public void testParseUnknown2 () {
        Operator.parse("**");
    }

    @Test(expected = RuntimeException.class)
    public void testParseUnknown3 () {
        Operator.parse("/*");
    }

    @Test(expected = RuntimeException.class)
    public void testParseUnknown4 () {
        Operator.parse("//");
    }

    @Test(expected = RuntimeException.class)
    public void testParseUnknown5 () {
        Operator.parse("++");
    }

    @Test(expected = RuntimeException.class)
    public void testParseUnknown6 () {
        Operator.parse("--");
    }
}
