
package frigo.graph;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class KruskalTest {

    private Graph<String, Double> graph = new Graph<>();
    private Kruskal<String, Double> kruskal = new Kruskal<>(graph);

    @Test
    public void empty_graph_returns_empty_graph () {
        assertThat(kruskal.run(), empty());
    }

    @Test
    public void one_edge () {
        graph.addNodes("A", "B");

        Edge<String, Double> ab = new Edge<>("A", "B", 1.0);
        graph.addEdge(ab);

        assertThat(kruskal.run(), containsInAnyOrder(new Edge[] {ab}));
    }

    @Test
    public void two_edges () {
        graph.addNodes("A", "B", "C");

        Edge<String, Double> ab = new Edge<>("A", "B", 1.0);
        Edge<String, Double> bc = new Edge<>("B", "C", 1.0);
        graph.addEdges(ab, bc);

        assertThat(kruskal.run(), containsInAnyOrder(new Edge[] {ab, bc}));
    }

    @Test
    public void three_edges_in_triangle () {
        graph.addNodes("A", "B", "C");

        Edge<String, Double> ab = new Edge<>("A", "B", 1.0);
        Edge<String, Double> bc = new Edge<>("B", "C", 2.0);
        Edge<String, Double> ca = new Edge<>("C", "A", 3.0);
        graph.addEdges(ab, bc, ca);

        assertThat(kruskal.run(), containsInAnyOrder(new Edge[] {ab, bc}));
    }

    @Test
    public void parallel_edges () {
        graph.addNodes("A", "B");

        Edge<String, Double> ab1 = new Edge<>("A", "B", 1.0);
        Edge<String, Double> ab2 = new Edge<>("A", "B", 2.0);
        graph.addEdges(ab1, ab2);

        assertThat(kruskal.run(), containsInAnyOrder(new Edge[] {ab1}));
    }

    @Test
    public void wikipedia_example () {
        graph.addNodes("A", "B", "C", "D", "E", "F", "G");

        Edge<String, Double> ab = new Edge<>("A", "B", 7.0);
        Edge<String, Double> ad = new Edge<>("A", "D", 5.0);
        Edge<String, Double> bc = new Edge<>("B", "C", 8.0);
        Edge<String, Double> bd = new Edge<>("B", "D", 9.0);
        Edge<String, Double> be = new Edge<>("B", "E", 7.0);
        Edge<String, Double> ce = new Edge<>("C", "E", 5.0);
        Edge<String, Double> de = new Edge<>("D", "E", 15.0);
        Edge<String, Double> df = new Edge<>("D", "F", 6.0);
        Edge<String, Double> ef = new Edge<>("E", "F", 8.0);
        Edge<String, Double> eg = new Edge<>("E", "G", 9.0);
        Edge<String, Double> fg = new Edge<>("E", "G", 11.0);
        graph.addEdges(ab, ad, bc, bd, be, ce, de, df, ef, eg, fg);
        assertThat(kruskal.run(), containsInAnyOrder(new Edge[] {ab, ad, be, ce, df, eg}));
    }

}
