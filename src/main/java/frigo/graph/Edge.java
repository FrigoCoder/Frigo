
package frigo.graph;

public class Edge<T, W> {

    public T source;
    public T target;
    public W weight;

    public Edge (T source, T target, W weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public String toString () {
        return "Edge{" + source + "->" + target + "@" + weight + "}";
    }

}
