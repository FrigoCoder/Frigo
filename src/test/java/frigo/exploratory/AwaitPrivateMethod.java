
package frigo.exploratory;

import static com.jayway.awaitility.Awaitility.to;
import static com.jayway.awaitility.Awaitility.waitAtMost;
import static org.hamcrest.Matchers.is;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class AwaitPrivateMethod {

    boolean packagePrivateMethod() {
        return true;
    }

    private boolean privateMethod() {
        return true;
    }

    protected boolean protectedMethod() {
        return true;
    }

    @Test
    public void testAwaitDoesNotThrowOnPackagePrivateMethod() throws Exception {
        waitAtMost(10, TimeUnit.SECONDS).untilCall(to(this).packagePrivateMethod(), is(true));
    }

    @Test
    public void testAwaitDoesNotThrowOnProtectedMethod() throws Exception {
        waitAtMost(10, TimeUnit.SECONDS).untilCall(to(this).protectedMethod(), is(true));
    }

    @Test(expected = IllegalStateException.class)
    public void testAwaitThrowsOnPrivateMethod() throws Exception {
        waitAtMost(10, TimeUnit.SECONDS).untilCall(to(this).privateMethod(), is(true));
    }
}
