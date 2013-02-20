
package frigo.exploratory.java;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GenericOverride {

    static class Empty {
    }

    private static class Base {
        public <T> T whatever(Class<T> clazz) throws Exception {
            return null;
        }
    }

    private static class Sub extends Base {
        @Override
        public <T> T whatever(Class<T> clazz) throws Exception {
            return clazz.newInstance();
        }
    }

    @Test
    public void generic_method_can_be_overridden() throws Exception {
        Base source = new Sub();
        assertThat(source.whatever(Empty.class), instanceOf(Empty.class));
    }

}
