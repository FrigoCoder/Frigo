
package frigo.graph;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

@Ignore("Test base")
public abstract class MstTestBase {

    private Graph graph = new Graph();
    private Mst mst;

    public MstTestBase (Mst mst) {
        this.mst = mst;
    }

    @Test
    public void empty_graph_returns_empty_graph () {
        assertMst();
    }

    @Test
    public void one_edge () {
        Edge ab = graph.addEdge("A", "B", 1.0);
        assertMst(ab);
    }

    @Test
    public void two_edges () {
        Edge ab = graph.addEdge("A", "B", 1.0);
        Edge bc = graph.addEdge("B", "C", 1.0);

        assertMst(ab, bc);
    }

    @Test
    public void three_edges_in_triangle () {
        Edge ab = graph.addEdge("A", "B", 1.0);
        Edge bc = graph.addEdge("B", "C", 2.0);
        graph.addEdge("C", "A", 3.0);

        assertMst(ab, bc);
    }

    @Test
    public void parallel_edges () {
        Edge ab1 = graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "B", 2.0);

        assertMst(ab1);
    }

    @Test
    public void wikipedia_example () {
        Edge ab = graph.addEdge("A", "B", 7.0);
        Edge ad = graph.addEdge("A", "D", 5.0);
        graph.addEdge("B", "C", 8.0);
        graph.addEdge("B", "D", 9.0);
        Edge be = graph.addEdge("B", "E", 7.0);
        Edge ce = graph.addEdge("C", "E", 5.0);
        graph.addEdge("D", "E", 15.0);
        Edge df = graph.addEdge("D", "F", 6.0);
        graph.addEdge("E", "F", 8.0);
        Edge eg = graph.addEdge("E", "G", 9.0);
        graph.addEdge("F", "G", 11.0);

        assertMst(ab, ad, be, ce, df, eg);
    }

    private void assertMst (Edge... expected) {
        assertThat(mst.run(graph), containsInAnyOrder(expected));
    }

}
