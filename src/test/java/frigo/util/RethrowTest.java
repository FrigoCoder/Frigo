
package frigo.util;

import static frigo.util.Rethrow.rethrow;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RethrowTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void checked_exception_can_be_rethrown_without_declaring_it () {
        try{
            methodWithUndeclaredCheckedException();
        }finally{
            thrown.expect(Exception.class);
        }
    }

    private void methodWithUndeclaredCheckedException () {
        rethrow(new Exception());
    }

}
