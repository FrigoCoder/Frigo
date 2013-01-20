
package frigo.util;

import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.internal.util.RemoveFirstLine;

public class ImplicitVerificationFailed extends MockitoAssertionError {

    private static final long serialVersionUID = 6077658921657736528L;

    public ImplicitVerificationFailed (String message) {
        super(message);
    }

    @Override
    public String toString () {
        return new RemoveFirstLine().of(super.toString());
    }

}
