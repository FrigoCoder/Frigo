
package frigo.exploratory.java;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FinallyReturnOrderTest {

    private static final int TRY_RESULT = 0;
    private static final int CATCH_RESULT = 1;
    private static final int FINALLY_RESULT = 2;

    @Test
    public void return_in_finally_overrides_return_in_try () {
        assertThat(returnInTryAndReturnInFinally(), is(FINALLY_RESULT));
    }

    @SuppressWarnings("finally")
    private int returnInTryAndReturnInFinally () {
        try{
            return TRY_RESULT;
        }finally{
            return FINALLY_RESULT;
        }
    }

    @Test
    public void return_in_finally_overrides_return_in_catch () {
        assertThat(returnInCatchAndReturnInFinally(), is(FINALLY_RESULT));
    }

    @SuppressWarnings("finally")
    private int returnInCatchAndReturnInFinally () {
        try{
            throw new Exception();
        }catch( Exception e ){
            return CATCH_RESULT;
        }finally{
            return FINALLY_RESULT;
        }
    }

    @Test
    public void return_in_finally_overrides_throw_in_try () {
        assertThat(throwInTryAndReturnInFinally(), is(FINALLY_RESULT));
    }

    @SuppressWarnings("finally")
    private int throwInTryAndReturnInFinally () {
        try{
            throw new Exception();
        }finally{
            return FINALLY_RESULT;
        }
    }

    @Test
    public void return_in_finally_overrides_throw_in_catch () {
        assertThat(throwInCatchAndReturnInFinally(), is(FINALLY_RESULT));
    }

    @SuppressWarnings("finally")
    private int throwInCatchAndReturnInFinally () {
        try{
            throw new Exception();
        }catch( Exception e ){
            throw new Exception();
        }finally{
            return FINALLY_RESULT;
        }
    }

}
