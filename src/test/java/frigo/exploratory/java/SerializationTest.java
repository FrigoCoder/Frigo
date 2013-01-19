
package frigo.exploratory.java;

import static org.apache.commons.lang3.SerializationUtils.deserialize;
import static org.apache.commons.lang3.SerializationUtils.serialize;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.io.Serializable;
import org.junit.Before;
import org.junit.Test;

public class SerializationTest {

    private static class SerializableStuff implements Serializable {

        public static int staticField = 1;
        public int field = 1;
    }

    private SerializableStuff object;
    private byte[] byteArray;

    @Before
    public void setUp () {
        object = new SerializableStuff();
        byteArray = serialize(object);
    }

    @Test
    public void deserialization_restores_object () {
        object.field = 2;
        object = (SerializableStuff) deserialize(byteArray);
        assertThat(object.field, is(1));
    }

    @Test
    public void deserialization_does_not_affect_static_fields () {
        SerializableStuff.staticField = 2;
        object = (SerializableStuff) deserialize(byteArray);
        assertThat(SerializableStuff.staticField, is(2));
    }

}
