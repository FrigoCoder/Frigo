
package frigo.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.lang.ref.WeakReference;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class WeakObservableTest {

    protected Object action;
    protected WeakObservable observable;
    protected WeakObserver observerA;
    protected WeakObserver observerB;
    protected WeakReference<WeakObserver> referenceA1;
    protected WeakReference<WeakObserver> referenceA2;

    @Before
    public void setUp () {
        observable = new WeakObservable();
        observerA = mock(WeakObserver.class);
        observerB = mock(WeakObserver.class);
        referenceA1 = new WeakReference<>(observerA);
        referenceA2 = new WeakReference<>(observerA);
        action = Integer.valueOf(1);
    }

    @Test
    public void testAddObserver () {
        assertThat(observable.isObserverPresent(observerA), is(false));
        observable.addObserver(observerA);
        assertThat(observable.isObserverPresent(observerA), is(true));
        assertThat(observable.isObserverPresent(observerB), is(false));
        observable.addObserver(observerB);
        assertThat(observable.isObserverPresent(observerB), is(true));
    }

    @Test
    public void testDeleteObserver () {
        observable.addObserver(observerA);
        assertThat(observable.isObserverPresent(observerA), is(true));
        observable.addObserver(observerB);
        assertThat(observable.isObserverPresent(observerB), is(true));
        observable.deleteObserver(observerA);
        assertThat(observable.isObserverPresent(observerA), is(false));
        observable.deleteObserver(observerB);
        assertThat(observable.isObserverPresent(observerB), is(false));
    }

    @Test
    public void testGetReferencesToObserver () {
        assertThat(0, is(observable.getReferencesToObserver(observerA).size()));
        observable.addReference(referenceA1);
        observable.addReference(referenceA2);
        List<WeakReference<WeakObserver>> references = observable.getReferencesToObserver(observerA);
        assertThat(2, is(references.size()));
        assertThat(references.contains(referenceA1), is(true));
        assertThat(references.contains(referenceA2), is(true));
    }

    @Test
    public void testNotifyObservers () {
        observable.addObserver(observerA);
        observable.addObserver(observerB);
        observable.notifyObservers(action);
        verify(observerA).update(observable, action);
        verify(observerB).update(observable, action);
    }
}
