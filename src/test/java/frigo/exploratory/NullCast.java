
package frigo.exploratory;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class NullCast {

    @Test
    public void castingNullAndAssigningItDoesNotThrowNullPointerException () {
        Object obj = null;
        Double x = (Double) obj;
        assertThat(x, nullValue());
    }

    @Test
    public void castingNullAsParameterDoesNotThrowNullPointerException () {
        Object obj = null;
        Double x = f((Double) obj);
        assertThat(x, nullValue());
    }

    @Test
    public void nullIsNotInstanceOfObject () {
        Object obj = null;
        assertThat(obj, not(instanceOf(Object.class)));
    }

    private Double f (Double y) {
        return y;
    }
}
