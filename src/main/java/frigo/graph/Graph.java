
package frigo.graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {

    private HashSet<Object> nodes = new HashSet<>();
    private HashSet<Edge> edges = new HashSet<>();
    private HashMap<Object, HashSet<Edge>> outEdges = new HashMap<>();

    public HashSet<Object> getNodes () {
        return clone(nodes);
    }

    public void addNode (Object node) {
        nodes.add(node);
        outEdges.putIfAbsent(node, new HashSet<>());
    }

    public void addNodes (Object... nodesToAdd) {
        for( Object node : nodesToAdd ){
            addNode(node);
        }
    }

    public HashSet<Edge> getEdges () {
        return clone(edges);
    }

    public Edge addEdge (Object source, Object target, Comparable<?> weight) {
        return addEdge(new Edge(source, target, weight));
    }

    public Edge addEdge (Edge edge) {
        addNode(edge.node1);
        addNode(edge.node2);
        edges.add(edge);
        outEdges.get(edge.node1).add(edge);
        outEdges.get(edge.node2).add(edge);
        return edge;
    }

    public HashSet<Edge> getEdges (Object source) {
        return clone(outEdges.get(source));
    }

    private <T> HashSet<T> clone (HashSet<T> set) {
        return new HashSet<>(set);
    }

}
