
package frigo.exploratory.java;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ListRemoveTest {

    private List<Integer> v = new ArrayList<>(asList(1, 0));

    @Test
    public void remove_Integer_removes_value () {
        v.remove((Integer) 1);
        assertThat(v.get(0), is(0));
    }

    @Test
    public void remove_int_removes_index () {
        v.remove(1);
        assertThat(v.get(0), is(1));
    }
}
