
package frigo.graph;

import java.util.List;
import java.util.Vector;

public class VectorEdgeMap<T> implements EdgeMap<T> {

    protected T defaultValue;
    protected final List<T> v;

    protected VectorEdgeMap (int n) {
        this(n, null);
    }

    protected VectorEdgeMap (int n, T defaultValue) {
        this.defaultValue = defaultValue;
        v = new Vector<>();
        for( int i = 0; i < n; i++ ){
            v.add(i, defaultValue);
        }
    }

    @Override
    public T get (Edge edge) {
        return v.get(edge.id);
    }

    @Override
    public void set (Edge edge, T obj) {
        v.set(edge.id, obj);
    }

    protected void addEdge (Edge x) {
        if( x.id != size() ){
            throw new IllegalArgumentException("Added edge must have an id of n");
        }
        v.add(defaultValue);
    }

    protected void removeEdge (Edge x) {
        if( x.id != size() - 1 ){
            throw new IllegalArgumentException("Removed edge must have an id of n-1");
        }
        v.remove(x.id);
    }

    protected int size () {
        return v.size();
    }

    protected void swapEdge (Edge x, Edge y) {
        T temp = v.get(x.id);
        v.set(x.id, v.get(y.id));
        v.set(y.id, temp);
    }
}
