
package frigo.exploratory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.Test;

class PPC {

    public int get() {
        return 1;
    }
}

class PPM {

    int get() {
        return 1;
    }
}

public class PackagePrivateMock {

    @Test
    public void mockitoCanMockPackagePrivateClass() {
        PPC obj = mock(PPC.class);
        doReturn(2).when(obj).get();
        assertThat(obj.get(), is(2));
    }

    @Test
    public void mockitoCanMockPackagePrivateMethod() {
        PPM obj = mock(PPM.class);
        doReturn(2).when(obj).get();
        assertThat(obj.get(), is(2));
    }

    @Test
    public void mockitoCanSpyPackagePrivateClass() {
        PPC obj = spy(new PPC());
        doReturn(2).when(obj).get();
        assertThat(obj.get(), is(2));
    }

    @Test
    public void mockitoCanSpyPackagePrivateMethod() {
        PPM obj = spy(new PPM());
        doReturn(2).when(obj).get();
        assertThat(obj.get(), is(2));
    }
}
