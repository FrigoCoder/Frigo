
package frigo.util;

import static frigo.util.Environment.get;
import static frigo.util.Environment.has;
import static frigo.util.Environment.remove;
import static frigo.util.Environment.set;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EnvironmentTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isEnv_returns_true_on_existing_environment_variable () throws Exception {
        set("whatever", "who cares");
        assertThat(has("whatever"), is(true));
    }

    @Test
    public void isEnv_returns_false_on_missing_environment_variable () throws Exception {
        remove("whatever");
        assertThat(has("whatever"), is(false));
    }

    @Test
    public void getEnv_returns_existing_environment_variable () throws Exception {
        set("whatever", "who cares");
        assertThat(get("whatever"), is("who cares"));
    }

    @Test
    public void getEnv_throws_on_missing_environment_variable () throws Exception {
        set("whatever", "who cares");
        remove("whatever");
        thrown.expect(IllegalArgumentException.class);
        get("whatever");
    }

}
