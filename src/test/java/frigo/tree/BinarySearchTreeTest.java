
package frigo.tree;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

    private IntegerNode node1;
    private IntegerNode node2;
    private BinarySearchTree<Integer, IntegerNode> tree;

    @Before
    public void setUp () {
        node1 = new IntegerNode(1);
        node2 = new IntegerNode(2);
        tree = new BinarySearchTree<>();
    }

    @Test
    public void testCanBeCreatedAndEmpty () {
        assertThat(tree.size(), is(0));
        assertThat(tree.isEmpty(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindInEmptyTree () {
        tree.find(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNonExistentKey () {
        tree.insert(node1);
        tree.find(node2.getKey());
    }

    @Test
    public void testInsertedNodeCanBeFound () {
        tree.insert(node1);
        assertThat(tree.find(node1.getKey()), is(node1));
    }

    @Test
    public void testInsertedNodeIncreasesSize () {
        tree.insert(node1);
        assertThat(tree.size(), is(1));
        assertThat(tree.isEmpty(), is(false));
    }

    @Test
    public void testInsertedNodesAreInCorrectStructure () {
        IntegerNode[] nodes = new IntegerNode[6];
        for( int i = 0; i < nodes.length; i++ ){
            nodes[i] = new IntegerNode(i);
        }
        tree.insert(nodes[2]);
        tree.insert(nodes[1]);
        tree.insert(nodes[3]);
        tree.insert(nodes[0]);
        tree.insert(nodes[5]);
        tree.insert(nodes[4]);
        assertThat(nodes[2].getLeft(), is(nodes[1]));
        assertThat(nodes[2].getRight(), is(nodes[3]));
        assertThat(nodes[1].getLeft(), is(nodes[0]));
        assertThat(nodes[1].getRight(), nullValue());
        assertThat(nodes[3].getLeft(), nullValue());
        assertThat(nodes[3].getRight(), is(nodes[5]));
        assertThat(nodes[5].getLeft(), is(nodes[4]));
        assertThat(nodes[5].getRight(), nullValue());
        assertThat(nodes[4].getLeft(), nullValue());
        assertThat(nodes[4].getRight(), nullValue());
    }

    @Test
    public void testInsertedNodesCanBeFound () {
        tree.insert(node1);
        tree.insert(node2);
        assertThat(tree.find(node1.getKey()), is(node1));
        assertThat(tree.find(node2.getKey()), is(node2));
    }

    @Test
    public void testInsertedNodesIncreaseSize () {
        tree.insert(node1);
        tree.insert(node2);
        assertThat(tree.size(), is(2));
        assertThat(tree.isEmpty(), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertingExistingKey () {
        tree.insert(node1);
        tree.insert(node1);
    }

}
