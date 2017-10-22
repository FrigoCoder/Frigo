
package frigo.lang;

import static frigo.lang.Environment.get;
import static frigo.lang.Environment.has;
import static frigo.lang.Environment.remove;
import static frigo.lang.Environment.set;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EnvironmentTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isEnv_returns_true_on_existing_environment_variable () {
        set("whatever", "who cares");
        assertThat(has("whatever"), is(true));
    }

    @Test
    public void isEnv_returns_false_on_missing_environment_variable () {
        remove("whatever");
        assertThat(has("whatever"), is(false));
    }

    @Test
    public void getEnv_returns_existing_environment_variable () {
        set("whatever", "who cares");
        assertThat(get("whatever"), is("who cares"));
    }

    @Test
    public void getEnv_throws_on_missing_environment_variable () {
        set("whatever", "who cares");
        remove("whatever");
        thrown.expect(IllegalArgumentException.class);
        get("whatever");
    }

}
