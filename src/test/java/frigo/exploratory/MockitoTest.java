
package frigo.exploratory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class MockitoTest {

    private static class Originale {

        public int getInt() {
            return 0;
        }
    }

    private Originale mock = mock(Originale.class);

    @Test
    public void mockito_rewrites_expectation_at_multiple_stubbing_call() {
        doReturn(1).when(mock).getInt();
        doReturn(2).when(mock).getInt();
        assertThat(mock.getInt(), is(2));
    }

}
