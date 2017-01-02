
package frigo.graph;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

@Ignore("Test base")
public abstract class MstTestBase {

    private Graph graph = new Graph();
    private EdgeMap<Integer> weights = new EdgeMap<>();
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

        Edge ab = graph.addEdge(a, b);

        weights.put(ab, 1);

        assertMst(ab);
    }

    @Test
    public void two_edges () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b);
        Edge bc = graph.addEdge(b, c);

        weights.put(ab, 1);
        weights.put(bc, 1);

        assertMst(ab, bc);
    }

    @Test
    public void three_edges_in_triangle () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b);
        Edge bc = graph.addEdge(b, c);
        Edge ca = graph.addEdge(c, a);

        weights.put(ab, 1);
        weights.put(bc, 2);
        weights.put(ca, 3);

        assertMst(ab, bc);
    }

    @Test
    public void parallel_edges () {
        Node a = graph.addNode();
        Node b = graph.addNode();

        Edge ab1 = graph.addEdge(a, b);
        Edge ab2 = graph.addEdge(a, b);

        weights.put(ab1, 1);
        weights.put(ab2, 2);

        assertMst(ab1);
        assertNotMst(ab2);
    }

    /**
     * Based on https://en.wikipedia.org/wiki/Kruskal's_algorithm#Example
     */

    @Test
    public void wikipedia_example () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();
        Node d = graph.addNode();
        Node e = graph.addNode();
        Node f = graph.addNode();
        Node g = graph.addNode();

        Edge ab = graph.addEdge(a, b);
        Edge ad = graph.addEdge(a, d);
        Edge bc = graph.addEdge(b, c);
        Edge bd = graph.addEdge(b, d);
        Edge be = graph.addEdge(b, e);
        Edge ce = graph.addEdge(c, e);
        Edge de = graph.addEdge(d, e);
        Edge df = graph.addEdge(d, f);
        Edge ef = graph.addEdge(e, f);
        Edge eg = graph.addEdge(e, g);
        Edge fg = graph.addEdge(f, g);

        weights.put(ab, 7);
        weights.put(ad, 5);
        weights.put(bc, 8);
        weights.put(bd, 9);
        weights.put(be, 7);
        weights.put(ce, 5);
        weights.put(de, 15);
        weights.put(df, 6);
        weights.put(ef, 8);
        weights.put(eg, 9);
        weights.put(fg, 11);

        assertMst(ab, ad, be, ce, df, eg);
    }

    private void assertMst (Edge... expected) {
        assertThat(mst.run(graph, weights), containsInAnyOrder(expected));
    }

    private void assertNotMst (Edge... unexpected) {
        assertThat(mst.run(graph, weights), not(containsInAnyOrder(unexpected)));
    }

}
