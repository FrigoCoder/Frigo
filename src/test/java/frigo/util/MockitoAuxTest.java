
package frigo.util;

import static frigo.util.MockitoAux.strictMock;
import static frigo.util.MockitoAux.verifyImplicit;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import org.junit.Test;
import frigo.util.MockitoAux.ImplicitVerificationFailed;
import frigo.util.MockitoAux.UnstubbedInvocationInvoked;

public class MockitoAuxTest {

    private static class Originale {

        public int getOne () {
            return 1;
        }

        public int getSomething (int i) {
            return i;
        }
    }

    @Test
    public void strick_mock_behaves_like_a_mock_on_stubbed_invocation () {
        Originale mock = strictMock(Originale.class);
        doReturn(2).when(mock).getOne();
        int one = mock.getOne();
        assertThat(one, is(2));
    }

    @Test(expected = UnstubbedInvocationInvoked.class)
    public void strict_mock_throws_exception_on_unstubbed_invocation () {
        Originale mock = strictMock(Originale.class);
        mock.getOne();
    }

    @Test(expected = UnstubbedInvocationInvoked.class)
    public void strict_mock_throws_exception_if_stubbed_method_is_called_with_different_parameters () {
        Originale mock = strictMock(Originale.class);
        doReturn(20).when(mock).getSomething(2);
        mock.getSomething(3);
    }

    @Test
    public void verifyImplicit_does_not_throw_if_stubbed_method_is_called () {
        Originale mock = mock(Originale.class);
        doReturn(2).when(mock).getOne();
        mock.getOne();
        verifyImplicit(mock);
    }

    @Test(expected = ImplicitVerificationFailed.class)
    public void verifyImplicit_throws_if_stubbed_method_is_not_called () {
        Originale mock = mock(Originale.class);
        doReturn(2).when(mock).getOne();
        verifyImplicit(mock);
    }

    @Test(expected = ImplicitVerificationFailed.class)
    public void verifyImplicit_throws_if_stubbed_method_is_called_with_different_parameters () {
        Originale mock = mock(Originale.class);
        doReturn(20).when(mock).getSomething(2);
        mock.getSomething(3);
        verifyImplicit(mock);
    }

    // @Test(expected = ImplicitVerificationFailed.class)
    // public void verifyImplicit_throws_if_stubbed_method_is_called_less_than_expected () {
    // Originale mock = mock(Originale.class);
    // doReturn(2).doReturn(3).when(mock).getOne();
    // mock.getOne();
    // verifyImplicit(mock);
    // }

    // @Test
    // public void whatever() {
    // Originale mock = mock(Originale.class);
    // doReturn(13).when(mock).getSomething(intThat(greaterThanOrEqualTo(3)));
    // doReturn(12).when(mock).getSomething(intThat(greaterThanOrEqualTo(2)));
    // doReturn(11).when(mock).getSomething(intThat(greaterThanOrEqualTo(1)));
    // System.out.println(mock.getSomething(0));
    // System.out.println(mock.getSomething(1));
    // System.out.println(mock.getSomething(2));
    // System.out.println(mock.getSomething(3));
    // }

}
