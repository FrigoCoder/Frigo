
package frigo.util;

import static frigo.util.Reflection.setField;
import static frigo.util.Reflection.setStaticField;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ReflectionTest {

    private int privateField;

    private static int privateStaticField;

    @Test
    public void getField_returns_value_of_private_field () throws Exception {
        privateField = 7;
        assertThat(Reflection.<Integer> getField(this, "privateField"), is(7));
    }

    @Test
    public void setField_sets_value_of_private_field () throws Exception {
        setField(this, "privateField", 2);
        assertThat(privateField, is(2));
    }

    @Test
    public void getStaticField_returns_value_of_private_static_field () throws Exception {
        privateStaticField = 13;
        assertThat(Reflection.<Integer> getStaticField(getClass().getCanonicalName(), "privateStaticField"), is(13));
        assertThat(Reflection.<Integer> getStaticField(getClass(), "privateStaticField"), is(13));
    }

    @Test
    public void setStaticField_sets_value_of_private_static_field () throws Exception {
        setStaticField(getClass().getCanonicalName(), "privateStaticField", 5);
        assertThat(privateStaticField, is(5));
        setStaticField(getClass(), "privateStaticField", 6);
        assertThat(privateStaticField, is(6));
    }

}
