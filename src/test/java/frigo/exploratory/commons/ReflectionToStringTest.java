
package frigo.exploratory.commons;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;

import com.google.common.annotations.VisibleForTesting;

public class ReflectionToStringTest {

    @VisibleForTesting
    static class ExampleClass {

        @SuppressWarnings("unused")
        private int integerField;
        String someString;
        protected long aLong;
        public byte aPublicByte;

        @Override
        public String toString () {
            return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
        }
    }

    @Test
    public void toString_returns_all_fields () {
        ExampleClass example = new ExampleClass();
        example.integerField = 1;
        example.someString = "Hello World!";
        example.aLong = 2L;
        example.aPublicByte = 3;

        assertThat(example.toString(),
            is("ReflectionToStringTest.ExampleClass[integerField=1,someString=Hello World!,aLong=2,aPublicByte=3]"));
    }
}
