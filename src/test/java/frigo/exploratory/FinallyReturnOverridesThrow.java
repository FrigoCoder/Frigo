
package frigo.exploratory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FinallyReturnOverridesThrow {

    private static final int FINALLY_RESULT = 1;

    private static int throwAndFinallyReturn() {
        try {
            throw new Exception();
        } finally {
            return FINALLY_RESULT;
        }
    }

    @Test
    public void finallyReturnOverridesThrownException() {
        assertThat(throwAndFinallyReturn(), is(FINALLY_RESULT));
    }
}
