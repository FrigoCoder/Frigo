
package frigo.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ReflectionTest {

    public int publicField;
    private int privateField;

    public static int publicStaticField;
    private static int privateStaticField;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isEnv_returns_true_on_existing_environment_variable () throws Exception {
        Reflection.setEnv("whatever", "who cares");
        assertThat(Reflection.isEnv("whatever"), is(true));
    }

    @Test
    public void isEnv_returns_false_on_missing_environment_variable () throws Exception {
        Reflection.removeEnv("whatever");
        assertThat(Reflection.isEnv("whatever"), is(false));
    }

    @Test
    public void getEnv_returns_existing_environment_variable () throws Exception {
        Reflection.setEnv("whatever", "who cares");
        assertThat(Reflection.getEnv("whatever"), is("who cares"));
    }

    @Test
    public void getEnv_throws_on_missing_environment_variable () throws Exception {
        Reflection.setEnv("whatever", "who cares");
        Reflection.removeEnv("whatever");
        thrown.expect(IllegalArgumentException.class);
        Reflection.getEnv("whatever");
    }

    @Test
    public void getField_returns_value_of_public_field () throws Exception {
        publicField = 3;
        assertThat(Reflection.<Integer> getField(this, "publicField"), is(3));
    }

    @Test
    public void getField_returns_value_of_private_field () throws Exception {
        privateField = 7;
        assertThat(Reflection.<Integer> getField(this, "privateField"), is(7));
    }

    @Test
    public void setField_sets_value_of_public_field () throws Exception {
        Reflection.setField(this, "publicField", 1);
        assertThat(publicField, is(1));
    }

    @Test
    public void setField_sets_value_of_private_field () throws Exception {
        Reflection.setField(this, "privateField", 2);
        assertThat(privateField, is(2));
    }

    @Test
    public void getStaticField_returns_value_of_public_static_field () throws Exception {
        publicStaticField = 11;
        assertThat(Reflection.<Integer> getStaticField(getClass().getCanonicalName(), "publicStaticField"), is(11));
        assertThat(Reflection.<Integer> getStaticField(getClass(), "publicStaticField"), is(11));
    }

    @Test
    public void getStaticField_returns_value_of_private_static_field () throws Exception {
        privateStaticField = 13;
        assertThat(Reflection.<Integer> getStaticField(getClass().getCanonicalName(), "privateStaticField"), is(13));
        assertThat(Reflection.<Integer> getStaticField(getClass(), "privateStaticField"), is(13));
    }

    @Test
    public void setStaticField_sets_value_of_public_static_field () throws Exception {
        Reflection.setStaticField(getClass().getCanonicalName(), "publicStaticField", 3);
        assertThat(publicStaticField, is(3));
        Reflection.setStaticField(getClass(), "publicStaticField", 4);
        assertThat(publicStaticField, is(4));
    }

    @Test
    public void setStaticField_sets_value_of_private_static_field () throws Exception {
        Reflection.setStaticField(getClass().getCanonicalName(), "privateStaticField", 5);
        assertThat(publicStaticField, is(5));
        Reflection.setStaticField(getClass(), "privateStaticField", 6);
        assertThat(publicStaticField, is(6));
    }

}
