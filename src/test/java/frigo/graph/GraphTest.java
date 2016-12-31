
package frigo.graph;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class GraphTest {

    private Graph graph = new Graph();

    @Test
    public void getNodes_returns_copy_of_nodes () {
        graph.addNodes("A", "B");

        List<Object> nodes = graph.getNodes();

        graph.addNodes("C");

        assertThat(nodes, hasItems("A", "B"));
        assertThat(nodes, not(hasItem("C")));
    }

    @Test
    public void getEdges_returns_copy_of_edges () {
        graph.addNodes("A", "B", "C");
        Edge ab = graph.addEdge("A", "B", 1.0);
        Edge bc = graph.addEdge("B", "C", 2.0);
        graph.addEdges(ab, bc);

        List<Edge> edges = graph.getEdges();

        Edge ca = graph.addEdge("C", "A", 3.0);

        assertThat(edges, hasItem(ab));
        assertThat(edges, hasItem(bc));
        assertThat(edges, not(hasItem(ca)));
    }

    @Test
    public void getEdges_returns_edges_of_a_node () {
        graph.addNodes("A", "B", "C");
        Edge ab = graph.addEdge("A", "B", 1.0);
        Edge bc = graph.addEdge("B", "C", 2.0);
        Edge ca = graph.addEdge("C", "A", 3.0);
        graph.addEdges(ab, bc, ca);

        List<Edge> edges = graph.getEdges("A");
        assertThat(edges, hasItem(ab));
        assertThat(edges, not(hasItem(bc)));
        assertThat(edges, hasItem(ca));
    }

}
