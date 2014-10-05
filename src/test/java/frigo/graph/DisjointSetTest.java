
package frigo.graph;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DisjointSetTest {

    private DisjointSet<String> set = new DisjointSet<>();

    @Test
    public void two_added_elements_belong_to_different_sets () {
        set.add("A", "B");
        assertThat(set.find("A"), not(set.find("B")));
    }

    @Test
    public void union_of_two_elements_results_in_same_set () {
        set.add("A", "B");
        set.union("A", "B");
        assertThat(set.find("A"), is(set.find("B")));
    }

    @Test
    public void union_of_three_elements_results_in_same_set () {
        set.add("A", "B", "C");
        set.union("A", "B");
        set.union("B", "C");
        assertThat(set.find("B"), is(set.find("A")));
        assertThat(set.find("C"), is(set.find("A")));
    }

    @Test
    public void union_attaches_smaller_tree_to_the_root_of_the_larger_tree () {
        set.add("A", "B", "C");
        set.union("A", "B");
        set.union("B", "C");
        assertThat(set.find("A"), is("A"));
        assertThat(set.find("B"), is("A"));
        assertThat(set.find("C"), is("A"));
    }

    @Test
    public void union_attaches_smaller_tree_to_the_root_of_the_larger_tree_other_branch () {
        set.add("A", "B", "C");
        set.union("A", "B");
        set.union("C", "B");
        assertThat(set.find("A"), is("A"));
        assertThat(set.find("B"), is("A"));
        assertThat(set.find("C"), is("A"));
    }

    @Test
    public void union_of_elements_in_the_same_set () {
        set.add("A", "B", "C");
        set.union("A", "B");
        set.union("A", "C");
        set.union("B", "C");
        assertThat(set.find("A"), is("A"));
        assertThat(set.find("B"), is("A"));
        assertThat(set.find("C"), is("A"));
    }

}
