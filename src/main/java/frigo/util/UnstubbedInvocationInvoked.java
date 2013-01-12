package frigo.util;

import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.internal.util.RemoveFirstLine;

public class UnstubbedInvocationInvoked extends MockitoAssertionError {

    private static final long serialVersionUID = -1553327294116183041L;

    public UnstubbedInvocationInvoked (String message) {
        super(message);
    }

    @Override
    public String toString () {
        return new RemoveFirstLine().of(super.toString());
    }

}