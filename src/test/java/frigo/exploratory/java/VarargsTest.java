
package frigo.exploratory.java;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VarargsTest {

    @Test
    public void empty_varargs_can_be_iterated() {
        assertThat(concat(), is(""));
    }

    private String concat(String... strings) {
        String result = "";
        for (String string : strings) {
            result += string;
        }
        return result;
    }

}
