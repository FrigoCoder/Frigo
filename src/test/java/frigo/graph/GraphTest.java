
package frigo.graph;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class GraphTest {

    private Graph graph = new Graph();

    @Test
    public void nodes_contains_added_nodes () {
        Node a = graph.addNode();
        Node b = graph.addNode();

        assertThat(graph.nodes(), hasItems(a, b));
    }

    @Test
    public void edges_contains_added_edges () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b);
        Edge bc = graph.addEdge(b, c);

        assertThat(graph.edges(), hasItems(ab, bc));
    }

    @Test
    public void outEdges_returns_edges_of_a_node () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b);
        Edge bc = graph.addEdge(b, c);
        Edge ca = graph.addEdge(c, a);

        Collection<Edge> edges = graph.outEdges(a);
        assertThat(edges, hasItems(ab, ca));
        assertThat(edges, not(hasItem(bc)));
    }

    @Test
    public void nodes_retain_insertion_order () {
        List<Node> nodes = new LinkedList<>();
        for( int i = 0; i < 100; i++ ){
            nodes.add(graph.addNode());
        }

        assertIterableEqual(graph.nodes(), nodes);
    }

    @Test
    public void edges_retain_insertion_order () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        List<Edge> edges = new LinkedList<>();
        for( int i = 0; i < 100; i++ ){
            edges.add(graph.addEdge(a, b));
        }

        assertIterableEqual(graph.edges(), edges);
    }

    @Test
    public void outEdges_retain_insertion_order () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        List<Edge> edges = new LinkedList<>();
        for( int i = 0; i < 100; i++ ){
            edges.add(graph.addEdge(a, b));
        }

        assertIterableEqual(graph.outEdges(a), edges);
    }

    private static <T> void assertIterableEqual (Iterable<T> actual, Iterable<T> expected) {
        Iterator<T> actualIt = actual.iterator();
        Iterator<T> expectedIt = expected.iterator();
        while( actualIt.hasNext() && expectedIt.hasNext() ){
            assertThat(actualIt.next(), is(expectedIt.next()));
        }
        assertThat(actualIt.hasNext(), is(expectedIt.hasNext()));
    }

}
