
package frigo.graph;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

public class GraphTest {

    private Graph graph = new Graph();

    @Test
    public void nodes_contains_added_nodes () {
        Node a = graph.addNode();
        Node b = graph.addNode();

        assertEquals(graph.nodes(), asList(a, b));
    }

    @Test
    public void edges_contains_added_edges () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b);
        Edge bc = graph.addEdge(b, c);

        assertEquals(graph.edges(), asList(ab, bc));
    }

    @Test
    public void outEdges_returns_edges_of_a_node () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b);
        graph.addEdge(b, c);
        Edge ca = graph.addEdge(c, a);

        assertEquals(graph.outEdges(a), asList(ab, ca));
    }

    @Test
    public void nodes_retain_insertion_order () {
        List<Node> nodes = createNodes(100);
        assertEquals(graph.nodes(), nodes);
    }

    @Test
    public void edges_retain_insertion_order () {
        createNodes(100);
        List<Edge> edges = createEdges();
        assertEquals(graph.edges(), edges);
    }

    @Test
    public void outEdges_retain_insertion_order () {
        createNodes(100);
        List<Edge> edges = createEdges();
        assertEquals(graph.outEdges(firstNode()), edges);
    }

    private List<Node> createNodes (int n) {
        List<Node> nodes = new LinkedList<>();
        for( int i = 0; i < n; i++ ){
            nodes.add(graph.addNode());
        }
        return nodes;
    }

    private List<Edge> createEdges () {
        List<Edge> edges = new LinkedList<>();
        Node a = firstNode();
        for( Node b : graph.nodes() ){
            edges.add(graph.addEdge(a, b));
        }
        return edges;
    }

    private Node firstNode () {
        return graph.nodes().iterator().next();
    }

    @Test
    public void undirected_edges_are_equal_in_both_directions () {
        Node a = graph.addNode();
        Node b = graph.addNode();

        Edge ab = graph.addEdge(a, b);
        Edge ba = graph.addEdge(b, a);

        assertThat(ab, is(ba));
    }

    @Test
    public void undirected_edges_have_same_hashcode_in_both_directions () {
        Node a = graph.addNode();
        Node b = graph.addNode();

        Edge ab = graph.addEdge(a, b);
        Edge ba = graph.addEdge(b, a);

        assertThat(ab.hashCode(), is(ba.hashCode()));
    }

    private static <T> void assertEquals (Iterable<T> actual, List<T> expected) {
        assertThat(actual, IsIterableContainingInOrder.contains(expected.toArray()));
    }

}
