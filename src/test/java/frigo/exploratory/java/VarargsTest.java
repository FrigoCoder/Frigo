
package frigo.exploratory.java;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class VarargsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void empty_varargs_can_be_iterated () {
        assertThat(concat(), is(""));
    }

    private String concat (String... strings) {
        String result = "";
        for( String string : strings ){
            result += string;
        }
        return result;
    }

    @Test
    public void unsafe_varargs_can_cause_runtime_exception () {
        List<String> list1 = Arrays.asList("a", "b");
        List<String> list2 = Arrays.asList("c", "d");
        thrown.expect(ClassCastException.class);
        unsafeVarargMethod(list1, list2);
    }

    @SafeVarargs
    private final void unsafeVarargMethod (List<String>... stringLists) {
        Object[] array = stringLists;
        array[0] = Arrays.asList(42);
        String firstString = stringLists[0].get(0);
    }

}
