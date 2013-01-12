
package frigo.util;

import static frigo.util.MockitoAux.strictMock;
import static frigo.util.MockitoAux.verifyImplicit;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;

public class MockitoAuxTest {

    private static class Originale {

        public int getSomething (int i) {
            return i;
        }

        public Object getObject () {
            return null;
        }

        public byte getByte () {
            return 0;
        }

        public short getShort () {
            return 0;
        }

        public int getInt () {
            return 0;
        }

        public long getLong () {
            return 0;
        }

        public float getFloat () {
            return 0;
        }

        public double getDouble () {
            return 0;
        }

        public boolean getBoolean () {
            return false;
        }

        public char getChar () {
            return 0x00;
        }

    }

    private Originale mock;

    @Before
    public void setUp () {
        mock = mock(Originale.class);
    }

    @Test
    public void strick_mock_behaves_like_a_mock_on_stubbed_invocation () {
        mock = strictMock(Originale.class);
        doReturn(2).when(mock).getInt();
        int one = mock.getInt();
        assertThat(one, is(2));
    }

    @Test(expected = UnstubbedInvocationInvoked.class)
    public void strict_mock_throws_exception_on_unstubbed_invocation () {
        mock = strictMock(Originale.class);
        mock.getInt();
    }

    @Test(expected = UnstubbedInvocationInvoked.class)
    public void strict_mock_throws_exception_if_stubbed_method_is_called_with_different_parameters () {
        mock = strictMock(Originale.class);
        doReturn(20).when(mock).getSomething(2);
        mock.getSomething(3);
    }

    @Test
    public void verifyImplicit_does_not_throw_if_stubbed_method_is_called () {
        doReturn(2).when(mock).getInt();
        mock.getInt();
        verifyImplicit(mock);
    }

    @Test(expected = ImplicitVerificationFailed.class)
    public void verifyImplicit_throws_if_stubbed_method_is_not_called () {
        doReturn(2).when(mock).getInt();
        verifyImplicit(mock);
    }

    @Test(expected = ImplicitVerificationFailed.class)
    public void verifyImplicit_throws_if_stubbed_method_is_called_with_different_parameters () {
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
