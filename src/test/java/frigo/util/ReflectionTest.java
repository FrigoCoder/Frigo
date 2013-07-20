
package frigo.util;

import static frigo.util.Reflection.setField;
import static frigo.util.Reflection.setStaticField;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ReflectionTest {

    private static class Base {

        private int privateField;
        private static int privateStaticField;

    }

    private static class Sub extends Base {

        private int anotherPrivateField;
        private static int anotherPrivateStaticField;

    }

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
        Base.privateStaticField = 13;
        assertThat(Reflection.<Integer> getStaticField(base.getClass().getName(), "privateStaticField"), is(13));
        Base.privateStaticField = 14;
        assertThat(Reflection.<Integer> getStaticField(base.getClass(), "privateStaticField"), is(14));
    }

    @Test
    public void setStaticField_sets_value_of_private_static_field () throws Exception {
        setStaticField(base.getClass().getName(), "privateStaticField", 5);
        assertThat(Base.privateStaticField, is(5));
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
        Sub.anotherPrivateStaticField = 15;
        assertThat(Reflection.<Integer> getStaticField(sub.getClass().getName(), "anotherPrivateStaticField"), is(15));
        Sub.anotherPrivateStaticField = 16;
        assertThat(Reflection.<Integer> getStaticField(sub.getClass(), "anotherPrivateStaticField"), is(16));
    }

    @Test
    public void setStaticField_sets_value_of_private_static_field_of_subclass () throws Exception {
        setStaticField(sub.getClass().getName(), "anotherPrivateStaticField", 115);
        assertThat(Sub.anotherPrivateStaticField, is(115));
        setStaticField(sub.getClass(), "anotherPrivateStaticField", 116);
        assertThat(Sub.anotherPrivateStaticField, is(116));
    }

}
