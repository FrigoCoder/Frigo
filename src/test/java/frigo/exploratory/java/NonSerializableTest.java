
package frigo.exploratory.java;

import static org.apache.commons.lang3.SerializationUtils.serialize;

import java.io.NotSerializableException;
import java.io.Serializable;

import org.apache.commons.lang3.SerializationException;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NonSerializableTest {

    public static class SerializableClass implements Serializable {

        public NonSerializableClass nonSerializableObject = new NonSerializableClass();
    }

    public static class NonSerializableClass {

        public NonSerializableClass () {
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void serializable_class_with_nonserializable_fields_can_not_be_serialized () {
        SerializableClass object = new SerializableClass();
        thrown.expect(SerializationException.class);
        thrown.expectCause(Matchers.<Throwable> instanceOf(NotSerializableException.class));
        serialize(object);
    }
}
