
package frigo.graph;

public class Graph {

    private final NodeSet nodes = new NodeSet();
    private final EdgeSet edges = new EdgeSet();
    private final NodeMap<EdgeSet> outEdges = new NodeMap<>();

    public Node addNode () {
        Node node = new Node(nodes.size());
        nodes.add(node);
        outEdges.putIfAbsent(node, new EdgeSet());
        return node;
    }

    public NodeSet nodes () {
        return nodes;
    }

    public Edge addEdge (Node source, Node target) {
        return addEdge(new Edge(source, target));
    }

    public Edge addEdge (Edge edge) {
        edges.add(edge);
        outEdges.get(edge.node1).add(edge);
        outEdges.get(edge.node2).add(edge);
        return edge;
    }

    public EdgeSet edges () {
        return edges;
    }

    public EdgeSet outEdges (Node source) {
        return outEdges.get(source);
    }

}
