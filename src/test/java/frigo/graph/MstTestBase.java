
package frigo.graph;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MstTestBase {

    private Mst mst;

    public MstTestBase (Mst mst) {
        this.mst = mst;
    }

    @Test
    public void empty_graph_returns_empty_graph () {
        assertMst(list(), list());
    }

    @Test
    public void one_edge () {
        Edge<String, Double> ab = new Edge<>("A", "B", 1.0);

        assertMst(list(ab), list(ab));
    }

    @Test
    public void two_edges () {
        Edge<String, Double> ab = new Edge<>("A", "B", 1.0);
        Edge<String, Double> bc = new Edge<>("B", "C", 1.0);

        assertMst(list(ab, bc), list(ab, bc));
    }

    @Test
    public void three_edges_in_triangle () {
        Edge<String, Double> ab = new Edge<>("A", "B", 1.0);
        Edge<String, Double> bc = new Edge<>("B", "C", 2.0);
        Edge<String, Double> ca = new Edge<>("C", "A", 3.0);

        assertMst(list(ab, bc, ca), list(ab, bc));
    }

    @Test
    public void parallel_edges () {
        Edge<String, Double> ab1 = new Edge<>("A", "B", 1.0);
        Edge<String, Double> ab2 = new Edge<>("A", "B", 2.0);

        assertMst(list(ab1, ab2), list(ab1));
    }

    @Test
    public void wikipedia_example () {
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
        Edge<String, Double> fg = new Edge<>("F", "G", 11.0);

        assertMst(list(ab, ad, bc, bd, be, ce, de, df, ef, eg, fg), list(ab, ad, be, ce, df, eg));
    }

    @SafeVarargs
    private final List<Edge<String, Double>> list (Edge<String, Double>... items) {
        return Arrays.asList(items);
    }

    private void assertMst (List<Edge<String, Double>> edges, List<Edge<String, Double>> expected) {
        assertThat(mst.run(edges), containsInAnyOrder(expected.toArray()));
    }

}
