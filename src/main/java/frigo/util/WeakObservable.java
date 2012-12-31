
package frigo.util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WeakObservable {

    protected List<WeakReference<WeakObserver>> observers;

    public WeakObservable () {
        observers = new ArrayList<>();
    }

    public void addObserver (WeakObserver observer) {
        removeDeadReferences();
        if( !isObserverPresent(observer) ){
            addReference(new WeakReference<>(observer));
        }
    }

    public void deleteObserver (WeakObserver observer) {
        removeDeadReferences();
        removeReferences(getReferencesToObserver(observer));
    }

    public void notifyObservers (Object arg) {
        removeDeadReferences();
        for( WeakReference<WeakObserver> reference : observers ){
            WeakObserver observer = reference.get();
            if( observer != null ){
                observer.update(this, arg);
            }
        }
    }

    protected void addReference (WeakReference<WeakObserver> reference) {
        observers.add(reference);
    }

    protected List<WeakReference<WeakObserver>> getReferencesToObserver (WeakObserver observer) {
        List<WeakReference<WeakObserver>> result = new ArrayList<>();
        for( WeakReference<WeakObserver> reference : observers ){
            if( reference.get() == observer ){
                result.add(reference);
            }
        }
        return result;
    }

    protected boolean isObserverPresent (WeakObserver observer) {
        return getReferencesToObserver(observer).size() != 0;
    }

    protected void removeDeadReferences () {
        observers.removeAll(getReferencesToObserver(null));
    }

    protected void removeReferences (List<WeakReference<WeakObserver>> references) {
        observers.removeAll(references);
    }
}
