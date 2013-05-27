
package frigo.electronics;

import static frigo.electronics.IEC60063.E48;
import static frigo.electronics.IEC60063.higher;
import static frigo.electronics.IEC60063.lower;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IEC60063Test {

    @Test
    public void test_lower () {
        assertThat(lower(1, E48), is(1.0));
        assertThat(lower(10.1, E48), is(10.0));
        assertThat(lower(10.5, E48), is(10.5));
        assertThat(lower(95.3, E48), is(95.3));
        assertThat(lower(99.9, E48), is(95.3));
    }

    @Test
    public void test_higher () {
        assertThat(higher(1, E48), is(1.0));
        assertThat(higher(10.1, E48), is(10.5));
        assertThat(higher(10.5, E48), is(10.5));
        assertThat(higher(95.3, E48), is(95.3));
        assertThat(higher(99.9, E48), is(100.0));
    }
}
