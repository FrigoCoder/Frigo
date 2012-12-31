
package frigo.exploratory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class EvaluationOrder {

    private int field;

    @Test
    public void fieldIsReadBeforeMethodExecutes () {
        field = 1;
        field += setFieldAndReturn(0, 2);
        assertThat(field, is(1 + 2));
    }

    private int setFieldAndReturn (int fieldValue, int returnValue) {
        field = fieldValue;
        return returnValue;
    }
}
