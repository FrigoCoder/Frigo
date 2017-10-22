
package frigo.lang;

import static frigo.lang.Rethrow.canThrow;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import lombok.SneakyThrows;

public class RethrowTest {

    @Test
    public void redeclare_and_catch () {
        try{
            canThrow(IOException.class);
            sneakilyThrows();
            Assert.fail();
        }catch( IOException e ){
        }
    }

    @SneakyThrows
    private void sneakilyThrows () {
        throw new IOException();
    }

}
