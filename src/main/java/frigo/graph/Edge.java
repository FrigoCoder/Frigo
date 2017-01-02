
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
}
