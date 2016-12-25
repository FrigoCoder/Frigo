
package frigo.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    private List<Object> nodes = new LinkedList<>();
    private List<Edge> edges = new LinkedList<>();

    public List<Object> getNodes () {
        return new ArrayList<>(nodes);
    }

    public void addNode (Object node) {
        nodes.add(node);
    }

    @SafeVarargs
    public final void addNodes (Object... nodesToAdd) {
        for( Object node : nodesToAdd ){
            addNode(node);
        }
    }

    public List<Edge> getEdges () {
        return new ArrayList<>(edges);
    }

    public void addEdge (Edge edge) {
        edges.add(edge);
    }

    @SafeVarargs
    public final void addEdges (Edge... edgesToAdd) {
        for( Edge edge : edgesToAdd ){
            addEdge(edge);
        }
    }

}
