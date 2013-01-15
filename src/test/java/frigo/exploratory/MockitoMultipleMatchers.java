
package frigo.exploratory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class MockitoMultipleMatchers {

    @Test
    public void testMultipleMatchers() {
        Function f = mock(Function.class);
        doReturn(1).when(f).evaluate(1);
        doReturn(2).when(f).evaluate(2);
        assertThat(f.evaluate(1), is(1));
        assertThat(f.evaluate(2), is(2));
    }

}
