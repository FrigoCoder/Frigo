
package frigo.exploratory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.junit.Test;

public class SerializableWithNonSerializableFields {

    static class SerializableStuff implements Serializable {

        private static final long serialVersionUID = -3176918847542358086L;
        NonSerializableStuff nonSerializableObject = new NonSerializableStuff();
    }

    private static class NonSerializableStuff {

        public NonSerializableStuff () {
        }
    }

    @Test(expected = NotSerializableException.class)
    public void testCannotSerializeSerializableClassWithNonSerializableFields () throws IOException {
        SerializableStuff object = new SerializableStuff();
        File file = new File("whatever");
        try( ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file)) ){
            stream.writeObject(object);
        }finally{
            file.delete();
        }
    }
}
