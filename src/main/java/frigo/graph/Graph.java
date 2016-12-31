
package frigo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    private List<Object> nodes = new LinkedList<>();
    private List<Edge> edges = new LinkedList<>();
    private HashMap<Object, List<Edge>> outEdges = new HashMap<>();

    public List<Object> getNodes () {
        return clone(nodes);
    }

    public void addNode (Object node) {
        nodes.add(node);
        outEdges.put(node, new LinkedList<>());
    }

    public final void addNodes (Object... nodesToAdd) {
        for( Object node : nodesToAdd ){
            addNode(node);
        }
    }

    public List<Edge> getEdges () {
        return clone(edges);
    }

    public Edge addEdge (Object source, Object target, Comparable<?> weight) {
        Edge edge = new Edge(source, target, weight);
        addEdge(edge);
        return edge;
    }

    public void addEdge (Edge edge) {
        edges.add(edge);
        outEdges.get(edge.source).add(edge);
        outEdges.get(edge.target).add(edge);
    }

    public final void addEdges (Edge... edgesToAdd) {
        for( Edge edge : edgesToAdd ){
            addEdge(edge);
        }
    }

    public List<Edge> getEdges (Object source) {
        return clone(outEdges.get(source));
    }

    private <T> List<T> clone (List<T> list) {
        return new ArrayList<>(list);
    }
}
