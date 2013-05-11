
package frigo.exploratory.commons;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AnonymousClassSerializationTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private class SerializableClass implements Serializable {
    }

    private Serializable object = new SerializableClass();

    @Test
    public void anonymous_classes_can_not_be_serialized_if_the_containing_class_is_not_serializable () {
        thrown.expect(SerializationException.class);
        SerializationUtils.serialize(object);
    }

}
