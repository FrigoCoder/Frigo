
package frigo.lang;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Value implements Serializable, Cloneable {

    @Override
    public boolean equals (Object obj) {
        if( obj == null || this.getClass() != obj.getClass() ){
            return false;
        }
        Value that = (Value) obj;
        return Arrays.equals(serialize(), that.serialize());
    }

    @Override
    public int hashCode () {
        return getClass().hashCode() * 31 + ArrayUtils.hashCode(serialize());
    }

    @Override
    public String toString () {
        return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
    }

    @Override
    public Value clone () {
        return SerializationUtils.clone(this);
    }

    public byte[] serialize () {
        return SerializationUtils.serialize(this);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Value> T deserialize (byte[] data) {
        return (T) SerializationUtils.deserialize(data);
    }

}
