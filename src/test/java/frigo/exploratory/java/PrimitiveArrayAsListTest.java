
package frigo.exploratory.java;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PrimitiveArrayAsListTest {

    @Test
    public void asList_of_primitive_array_creates_a_list_of_primitive_arrays () {
        double[] array = {1, 2, 3, 4, 5, 6};
        List<?> list = Arrays.asList(array);
        assertThat(list.get(0), instanceOf(double[].class));
    }
}
