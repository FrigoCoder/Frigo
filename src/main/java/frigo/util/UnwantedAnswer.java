package frigo.util;

import static org.mockito.internal.util.StringJoiner.join;

import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class UnwantedAnswer<T> implements Answer<T> {

    @Override
    public T answer (InvocationOnMock invocation) {
        // InvocationImpl is the only class implementing both of these interfaces. Might change in the future.
        DescribedInvocation invoked = (DescribedInvocation) invocation;

        String message =
            join("Unstubbed invocation:", invoked, "Never wanted but invoked here: ", invoked.getLocation(), "");
        throw new UnstubbedInvocationInvoked(message);
    }
}