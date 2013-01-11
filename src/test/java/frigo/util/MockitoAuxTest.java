
package frigo.util;

import static frigo.util.MockitoAux.strictMock;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;

import org.junit.Test;
import org.mockito.exceptions.verification.NeverWantedButInvoked;

public class MockitoAuxTest {

    private static class Originale {
        @Override
        public String toString() {
            return "Hello World";
        }
    }

    @Test
    public void strick_mock_behaves_like_a_mock_on_stubbed_invocation() {
        Originale mock = strictMock(Originale.class);
        doReturn("Hi").when(mock).toString();
        String s = mock.toString();
        assertThat(s, is("Hi"));

    }

    @Test(expected = NeverWantedButInvoked.class)
    public void strict_mock_throws_exception_on_unstubbed_invocation() {
        Originale mock = strictMock(Originale.class);
        mock.toString();
    }

    @Test
    public void strict_mock_exception_message_contains_line_of_invocation() {
        Originale mock = strictMock(Originale.class);
        mock.toString();
        fail("unimplemented");
    }

}
