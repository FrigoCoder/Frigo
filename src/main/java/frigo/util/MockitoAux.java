
package frigo.util;

import static org.mockito.Mockito.mock;

import org.mockito.exceptions.Reporter;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.invocation.Location;
import org.mockito.stubbing.Answer;

public class MockitoAux {

    public static <T> T strictMock(Class<T> clazz) {
        return mock(clazz, new UnwantedAnswer<T>());
    }

    private static class UnwantedAnswer<T> implements Answer<T> {
        @Override
        public T answer(InvocationOnMock invocation) {
            // InvocationImpl is the only class implementing both of these interfaces. Might change in the future.
            DescribedInvocation wanted = (DescribedInvocation) invocation;

            // filter this please
            Location location = wanted.getLocation();

            Reporter reporter = new Reporter();
            reporter.neverWantedButInvoked(wanted, location);
            return null;
        }
    }

}
