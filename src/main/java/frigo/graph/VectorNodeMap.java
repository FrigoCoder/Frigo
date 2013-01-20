
package frigo.graph;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Vector;

public class VectorNodeMap<T> implements NodeMap<T> {

    protected T defaultValue;
    protected final List<T> v;

    protected VectorNodeMap (int n) {
        this(n, null);
    }

    protected VectorNodeMap (int n, T defaultValue) {
        this.defaultValue = defaultValue;
        v = new Vector<>();
        for( int i = 0; i < n; i++ ){
            v.add(i, defaultValue);
        }
    }

    @Override
    public T get (Node node) {
        return v.get(node.id);
    }

    @Override
    public void set (Node node, T obj) {
        v.set(node.id, obj);
    }

    protected void addNode (Node x) {
        checkArgument(x.id == size());
        v.add(defaultValue);
    }

    protected void removeNode (Node x) {
        checkArgument(x.id == size() - 1);
        v.remove(x.id);
    }

    protected int size () {
        return v.size();
    }

    protected void swapNode (Node x, Node y) {
        T temp = v.get(x.id);
        v.set(x.id, v.get(y.id));
        v.set(y.id, temp);
    }
}
