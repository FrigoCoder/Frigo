
package frigo.exploratory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ListRemoveInt {

    @Test
    public void listRemoveIntegerRemovesValue () {
        List<Integer> v = new ArrayList<>();
        v.add(1);
        v.add(0);
        v.remove((Integer) 1);
        assertThat(v.get(0), is(0));
    }

    @Test
    public void listRemoveIntRemovesIndexNotValue () {
        List<Integer> v = new ArrayList<>();
        v.add(1);
        v.add(0);
        v.remove(1);
        assertThat(v.get(0), is(1));
    }
}
