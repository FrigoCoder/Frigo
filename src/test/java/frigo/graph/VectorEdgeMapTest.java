
package frigo.graph;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class VectorEdgeMapTest {

    protected Edge edge0;
    protected Edge edge1;
    protected VectorEdgeMap<Integer> edgeMap;
    protected int n;

    @Before
    public void setUp () {
        edge0 = new Edge(0);
        edge1 = new Edge(1);
        edgeMap = new VectorEdgeMap<>(1, 2);
    }

    @Test
    public void testAddEdge () {
        edgeMap.addEdge(edge1);
        assertThat(edgeMap.size(), equalTo(2));
        assertThat(edgeMap.get(edge1), equalTo(edgeMap.defaultValue));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWrongEdge () {
        edgeMap.addEdge(new Edge(3));
    }

    @Test
    public void testGetAndSet () {
        assertThat(edgeMap.get(edge0), equalTo(edgeMap.defaultValue));
        edgeMap.set(edge0, 3);
        assertThat(edgeMap.get(edge0), equalTo(3));
    }

    @Test
    public void testRemoveEdge () {
        edgeMap.removeEdge(edge0);
        assertThat(edgeMap.size(), equalTo(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWrongEdge () {
        edgeMap.removeEdge(new Edge(3));
    }

    @Test
    public void testSwapEdge () {
        edgeMap.addEdge(edge1);
        edgeMap.set(edge0, 0);
        edgeMap.set(edge1, 1);
        edgeMap.swapEdge(edge0, edge1);
        assertThat(edgeMap.get(edge0), equalTo(1));
        assertThat(edgeMap.get(edge1), equalTo(0));
    }

    @Test
    public void testVectorEdgeMapInt () {
        edgeMap = new VectorEdgeMap<>(1);
        assertThat(edgeMap.defaultValue, equalTo(null));
        assertThat(edgeMap.size(), equalTo(1));
    }

    @Test
    public void testVectorEdgeMapIntT () {
        assertThat(edgeMap.defaultValue, equalTo(2));
        assertThat(edgeMap.size(), equalTo(1));
    }
}
