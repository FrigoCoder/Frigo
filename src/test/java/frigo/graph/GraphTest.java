
package frigo.graph;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

public class GraphTest {

    private Graph graph = new Graph();

    @Test
    public void nodes_contains_added_nodes () {
        Node a = graph.addNode();
        Node b = graph.addNode();

        assertThat(graph.nodes(), hasItems(a, b));
    }

    @Test
    public void edges_contains_added_edges () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b);
        Edge bc = graph.addEdge(b, c);

        assertThat(graph.edges(), hasItems(ab, bc));
    }

    @Test
    public void outEdges_returns_edges_of_a_node () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Node c = graph.addNode();

        Edge ab = graph.addEdge(a, b);
        Edge bc = graph.addEdge(b, c);
        Edge ca = graph.addEdge(c, a);

        Collection<Edge> edges = graph.outEdges(a);
        assertThat(edges, hasItems(ab, ca));
        assertThat(edges, not(hasItem(bc)));
    }

    @Test
    public void nodes_retain_insertion_order () {
        Node[] nodes = new Node[100];
        for( int i = 0; i < nodes.length; i++ ){
            nodes[i] = graph.addNode();
        }

        int i = 0;
        for( Node node : graph.nodes() ){
            assertThat(node, sameInstance(nodes[i]));
            i++;
        }
    }

    @Test
    public void edges_retain_insertion_order () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Edge[] edges = new Edge[100];
        for( int i = 0; i < edges.length; i++ ){
            edges[i] = graph.addEdge(a, b);
        }
        int i = 0;
        for( Edge edge : graph.edges() ){
            assertThat(edge, sameInstance(edges[i]));
            i++;
        }
    }

    @Test
    public void outEdges_retain_insertion_order () {
        Node a = graph.addNode();
        Node b = graph.addNode();
        Edge[] edges = new Edge[100];
        for( int i = 0; i < edges.length; i++ ){
            edges[i] = graph.addEdge(a, b);
        }
        int i = 0;
        for( Edge edge : graph.outEdges(a) ){
            assertThat(edge, sameInstance(edges[i]));
            i++;
        }
    }

}
