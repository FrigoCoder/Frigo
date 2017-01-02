
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
        Node a = graph.addNode();
        Node b = graph.addNode();

        Edge ab = graph.addEdge(a, b, 1.0);

        assertMst(ab);
    }

    @Test
    public void two_edges () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b, 1.0);
        Edge bc = graph.addEdge(b, c, 1.0);

        assertMst(ab, bc);
    }

    @Test
    public void three_edges_in_triangle () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b, 1.0);
        Edge bc = graph.addEdge(b, c, 2.0);
        graph.addEdge(c, a, 3.0);

        assertMst(ab, bc);
    }

    @Test
    public void parallel_edges () {
        Node a = graph.addNode();
        Node b = graph.addNode();

        Edge ab1 = graph.addEdge(a, b, 1.0);
        graph.addEdge(a, b, 2.0);

        assertMst(ab1);
    }

    @Test
    public void wikipedia_example () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();
        Node d = graph.addNode();
        Node e = graph.addNode();
        Node f = graph.addNode();
        Node g = graph.addNode();

        Edge ab = graph.addEdge(a, b, 7.0);
        Edge ad = graph.addEdge(a, d, 5.0);
        graph.addEdge(b, c, 8.0);
        graph.addEdge(b, d, 9.0);
        Edge be = graph.addEdge(b, e, 7.0);
        Edge ce = graph.addEdge(c, e, 5.0);
        graph.addEdge(d, e, 15.0);
        Edge df = graph.addEdge(d, f, 6.0);
        graph.addEdge(e, f, 8.0);
        Edge eg = graph.addEdge(e, g, 9.0);
        graph.addEdge(f, g, 11.0);

        assertMst(ab, ad, be, ce, df, eg);
    }

    private void assertMst (Edge... expected) {
        assertThat(mst.run(graph), containsInAnyOrder(expected));
    }

}
