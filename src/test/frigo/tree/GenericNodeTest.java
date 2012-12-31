
package frigo.tree;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class GenericNodeTest {

    private GenericNode<Integer, Integer> node;
    private GenericNode<Integer, Integer> other;

    @Before
    public void setUp () {
        node = new GenericNode<>(1, 2);
        other = new GenericNode<>(2, 3);
    }

    @Test
    public void testCanBeCreated () {
        assertThat(node.getKey(), is(1));
        assertThat(node.key, is(1));
        assertThat(node.value, is(2));
    }

    @Test
    public void testSetAndGetLeftWorks () {
        node.setLeft(other);
        assertThat(node.getLeft(), is(other));
    }

    @Test
    public void testSetAndGetRightWorks () {
        node.setRight(other);
        assertThat(node.getRight(), is(other));
    }

}
