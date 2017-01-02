
package frigo.graph;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

public class GraphTest {

    private Graph graph = new Graph();

    @Test
    public void getNodes_returns_copy_of_nodes () {
        Node a = graph.addNode();
        Node b = graph.addNode();

        Collection<Node> nodes = graph.getNodes();

        Node c = graph.addNode();

        assertThat(nodes, hasItems(a, b));
        assertThat(nodes, not(hasItem(c)));
    }

    @Test
    public void getEdges_returns_copy_of_edges () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b, 1);
        Edge bc = graph.addEdge(b, c, 2);

        Collection<Edge> edges = graph.getEdges();

        Edge ca = graph.addEdge(c, a, 3);

        assertThat(edges, hasItem(ab));
        assertThat(edges, hasItem(bc));
        assertThat(edges, not(hasItem(ca)));
    }

    @Test
    public void getEdges_returns_edges_of_a_node () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b, 1);
        Edge bc = graph.addEdge(b, c, 2);
        Edge ca = graph.addEdge(c, a, 3);

        Collection<Edge> edges = graph.outEdges(a);
        assertThat(edges, hasItem(ab));
        assertThat(edges, not(hasItem(bc)));
        assertThat(edges, hasItem(ca));
    }

}
