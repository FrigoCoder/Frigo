
package frigo.exploratory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.junit.Test;

public class SerializationDoesNotAffectStaticFields {

    private static class SerializableStuff implements Serializable {

        private static final long serialVersionUID = -3176918847542358086L;
        public static int staticField = 1;
        public int field = 1;
    }

    @Test
    public void testDeserializationRevertsChangesToObject () throws IOException, ClassNotFoundException {
        SerializableStuff object = new SerializableStuff();
        byte[] byteArray = toByteArray(object);
        object.field = 2;
        object = (SerializableStuff) fromByteArray(byteArray);
        assertThat(object.field, is(1));
    }

    @Test
    public void testDeserializationDoesNotAffectStaticFields () throws IOException, ClassNotFoundException {
        SerializableStuff object = new SerializableStuff();
        byte[] byteArray = toByteArray(object);
        SerializableStuff.staticField = 2;
        object = (SerializableStuff) fromByteArray(byteArray);
        assertThat(SerializableStuff.staticField, is(2));
    }

    private Object fromByteArray (byte[] byteArray) throws IOException, ClassNotFoundException {
        try( ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(byteArray)) ){
            return in.readObject();
        }
    }

    private byte[] toByteArray (Serializable object) throws IOException {
        try( ByteArrayOutputStream stream = new ByteArrayOutputStream() ){
            try( ObjectOutput out = new ObjectOutputStream(stream) ){
                out.writeObject(object);
            }
            return stream.toByteArray();
        }
    }

}
