
package frigo.graph;

public class Edge implements Comparable<Edge> {

    public Object source;
    public Object target;
    public Comparable weight;

    public Edge (Object source, Object target, Comparable<?> weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public String toString () {
        return "Edge{" + source + "->" + target + "@" + weight + "}";
    }

    @Override
    public int compareTo (Edge that) {
        return weight.compareTo(that.weight);
    }

}
