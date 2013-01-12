
package frigo.util;

import static org.mockito.Mockito.mock;
import static org.mockito.internal.util.StringJoiner.join;
import java.util.List;
import org.mockito.Mockito;
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

    public static <T> T strictMock (Class<T> clazz) {
        return mock(clazz, UNWANTED);
    }

    private static class UnwantedAnswer<T> implements Answer<T> {

        @Override
        public T answer (InvocationOnMock invocation) {
            // InvocationImpl is the only class implementing both of these interfaces. Might change in the future.
            DescribedInvocation invoked = (DescribedInvocation) invocation;

            String message =
                join("Unstubbed invocation:", invoked, "Never wanted but invoked here: ", invoked.getLocation(), "");
            throw new UnstubbedInvocationInvoked(message);
        }
    }

    public static <T> void verifyImplicit (T mock) {
        MockUtil mockUtil = new MockUtil();
        InvocationContainer invocationContainer = mockUtil.getMockHandler(mock).getInvocationContainer();

        List<StubbedInvocationMatcher> matchers = invocationContainer.getStubbedInvocations();
        List<Invocation> invocations = invocationContainer.getInvocations();

        for( StubbedInvocationMatcher matcher : matchers ){
            boolean foundMatchingInvocation = false;
            // System.out.println("matcher: " + matcher);
            for( Invocation invocation : invocations ){
                // System.out.println("    invocation: " + invocation);
                if( matcher.matches(invocation) ){
                    foundMatchingInvocation = true;
                    matcher.markStubUsed(invocation);
                    invocation.markStubbed(new StubInfoImpl(matcher));
                    break;
                }
            }
            if( !foundMatchingInvocation ){
                String message = join("Implicitly wanted but not invoked:", matcher, matcher.getLocation(), "");
                throw new ImplicitVerificationFailed(message);
            }
        }
    }

    public static <T> Stubber doReturnValues (T... values) {
        Stubber stubber = Mockito.doReturn(values[0]);
        for( int i = 1; i < values.length; i++ ){
            stubber = stubber.doReturn(values[i]);
        }
        return stubber;
    }

}
