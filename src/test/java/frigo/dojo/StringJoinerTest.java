
package frigo.dojo;

import static frigo.dojo.StringJoiner.join;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

public class StringJoinerTest {

    @Test
    public void join_returns_empty_string_on_null () {
        Collection<String> strings = null;
        assertThat(join(strings, ','), is(""));
    }

    @Test
    public void join_returns_empty_string_on_empty_collection () {
        Collection<String> strings = asList();
        assertThat(join(strings, ','), is(""));
    }

    @Test
    public void join_returns_empty_string_on_empty_string () {
        Collection<String> strings = asList("");
        assertThat(join(strings, ','), is(""));
    }

    @Test
    public void join_leaves_a_single_string_alone () {
        Collection<String> strings = asList("Hello World!");
        assertThat(join(strings, ','), is("Hello World!"));
    }

    @Test
    public void join_merges_two_strings_with_comma_delimiter () {
        Collection<String> strings = asList("Hello", "World!");
        assertThat(join(strings, ','), is("Hello,World!"));
    }

    @Test
    public void join_merges_many_strings_with_space_delimiter () {
        Collection<String> strings = asList("Hello", "World", "!");
        assertThat(join(strings, ' '), is("Hello World !"));
    }
}
