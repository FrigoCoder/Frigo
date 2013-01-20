
package frigo.graph;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class VectorNodeMapTest {

    protected int n;
    protected Node node0;
    protected Node node1;
    protected VectorNodeMap<Integer> nodeMap;

    @Before
    public void setUp () {
        node0 = new Node(0);
        node1 = new Node(1);
        nodeMap = new VectorNodeMap<>(1, 2);
    }

    @Test
    public void testAddNode () {
        nodeMap.addNode(node1);
        assertThat(nodeMap.size(), equalTo(2));
        assertThat(nodeMap.get(node1), equalTo(nodeMap.defaultValue));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWrongNode () {
        nodeMap.addNode(new Node(3));
    }

    @Test
    public void testGetAndSet () {
        assertThat(nodeMap.get(node0), equalTo(nodeMap.defaultValue));
        nodeMap.set(node0, 3);
        assertThat(nodeMap.get(node0), equalTo(3));
    }

    @Test
    public void testRemoveNode () {
        nodeMap.removeNode(node0);
        assertThat(nodeMap.size(), equalTo(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWrongNode () {
        nodeMap.removeNode(new Node(3));
    }

    @Test
    public void testSwapNode () {
        nodeMap.addNode(node1);
        nodeMap.set(node0, 0);
        nodeMap.set(node1, 1);
        nodeMap.swapNode(node0, node1);
        assertThat(nodeMap.get(node0), equalTo(1));
        assertThat(nodeMap.get(node1), equalTo(0));
    }

    @Test
    public void testVectorNodeMapInt () {
        nodeMap = new VectorNodeMap<>(1);
        assertThat(nodeMap.defaultValue, equalTo(null));
        assertThat(nodeMap.size(), equalTo(1));
    }

    @Test
    public void testVectorNodeMapIntT () {
        assertThat(nodeMap.defaultValue, equalTo(2));
        assertThat(nodeMap.size(), equalTo(1));
    }
}
