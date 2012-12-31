
package frigo.graph;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EdgeTest {

    protected Edge edge0;
    protected Edge edge1;
    protected Edge edge2;

    @Before
    public void setUp () {
        edge0 = new Edge(0);
        edge1 = new Edge(1);
        edge2 = new Edge(1);
    }

    @After
    public void tearDown () {
        Edge.resetSerials();
    }

    @Test
    public void testCompareTo () {
        assertThat(edge0.compareTo(edge0), is(0));
        assertThat(edge0.compareTo(edge1), is(-1));
        assertThat(edge0.compareTo(edge2), is(-1));
        assertThat(edge1.compareTo(edge0), is(1));
        assertThat(edge1.compareTo(edge1), is(0));
        assertThat(edge1.compareTo(edge2), is(-1));
        assertThat(edge2.compareTo(edge0), is(1));
        assertThat(edge2.compareTo(edge1), is(1));
        assertThat(edge2.compareTo(edge2), is(0));
    }

    @Test
    public void testEdge () {
        checkEdge(edge0, 0, 0);
        checkEdge(edge1, 1, 1);
        checkEdge(edge2, 1, 2);
        assertThat(Edge.serials.get(), is(3L));
    }

    @Test
    public void testEqualsEdge () {
        assertThat(edge0, equalTo(edge0));
        assertThat(edge0, not(equalTo(edge1)));
        assertThat(edge0, not(equalTo(edge2)));
        assertThat(edge1, not(equalTo(edge0)));
        assertThat(edge1, equalTo(edge1));
        assertThat(edge1, not(equalTo(edge2)));
        assertThat(edge2, not(equalTo(edge0)));
        assertThat(edge2, not(equalTo(edge1)));
        assertThat(edge2, equalTo(edge2));
    }

    @Test
    public void testEqualsObject () {
        assertThat(edge0, equalTo((Object) edge0));
        assertThat(edge0, not(equalTo((Object) edge1)));
        assertThat(edge0, not(equalTo((Object) edge2)));
        assertThat(edge1, not(equalTo((Object) edge0)));
        assertThat(edge1, equalTo((Object) edge1));
        assertThat(edge1, not(equalTo((Object) edge2)));
        assertThat(edge2, not(equalTo((Object) edge0)));
        assertThat(edge2, not(equalTo((Object) edge1)));
        assertThat(edge2, equalTo((Object) edge2));
    }

    @Test
    public void testEqualsObjectUnrelated () {
        Object obj = new Object();
        assertThat(edge0, not(equalTo(null)));
        assertThat(edge1, not(equalTo(null)));
        assertThat(edge2, not(equalTo(null)));
        assertThat(edge0, not(equalTo(obj)));
        assertThat(edge1, not(equalTo(obj)));
        assertThat(edge2, not(equalTo(obj)));
    }

    @Test
    public void testHashCode () {
        assertThat(edge0.hashCode(), equalTo(edge0.hashCode()));
        assertThat(edge0.hashCode(), not(equalTo(edge1.hashCode())));
        assertThat(edge0.hashCode(), not(equalTo(edge2.hashCode())));
        assertThat(edge1.hashCode(), not(equalTo(edge0.hashCode())));
        assertThat(edge1.hashCode(), equalTo(edge1.hashCode()));
        assertThat(edge1.hashCode(), not(equalTo(edge2.hashCode())));
        assertThat(edge2.hashCode(), not(equalTo(edge0.hashCode())));
        assertThat(edge2.hashCode(), not(equalTo(edge1.hashCode())));
        assertThat(edge2.hashCode(), equalTo(edge2.hashCode()));
    }

    private void checkEdge (Edge edge, int id, long serial) {
        assertThat(edge.id, is(id));
        assertThat(edge.serial, is(serial));
    }
}
