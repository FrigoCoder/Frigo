
package frigo.graph;

import java.util.Collection;

public interface Graph {

    Edge addEdge (Node x, Node y);

    Node addNode ();

    int countEdges ();

    int countNodes ();

    Collection<Edge> getEdges ();

    Collection<Node> getNodes ();

    <T> EdgeMap<T> newEdgeMap ();

    <T> NodeMap<T> newNodeMap ();

    void removeEdge (Edge x);

    void removeNode (Node x);
}
