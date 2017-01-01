
package frigo.graph;

public class Edge implements Comparable<Edge> {

    public Object node1;
    public Object node2;
    public Comparable weight;

    public Edge (Object node1, Object node2, Comparable<?> weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    @Override
    public String toString () {
        return "Edge[" + node1 + "<->" + node2 + "@" + weight + "]";
    }

    @Override
    public int compareTo (Edge that) {
        return weight.compareTo(that.weight);
    }

    public Object getTarget (Object source) {
        return getOtherNode(source);
    }

    public Object getSource (Object target) {
        return getOtherNode(target);
    }

    public Object getOtherNode (Object node) {
        if( node1 == node ){
            return node2;
        }
        if( node2 == node ){
            return node1;
        }
        throw new IllegalArgumentException();
    }
}
