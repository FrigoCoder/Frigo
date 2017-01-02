
package frigo.graph;

public class Graph {

    private NodeSet nodes = new NodeSet();
    private EdgeSet edges = new EdgeSet();
    private NodeMap<EdgeSet> outEdges = new NodeMap<>();

    public NodeSet getNodes () {
        return nodes.clone();
    }

    public Node addNode () {
        Node node = new Node(nodes.size());
        nodes.add(node);
        outEdges.putIfAbsent(node, new EdgeSet());
        return node;
    }

    public EdgeSet getEdges () {
        return edges.clone();
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

    public EdgeSet outEdges (Node source) {
        return outEdges.get(source).clone();
    }

}
