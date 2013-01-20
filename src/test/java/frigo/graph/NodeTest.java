
package frigo.graph;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NodeTest {

    protected Node node0;
    protected Node node1;
    protected Node node2;

    @Before
    public void setUp () {
        Node.serials.set(0);
        node0 = new Node(0);
        node1 = new Node(1);
        node2 = new Node(1);
    }

    @Test
    public void testNode () {
        checkNode(node0, 0, 0);
        checkNode(node1, 1, 1);
        checkNode(node2, 1, 2);
        assertThat(Node.serials.get(), is(3L));
    }

    @Test
    public void testCompareTo () {
        assertThat(node0.compareTo(node0), is(0));
        assertThat(node0.compareTo(node1), is(-1));
        assertThat(node0.compareTo(node2), is(-1));
        assertThat(node1.compareTo(node0), is(1));
        assertThat(node1.compareTo(node1), is(0));
        assertThat(node1.compareTo(node2), is(-1));
        assertThat(node2.compareTo(node0), is(1));
        assertThat(node2.compareTo(node1), is(1));
        assertThat(node2.compareTo(node2), is(0));
    }

    @Test
    public void testEqualsNode () {
        assertThat(node0, equalTo(node0));
        assertThat(node0, not(equalTo(node1)));
        assertThat(node0, not(equalTo(node2)));
        assertThat(node1, not(equalTo(node0)));
        assertThat(node1, equalTo(node1));
        assertThat(node1, not(equalTo(node2)));
        assertThat(node2, not(equalTo(node0)));
        assertThat(node2, not(equalTo(node1)));
        assertThat(node2, equalTo(node2));
    }

    @Test
    public void testEqualsObject () {
        assertThat(node0, equalTo((Object) node0));
        assertThat(node0, not(equalTo((Object) node1)));
        assertThat(node0, not(equalTo((Object) node2)));
        assertThat(node1, not(equalTo((Object) node0)));
        assertThat(node1, equalTo((Object) node1));
        assertThat(node1, not(equalTo((Object) node2)));
        assertThat(node2, not(equalTo((Object) node0)));
        assertThat(node2, not(equalTo((Object) node1)));
        assertThat(node2, equalTo((Object) node2));
    }

    @Test
    public void testEqualsObjectUnrelated () {
        Object obj = new Object();
        assertThat(node0, not(equalTo(null)));
        assertThat(node1, not(equalTo(null)));
        assertThat(node2, not(equalTo(null)));
        assertThat(node0, not(equalTo(obj)));
        assertThat(node1, not(equalTo(obj)));
        assertThat(node2, not(equalTo(obj)));
    }

    @Test
    public void testHashCode () {
        assertThat(node0.hashCode(), equalTo(node0.hashCode()));
        assertThat(node0.hashCode(), not(equalTo(node1.hashCode())));
        assertThat(node0.hashCode(), not(equalTo(node2.hashCode())));
        assertThat(node1.hashCode(), not(equalTo(node0.hashCode())));
        assertThat(node1.hashCode(), equalTo(node1.hashCode()));
        assertThat(node1.hashCode(), not(equalTo(node2.hashCode())));
        assertThat(node2.hashCode(), not(equalTo(node0.hashCode())));
        assertThat(node2.hashCode(), not(equalTo(node1.hashCode())));
        assertThat(node2.hashCode(), equalTo(node2.hashCode()));
    }

    private void checkNode (Node node, int id, long serial) {
        assertThat(node.id, is(id));
        assertThat(node.serial, is(serial));
    }
}
