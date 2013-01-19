
package frigo.exploratory.java;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class EvaluationOrderTest {

    private int field;

    @Test
    public void field_is_read_before_method_executes () {
        field = 1;
        field += setFieldAndReturn(0, 2);
        assertThat(field, is(1 + 2));
    }

    private int setFieldAndReturn (int newValue, int returnValue) {
        field = newValue;
        return returnValue;
    }

}
