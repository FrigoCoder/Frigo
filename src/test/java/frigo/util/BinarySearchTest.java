
package frigo.util;

import static frigo.util.BinarySearch.findExact;
import static frigo.util.BinarySearch.findHigher;
import static frigo.util.BinarySearch.findLower;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BinarySearchTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private double[] array = {0, 1, 4, 9, 16};

    @Test
    public void findExact_finds_values () {
        assertThat(findExact(array, 0), is(0));
        assertThat(findExact(array, 1), is(1));
        assertThat(findExact(array, 4), is(2));
        assertThat(findExact(array, 9), is(3));
        assertThat(findExact(array, 16), is(4));
    }

    @Test
    public void findExact_throws_exception_on_unknown_element_at_left () {
        thrown.expect(NoSuchElementException.class);
        findExact(array, -1);
    }

    @Test
    public void findExact_throws_exception_on_unknown_element_at_middle () {
        thrown.expect(NoSuchElementException.class);
        findExact(array, 3);
    }

    @Test
    public void findExact_throws_exception_on_unknown_element_at_right () {
        thrown.expect(NoSuchElementException.class);
        findExact(array, 17);
    }

    @Test
    public void findLower_finds_values () {
        assertThat(findLower(array, 0.0), is(0));
        assertThat(findLower(array, 0.5), is(0));
        assertThat(findLower(array, 4.0), is(2));
        assertThat(findLower(array, 4.5), is(2));
        assertThat(findLower(array, 16.0), is(4));
        assertThat(findLower(array, 16.5), is(4));
    }

    @Test
    public void findLower_throws_exception_on_too_low_value () {
        thrown.expect(NoSuchElementException.class);
        findLower(array, -1);
    }

    @Test
    public void findHigher_finds_values () {
        assertThat(findHigher(array, -1.0), is(0));
        assertThat(findHigher(array, 0.0), is(0));
        assertThat(findHigher(array, 0.5), is(1));
        assertThat(findHigher(array, 4.0), is(2));
        assertThat(findHigher(array, 4.5), is(3));
        assertThat(findHigher(array, 16.0), is(4));
    }

    @Test
    public void findHigher_throws_exception_on_too_high_value () {
        thrown.expect(NoSuchElementException.class);
        findHigher(array, 16.5);
    }

}
