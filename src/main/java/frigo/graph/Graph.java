
package frigo.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private List<List<Edge>> outEdges = new ArrayList<>();

    public List<Node> getNodes () {
        return clone(nodes);
    }

    public Node addNode () {
        Node node = new Node(nodes.size());
        nodes.add(node);
        outEdges.add(new ArrayList<>());
        return node;
    }

    public List<Edge> getEdges () {
        return clone(edges);
    }

    public Edge addEdge (Node source, Node target, Comparable<?> weight) {
        return addEdge(new Edge(source, target, weight));
    }

    public Edge addEdge (Edge edge) {
        edges.add(edge);
        outEdges.get(edge.node1.index).add(edge);
        outEdges.get(edge.node2.index).add(edge);
        return edge;
    }

    public List<Edge> outEdges (Node source) {
        return clone(outEdges.get(source.index));
    }

    private <T> List<T> clone (List<T> list) {
        return new ArrayList<>(list);
    }

}
