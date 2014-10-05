
package frigo.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph<T, W extends Comparable<W>> {

    private List<T> nodes = new LinkedList<>();
    private List<Edge<T, W>> edges = new LinkedList<>();

    public List<T> getNodes () {
        return new ArrayList<>(nodes);
    }

    public List<Edge<T, W>> getEdges () {
        return new ArrayList<>(edges);
    }

    public void addNode (T node) {
        nodes.add(node);
    }

    @SafeVarargs
    public final void addNodes (T... nodesToAdd) {
        for( T node : nodesToAdd ){
            addNode(node);
        }
    }

    public void addEdge (Edge<T, W> edge) {
        edges.add(edge);
    }

    @SafeVarargs
    public final void addEdges (Edge<T, W>... edgesToAdd) {
        for( Edge<T, W> edge : edgesToAdd ){
            addEdge(edge);
        }
    }

}
