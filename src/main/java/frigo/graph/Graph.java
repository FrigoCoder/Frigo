
package frigo.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

    private Set<Node> nodes = new HashSet<>();
    private Set<Edge> edges = new HashSet<>();
    private Map<Node, Set<Edge>> outEdges = new HashMap<>();

    public Collection<Node> getNodes () {
        return clone(nodes);
    }

    public Node addNode () {
        Node node = new Node(nodes.size());
        nodes.add(node);
        outEdges.putIfAbsent(node, new HashSet<>());
        return node;
    }

    public Collection<Edge> getEdges () {
        return clone(edges);
    }

    public Edge addEdge (Node source, Node target, Comparable<?> weight) {
        return addEdge(new Edge(source, target, weight));
    }

    public Edge addEdge (Edge edge) {
        edges.add(edge);
        outEdges.get(edge.node1).add(edge);
        outEdges.get(edge.node2).add(edge);
        return edge;
    }

    public Collection<Edge> outEdges (Node source) {
        return clone(outEdges.get(source));
    }

    private <T> Set<T> clone (Set<T> set) {
        return new HashSet<>(set);
    }

}
