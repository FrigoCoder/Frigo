
package frigo.lang;

import static frigo.lang.Rethrow.canThrow;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import lombok.SneakyThrows;

public class RethrowTest {

    @Test
    public void no_need_for_declarations () {
        sneakilyThrows();
        intSneakilyThrows();
    }

    @Test
    public void declarations_are_not_suppressed () {
        try{
            loudlyThrows();
        }catch( Exception e ){
        }
    }

    @Test
    public void declare_exceptions () {
        try{
            canThrow(Exception.class);
        }catch( Exception e ){
        }
    }

    @Test
    public void return_values () {
        try{
            assertThat(canThrow(intSneakilyThrows(), Exception.class), Matchers.is(1));
        }catch( Exception e ){
        }
    }

    @Test
    public void void_methods () {
        try{
            canThrow( () -> sneakilyThrows(), Exception.class);
        }catch( Exception e ){
        }
    }

    @Test
    public void try_catch_constructor () {
        try( Rethrow canThrow = new Rethrow(Exception.class) ){
            sneakilyThrows();
        }catch( Exception e ){
        }
    }

    @Test
    public void try_catch_method () {
        try( Rethrow canThrow = Rethrow.canThrow(Exception.class) ){
            sneakilyThrows();
        }catch( Exception e ){
        }
    }

    @SneakyThrows
    private void sneakilyThrows () {
        try{
            return;
        }catch( Exception e ){
            throw new Exception();
        }
    }

    @SneakyThrows
    private int intSneakilyThrows () {
        try{
            return 1;
        }catch( Exception e ){
            throw new Exception();
        }
    }

    @SneakyThrows
    private void loudlyThrows () throws Exception {
        throw new Exception();
    }

}
