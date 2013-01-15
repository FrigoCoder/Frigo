
package frigo.exploratory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.junit.Test;

public class SerializableWithNonSerializableFields {

    private static class SerializableStuff implements Serializable {
        private static final long serialVersionUID = -3176918847542358086L;
        private NonSerializableStuff nonSerializableObject = new NonSerializableStuff();
    }

    private static class NonSerializableStuff {
    }

    @Test(expected = NotSerializableException.class)
    public void testCannotSerializeSerializableClassWithNonSerializableFields() throws IOException {
        SerializableStuff object = new SerializableStuff();
        File file = new File("whatever");
        OutputStream fileStream = new FileOutputStream(file);
        ObjectOutputStream stream = new ObjectOutputStream(fileStream);
        try {
            stream.writeObject(object);
        } finally {
            stream.close();
            file.delete();
        }
    }
}
