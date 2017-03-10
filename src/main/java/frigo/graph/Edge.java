
package frigo.graph;

public class Edge {

    public final Node node1;
    public final Node node2;

    Edge (Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public String toString () {
        return "Edge[" + node1 + "<->" + node2 + "]";
    }

    public Node target (Node source) {
        return otherNode(source);
    }

    public Node source (Node target) {
        return otherNode(target);
    }

    public Node otherNode (Node node) {
        if( node1 == node ){
            return node2;
        }
        if( node2 == node ){
            return node1;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean equals (Object obj) {
        if( !(obj instanceof Edge) ){
            return false;
        }
        Edge other = (Edge) obj;
        return node1 == other.node1 && node2 == other.node2 || node1 == other.node2 && node2 == other.node1;
    }

    @Override
    public int hashCode () {
        return node1.hashCode() + node2.hashCode();
    }

}
