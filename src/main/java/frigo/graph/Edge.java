
package frigo.graph;

public class Edge<T, W extends Comparable<W>> implements Comparable<Edge<T, W>> {

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

    @Override
    public int compareTo (Edge<T, W> edge) {
        return weight.compareTo(edge.weight);
    }

}
