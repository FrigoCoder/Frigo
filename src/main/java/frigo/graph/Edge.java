
package frigo.graph;

public class Edge implements Comparable<Edge> {

    public final Node node1;
    public final Node node2;
    public final Comparable weight;

    Edge (Node node1, Node node2, Comparable<?> weight) {
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

    public Node getTarget (Node source) {
        return getOtherNode(source);
    }

    public Node getSource (Node target) {
        return getOtherNode(target);
    }

    public Node getOtherNode (Node node) {
        if( node1 == node ){
            return node2;
        }
        if( node2 == node ){
            return node1;
        }
        throw new IllegalArgumentException();
    }
}
