
package frigo.graph;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class DepthFirstSearchTest {

    private Graph graph = new Graph();
    private DepthFirstSearch.Visitor visitor = Mockito.mock(DepthFirstSearch.Visitor.class);
    private DepthFirstSearch search = new DepthFirstSearch(graph, visitor);

    @Test
    public void empty_graph () {
        search.run();

        Mockito.verifyNoMoreInteractions(visitor);
    }

    @Test
    public void graph_with_one_node () {
        Node a = graph.addNode();

        search.run();

        InOrder inorder = Mockito.inOrder(visitor);
        inorder.verify(visitor).initializeVertex(a);
        inorder.verify(visitor).startVertex(a);
        inorder.verify(visitor).discoverVertex(a);
        inorder.verify(visitor).finishVertex(a);
        inorder.verifyNoMoreInteractions();
    }

    @Test
    public void graph_with_two_nodes_but_no_edges () {
        Node a = graph.addNode();
        Node b = graph.addNode();

        search.run();

        InOrder inorder = Mockito.inOrder(visitor);
        inorder.verify(visitor).initializeVertex(a);
        inorder.verify(visitor).initializeVertex(b);

        inorder.verify(visitor).startVertex(a);
        inorder.verify(visitor).discoverVertex(a);
        inorder.verify(visitor).finishVertex(a);

        inorder.verify(visitor).startVertex(b);
        inorder.verify(visitor).discoverVertex(b);
        inorder.verify(visitor).finishVertex(b);

        inorder.verifyNoMoreInteractions();
    }

    @Test
    public void graph_with_two_nodes_and_an_edge_between_them () {
        Node a = graph.addNode();
        Node b = graph.addNode();

        Edge ab = graph.addEdge(a, b, 1);

        search.run();

        InOrder inorder = Mockito.inOrder(visitor);
        inorder.verify(visitor).initializeVertex(a);
        inorder.verify(visitor).initializeVertex(b);

        inorder.verify(visitor).startVertex(a);

        inorder.verify(visitor).discoverVertex(a);
        {
            inorder.verify(visitor).examineEdge(ab);
            inorder.verify(visitor).treeEdge(ab);
            {
                inorder.verify(visitor).discoverVertex(b);
                {
                    inorder.verify(visitor).examineEdge(ab);
                    inorder.verify(visitor).finishEdge(ab);
                }
                inorder.verify(visitor).finishVertex(b);
            }
            inorder.verify(visitor).finishEdge(ab);
        }
        inorder.verify(visitor).finishVertex(a);

        inorder.verifyNoMoreInteractions();
    }

    @Test
    public void graph_with_three_nodes_and_a_cycle_between_them () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b, 1);
        Edge bc = graph.addEdge(b, c, 1);
        Edge ca = graph.addEdge(c, a, 1);

        search.run();

        InOrder inorder = Mockito.inOrder(visitor);
        inorder.verify(visitor).initializeVertex(a);
        inorder.verify(visitor).initializeVertex(b);
        inorder.verify(visitor).initializeVertex(c);

        inorder.verify(visitor).startVertex(a);

        inorder.verify(visitor).discoverVertex(a);
        {
            inorder.verify(visitor).examineEdge(ab);
            inorder.verify(visitor).treeEdge(ab);
            {
                inorder.verify(visitor).discoverVertex(b);
                {
                    inorder.verify(visitor).examineEdge(ab);
                    inorder.verify(visitor).finishEdge(ab);

                    inorder.verify(visitor).examineEdge(bc);
                    inorder.verify(visitor).treeEdge(bc);
                    {
                        inorder.verify(visitor).discoverVertex(c);
                        {
                            inorder.verify(visitor).examineEdge(bc);
                            inorder.verify(visitor).finishEdge(bc);

                            inorder.verify(visitor).examineEdge(ca);
                            inorder.verify(visitor).backEdge(ca);
                            inorder.verify(visitor).finishEdge(ca);
                        }
                        inorder.verify(visitor).finishVertex(c);
                    }
                    inorder.verify(visitor).finishEdge(bc);
                }
                inorder.verify(visitor).finishVertex(b);
            }
            inorder.verify(visitor).finishEdge(ab);

            inorder.verify(visitor).examineEdge(ca);
            inorder.verify(visitor).finishEdge(ca);
        }
        inorder.verify(visitor).finishVertex(a);

        inorder.verifyNoMoreInteractions();
    }

    @Test
    public void undirected_edge_is_treedge_or_backedge_but_noth_both () {
        Node a = graph.addNode();
        Node b = graph.addNode();

        Edge ab = graph.addEdge(a, b, 1);
        Edge ba = graph.addEdge(b, a, 1);

        search.run();

        verify(visitor).treeEdge(ab);
        verify(visitor, never()).backEdge(ab);

        verify(visitor).backEdge(ba);
        verify(visitor, never()).treeEdge(ba);
    }

    /**
     * Based on https://en.wikipedia.org/wiki/Depth-first_search#Example
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

        Edge ab = graph.addEdge(a, b, 1);
        Edge ac = graph.addEdge(a, c, 1);
        Edge ae = graph.addEdge(a, e, 1);
        Edge bd = graph.addEdge(b, d, 1);
        Edge bf = graph.addEdge(b, f, 1);
        Edge cg = graph.addEdge(c, g, 1);
        Edge ef = graph.addEdge(e, f, 1);

        search.run();

        // A-C-G path
        verify(visitor).treeEdge(ac);
        verify(visitor).treeEdge(cg);

        // A-B-D path
        verify(visitor).treeEdge(ab);
        verify(visitor).treeEdge(bd);

        // A-B-F-E-A cycle
        verify(visitor).treeEdge(ab);
        verify(visitor).treeEdge(bf);
        verify(visitor).treeEdge(ef);
        verify(visitor).backEdge(ae);
    }

}
