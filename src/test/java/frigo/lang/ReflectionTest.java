
package frigo.lang;

import static frigo.lang.Reflection.setField;
import static frigo.lang.Reflection.setStaticField;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ReflectionTest {

    private static class Base {

        private int privateField;
        private static int privateStaticField;

    }

    private static class Sub extends Base {

        private int anotherPrivateField;
        private static int anotherPrivateStaticField;

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Base base = new Sub();
    private Sub sub = new Sub();

    @Test
    public void getField_returns_value_of_private_field () throws Exception {
        base.privateField = 7;
        assertThat(Reflection.<Integer> getField(base, "privateField"), is(7));
    }

    @Test
    public void setField_sets_value_of_private_field () throws Exception {
        setField(base, "privateField", 2);
        assertThat(base.privateField, is(2));
    }

    @Test
    public void getStaticField_returns_value_of_private_static_field () throws Exception {
        Base.privateStaticField = 14;
        assertThat(Reflection.<Integer> getStaticField(base.getClass(), "privateStaticField"), is(14));
    }

    @Test
    public void setStaticField_sets_value_of_private_static_field () throws Exception {
        setStaticField(base.getClass(), "privateStaticField", 6);
        assertThat(Base.privateStaticField, is(6));
    }

    @Test
    public void getField_returns_value_of_private_field_of_subclass () throws Exception {
        sub.anotherPrivateField = 8;
        assertThat(Reflection.<Integer> getField(sub, "anotherPrivateField"), is(8));
    }

    @Test
    public void setField_sets_value_of_private_field_of_subclass () throws Exception {
        setField(sub, "anotherPrivateField", 2);
        assertThat(sub.anotherPrivateField, is(2));
    }

    @Test
    public void getStaticField_returns_value_of_private_static_field_of_subclass () throws Exception {
        Sub.anotherPrivateStaticField = 16;
        assertThat(Reflection.<Integer> getStaticField(sub.getClass(), "anotherPrivateStaticField"), is(16));
    }

    @Test
    public void setStaticField_sets_value_of_private_static_field_of_subclass () throws Exception {
        setStaticField(sub.getClass(), "anotherPrivateStaticField", 116);
        assertThat(Sub.anotherPrivateStaticField, is(116));
    }

    @Test
    public void getField_throws_exception_on_unknown_field () throws Exception {
        thrown.expect(NoSuchFieldException.class);
        Reflection.<Integer> getField(base, "doesNotExist");
    }

    @Test
    public void getStaticField_throws_exception_on_unknown_field () throws Exception {
        thrown.expect(NoSuchFieldException.class);
        Reflection.<Integer> getStaticField(base.getClass(), "doesNotExist");
    }

    @Test
    public void setField_throws_exception_on_unknown_field () throws Exception {
        thrown.expect(NoSuchFieldException.class);
        Reflection.<Integer> setField(base, "doesNotExist", 1);
    }

    @Test
    public void setStaticField_throws_exception_on_unknown_field () throws Exception {
        thrown.expect(NoSuchFieldException.class);
        Reflection.<Integer> setStaticField(base.getClass(), "doesNotExist", 2);
    }

}
