
package frigo.graph;

public interface EdgeMap<T> {

    T get (Edge node);

    void set (Edge node, T obj);
}
