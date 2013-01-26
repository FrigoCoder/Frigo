
package frigo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BoxTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private int left = 1;
    private int right = 4;
    private Box box = new Box(left, right);

    @Test
    public void fields_are_initialized_by_constructor () {
        assertThat(box.left, is(left));
        assertThat(box.right, is(right));
    }

    @Test
    public void constructor_throws_on_empty_interval () {
        try{
            box = new Box(left, left - 1);
        }finally{
            thrown.expect(IllegalArgumentException.class);
        }
    }

    @Test
    public void test_getCount () {
        assertThat(box.getCount(), is(right - left + 1));
    }

}
