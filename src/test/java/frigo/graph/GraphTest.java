
package frigo.graph;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class GraphTest {

    private Graph<String, Double> graph = new Graph<>();

    @Test
    public void getNodes_returns_copy_of_nodes () {
        graph.addNodes("A", "B");
        List<String> nodes = graph.getNodes();
        graph.addNodes("C");
        assertThat(nodes, hasItems("A", "B"));
        assertThat(nodes, not(hasItem("C")));
    }

    @Test
    public void getEdges_returns_copy_of_edges () {
        graph.addNodes("A", "B", "C");
        Edge<String, Double> ab = new Edge<>("A", "B", 1.0);
        Edge<String, Double> bc = new Edge<>("B", "C", 2.0);
        graph.addEdges(ab, bc);
        List<Edge<String, Double>> edges = graph.getEdges();
        Edge<String, Double> ca = new Edge<>("C", "A", 3.0);
        assertThat(edges, hasItem(ab));
        assertThat(edges, hasItem(bc));
        assertThat(edges, not(hasItem(ca)));
    }

}
