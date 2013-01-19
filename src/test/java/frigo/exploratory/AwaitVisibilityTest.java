
package frigo.exploratory;

import static com.jayway.awaitility.Awaitility.to;
import static com.jayway.awaitility.Awaitility.waitAtMost;
import static org.hamcrest.Matchers.is;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class AwaitVisibilityTest {

    protected boolean protectedMethod () {
        return true;
    }

    boolean packagePrivateMethod () {
        return true;
    }

    private boolean privateMethod () {
        return true;
    }

    @Test
    public void await_can_wait_for_protected_method () throws Exception {
        waitAtMost(10, TimeUnit.SECONDS).untilCall(to(this).protectedMethod(), is(true));
    }

    @Test
    public void await_can_wait_for_package_private_method () throws Exception {
        waitAtMost(10, TimeUnit.SECONDS).untilCall(to(this).packagePrivateMethod(), is(true));
    }

    @Test(expected = IllegalStateException.class)
    public void await_can_not_wait_for_private_method () throws Exception {
        waitAtMost(10, TimeUnit.SECONDS).untilCall(to(this).privateMethod(), is(true));
    }
}
