
package frigo.exploratory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class FinallyDoesNotCompleteNormally {

    private static final int CATCH_RESULT = 1;
    private static final int FINALLY_RESULT = 2;
    private static final int TRY_RESULT = 0;

    @SuppressWarnings("finally")
    private static int catchOrFinallyReturn () {
        try{
            throw new Exception();
        }catch( Exception e ){
            return CATCH_RESULT;
        }finally{
            return FINALLY_RESULT;
        }
    }

    @SuppressWarnings("finally")
    private static int tryOrFinallyReturn () {
        try{
            return TRY_RESULT;
        }finally{
            return FINALLY_RESULT;
        }
    }

    @Test
    public void finallyOverridesCatchReturn () {
        assertThat(catchOrFinallyReturn(), is(FINALLY_RESULT));
    }

    @Test
    public void finallyOverridesTryReturn () {
        assertThat(tryOrFinallyReturn(), is(FINALLY_RESULT));
    }
}
