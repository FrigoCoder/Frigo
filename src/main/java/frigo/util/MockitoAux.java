
package frigo.util;

import static frigo.util.ArraysAux.toObjectArray;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.util.StringUtil.join;

import java.util.List;
import java.util.Queue;

import org.mockito.internal.stubbing.InvocationContainerImpl;
import org.mockito.internal.util.MockUtil;
import org.mockito.invocation.InvocationContainer;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;
import org.mockito.stubbing.Stubbing;

import frigo.lang.Reflection;

public class MockitoAux {

    private static final UnwantedAnswer<?> UNWANTED = new UnwantedAnswer<>();

    public static <T> T strictMock (Class<T> clazz) {
        return mock(clazz, UNWANTED);
    }

    public static <T> void verifyImplicit (T mock) {
        InvocationContainer invocationContainer = MockUtil.getMockHandler(mock).getInvocationContainer();
        InvocationContainerImpl invocationContainerImpl = (InvocationContainerImpl) invocationContainer;
        List<Stubbing> matchers = invocationContainerImpl.getStubbedInvocations();

        for( Stubbing matcher : matchers ){
            if( !wasAlmostFullyUsed(matcher) ){
                String message
                    = join("Implicitly wanted but not invoked:", matcher, matcher.getInvocation().getLocation(), "");
                throw new ImplicitVerificationFailed(message);
            }
        }
    }

    private static boolean wasAlmostFullyUsed (Stubbing matcher) {
        if( !matcher.wasUsed() ){
            return false;
        }
        Queue<Answer<?>> answers = Reflection.getField(matcher, "answers");
        return answers.size() <= 1;
    }

    public static Stubber doReturnValues (Object firstValue, Object secondValue, Object... otherValues) {
        Stubber stubber = doReturn(firstValue);
        stubber = stubber.doReturn(secondValue);
        for( Object value : otherValues ){
            stubber = stubber.doReturn(value);
        }
        return stubber;
    }

    public static Stubber doReturnElements (Object[] elements) {
        Stubber stubber = doReturn(elements[0]);
        for( int i = 1; i < elements.length; i++ ){
            stubber = stubber.doReturn(elements[i]);
        }
        return stubber;
    }

    public static Stubber doReturnElements (byte[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements (short[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements (int[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements (long[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements (float[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements (double[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements (boolean[] values) {
        return doReturnElements(toObjectArray(values));
    }

    public static Stubber doReturnElements (char[] values) {
        return doReturnElements(toObjectArray(values));
    }

}
