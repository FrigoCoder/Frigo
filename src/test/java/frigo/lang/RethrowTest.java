
package frigo.lang;

import static frigo.lang.Rethrow.canThrow;
import static frigo.lang.Rethrow.unchecked;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RethrowTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void undeclared_throws () {
        thrown.expect(IOException.class);
        sneakilyThrows();
    }

    @Test
    public void undeclared_rethrows () {
        thrown.expect(IOException.class);
        sneakilyRethrows();
    }

    @Test
    public void redeclare_and_catch () {
        try{
            canThrow(IOException.class);
            sneakilyThrows();
            fail();
        }catch( IOException e ){
        }
    }

    private void sneakilyThrows () {
        throw unchecked(new IOException());
    }

    private void sneakilyRethrows () {
        try{
            openlyThrows();
        }catch( Exception e ){
            throw unchecked(e);
        }
    }

    private void openlyThrows () throws Exception {
        throw new IOException();
    }

}
