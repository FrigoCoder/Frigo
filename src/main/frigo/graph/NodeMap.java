
package frigo.graph;

public interface NodeMap<T> {

    T get (Node node);

    void set (Node node, T obj);
}
