
package frigo.lang;

import static frigo.lang.Rethrow.canThrow;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;

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
        }catch( FileNotFoundException e ){
        }
    }

    @Test
    public void declare_exceptions () {
        try{
            canThrow(FileNotFoundException.class);
        }catch( FileNotFoundException e ){
        }
    }

    @Test
    public void return_values () {
        try{
            assertThat(canThrow(intSneakilyThrows(), FileNotFoundException.class), Matchers.is(1));
        }catch( FileNotFoundException e ){
        }
    }

    @Test
    public void void_methods () {
        try{
            canThrow( () -> sneakilyThrows(), FileNotFoundException.class);
        }catch( FileNotFoundException e ){
        }
    }

    @Test
    public void try_catch_constructor () {
        try( Rethrow canThrow = new Rethrow(FileNotFoundException.class) ){
            sneakilyThrows();
        }catch( FileNotFoundException e ){
        }
    }

    @Test
    public void try_catch_method () {
        try( Rethrow canThrow = Rethrow.canThrow(FileNotFoundException.class) ){
            sneakilyThrows();
        }catch( FileNotFoundException e ){
        }
    }

    @SneakyThrows
    private void sneakilyThrows () {
        try{
            return;
        }catch( Exception e ){
            throw new FileNotFoundException();
        }
    }

    @SneakyThrows
    private int intSneakilyThrows () {
        try{
            return 1;
        }catch( Exception e ){
            throw new FileNotFoundException();
        }
    }

    @SneakyThrows
    private void loudlyThrows () throws FileNotFoundException {
        throw new FileNotFoundException();
    }

}
