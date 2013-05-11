
package frigo.exploratory.java;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GenericOverride {

    private static class Base {

        public <T> T whatever (T value) {
            return value == null ? value : null;
        }
    }

    private static class Sub extends Base {

        @Override
        public <T> T whatever (T value) {
            return value;
        }
    }

    @Test
    public void generic_method_can_be_overridden () {
        Base source = new Sub();
        assertThat(source.whatever(1), is(1));
    }
}
