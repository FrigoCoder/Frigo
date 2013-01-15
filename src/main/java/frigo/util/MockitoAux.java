
package frigo.util;

import static frigo.util.ArraysAux.toObjectArray;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.util.StringJoiner.join;

import java.util.List;

import org.mockito.internal.invocation.StubInfoImpl;
import org.mockito.internal.stubbing.InvocationContainer;
import org.mockito.internal.stubbing.StubbedInvocationMatcher;
import org.mockito.internal.util.MockUtil;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

public class MockitoAux {

    public static UnwantedAnswer<?> UNWANTED = new UnwantedAnswer<>();

    public static <T> T strictMock(Class<T> clazz) {
        return mock(clazz, UNWANTED);
    }

    private static class UnwantedAnswer<T> implements Answer<T> {

        @Override
        public T answer(InvocationOnMock invocation) {
            // InvocationImpl is the only class implementing both of these interfaces. Might change in the future.
            DescribedInvocation invoked = (DescribedInvocation) invocation;

            String message =
                    join("Unstubbed invocation:", invoked, "Never wanted but invoked here: ", invoked.getLocation(), "");
            throw new UnstubbedInvocationInvoked(message);
        }
    }

    public static <T> void verifyImplicit(T mock) {
        InvocationContainer invocationContainer = new MockUtil().getMockHandler(mock).getInvocationContainer();

        List<StubbedInvocationMatcher> matchers = invocationContainer.getStubbedInvocations();
        List<Invocation> invocations = invocationContainer.getInvocations();

        for (StubbedInvocationMatcher matcher : matchers) {
            boolean foundMatchingInvocation = false;
            for (Invocation invocation : invocations) {
                if (matcher.matches(invocation)) {
                    foundMatchingInvocation = true;
                    matcher.markStubUsed(invocation);
                    invocation.markStubbed(new StubInfoImpl(matcher));
                    break;
                }
            }
            if (!foundMatchingInvocation) {
                String message = join("Implicitly wanted but not invoked:", matcher, matcher.getLocation(), "");
                throw new ImplicitVerificationFailed(message);
            }
        }
    }

    public static Stubber doReturnValues(Object firstValue, Object secondValue, Object... otherValues) {
        Stubber stubber = doReturn(firstValue);
        stubber = stubber.doReturn(secondValue);
        for (Object value : otherValues) {
            stubber = stubber.doReturn(value);
        }
        return stubber;
    }

    public static Stubber doReturnElements(Object[] elements) {
        Stubber stubber = doReturn(elements[0]);
        for (int i = 1; i < elements.length; i++) {
            stubber = stubber.doReturn(elements[i]);
        }
        return stubber;
    }

    public static Stubber doReturnElements(byte[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements(short[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements(int[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements(long[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements(float[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements(double[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements(boolean[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements(char[] values) {
        return doReturnElements(toObjectArray(values));
    }

}
