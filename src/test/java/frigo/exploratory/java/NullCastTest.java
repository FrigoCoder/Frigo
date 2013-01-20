
package frigo.exploratory.java;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class NullCastTest {

    @Test
    public void cast_null_and_assign_it () {
        Object obj = null;
        Double x = (Double) obj;
        assertThat(x, nullValue());
    }

    @Test
    public void cast_null_and_pass_it_as_parameter () {
        Object obj = null;
        Double x = f((Double) obj);
        assertThat(x, nullValue());
    }

    @Test
    public void null_is_not_an_instance_of_Object () {
        Object obj = null;
        assertThat(obj, not(instanceOf(Object.class)));
    }

    private Double f (Double y) {
        return y;
    }
}
