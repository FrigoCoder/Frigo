
package frigo.graph;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

@Ignore("Test base")
public abstract class MstTestBase {

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
        Edge ab = new Edge("A", "B", 1.0);

        assertMst(list(ab), list(ab));
    }

    @Test
    public void two_edges () {
        Edge ab = new Edge("A", "B", 1.0);
        Edge bc = new Edge("B", "C", 1.0);

        assertMst(list(ab, bc), list(ab, bc));
    }

    @Test
    public void three_edges_in_triangle () {
        Edge ab = new Edge("A", "B", 1.0);
        Edge bc = new Edge("B", "C", 2.0);
        Edge ca = new Edge("C", "A", 3.0);

        assertMst(list(ab, bc, ca), list(ab, bc));
    }

    @Test
    public void parallel_edges () {
        Edge ab1 = new Edge("A", "B", 1.0);
        Edge ab2 = new Edge("A", "B", 2.0);

        assertMst(list(ab1, ab2), list(ab1));
    }

    @Test
    public void wikipedia_example () {
        Edge ab = new Edge("A", "B", 7.0);
        Edge ad = new Edge("A", "D", 5.0);
        Edge bc = new Edge("B", "C", 8.0);
        Edge bd = new Edge("B", "D", 9.0);
        Edge be = new Edge("B", "E", 7.0);
        Edge ce = new Edge("C", "E", 5.0);
        Edge de = new Edge("D", "E", 15.0);
        Edge df = new Edge("D", "F", 6.0);
        Edge ef = new Edge("E", "F", 8.0);
        Edge eg = new Edge("E", "G", 9.0);
        Edge fg = new Edge("F", "G", 11.0);

        assertMst(list(ab, ad, bc, bd, be, ce, de, df, ef, eg, fg), list(ab, ad, be, ce, df, eg));
    }

    @SafeVarargs
    private final List<Edge> list (Edge... items) {
        return Arrays.asList(items);
    }

    private void assertMst (List<Edge> edges, List<Edge> expected) {
        Graph graph = new Graph();
        for( Edge edge : edges ){
            graph.addEdge(edge);
        }
        assertThat(mst.run(graph), containsInAnyOrder(expected.toArray()));
    }

}
