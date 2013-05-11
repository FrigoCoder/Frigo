
package frigo.graph;

import static com.google.common.collect.Sets.newHashSet;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DirectedGraphTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private DirectedGraph<String> graph = new DirectedGraph<>();

    @Test
    public void topological_sort_of_two_unrelated_nodes () {
        // given
        graph.addNodes("A", "B");
        // when
        List<Set<String>> topology = graph.topologicalSort();
        // then
        assertThat(topology.size(), is(1));
        assertThat(topology.get(0), is(asSet("A", "B")));
    }

    @Test
    public void topological_sort_of_one_node_depending_on_another () {
        // given
        graph.addNodes("A", "B");
        graph.addEdge("A", "B");
        // when
        List<Set<String>> topology = graph.topologicalSort();
        // then
        assertThat(topology.size(), is(2));
        assertThat(topology.get(0), is(asSet("A")));
        assertThat(topology.get(1), is(asSet("B")));
    }

    @Test
    public void topological_sort_of_a_tree () {
        // given
        graph.addNodes("A", "B", "C", "D", "E", "F");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("C", "D");
        graph.addEdge("C", "E");
        graph.addEdge("C", "F");
        // when
        List<Set<String>> topology = graph.topologicalSort();
        // then
        assertThat(topology.size(), is(3));
        assertThat(topology.get(0), is(asSet("A")));
        assertThat(topology.get(1), is(asSet("B", "C")));
        assertThat(topology.get(2), is(asSet("D", "E", "F")));
    }

    @Test
    public void topological_sort_throws_exception_on_cycle () {
        // given
        graph.addNodes("A", "B", "C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        // then
        thrown.expect(IllegalStateException.class);
        // when
        graph.topologicalSort();
    }

    @Test
    public void topological_sort_for_multi_level_dependencies () {
        // given
        graph.addNodes("A", "B", "C");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        // when
        List<Set<String>> topology = graph.topologicalSort();
        // then
        assertThat(topology.size(), is(3));
        assertThat(topology.get(0), is(asSet("A")));
        assertThat(topology.get(1), is(asSet("B")));
        assertThat(topology.get(2), is(asSet("C")));
    }

    private <T> Set<T> asSet (T... objects) {
        return newHashSet(objects);
    }

}
