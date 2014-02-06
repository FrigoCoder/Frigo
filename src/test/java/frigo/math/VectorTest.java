
package frigo.math;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class VectorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void vector_has_correct_size() {
        int n = 5;
        Vector vector = Vector.create(n);
        assertThat(vector.n, is(n));
    }

    @Test
    public void new_Vector_from_array_has_same_contents() {
        double[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Vector vector = Vector.from(array);
        assertThat(vector.n, is(array.length));
        for (int i = 0; i < array.length; i++) {
            assertThat("At index " + i, vector.get(i), is(array[i]));
        }
    }

    @Test
    public void new_Vector_from_array_is_clone() {
        double[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Vector vector = Vector.from(array);
        array[0] = 0.0;
        assertThat(vector.get(0), is(1.0));
    }

    @Test
    public void multiplication_by_scalar_works_correctly() {
        Vector vector = Vector.from(1, 2, 3, 4, 5);
        assertThat(vector.mul(2), is(Vector.from(2, 4, 6, 8, 10)));
    }

    @Test
    public void division_by_scalar_works_correctly() {
        Vector vector = Vector.from(2, 4, 6, 8, 10);
        assertThat(vector.div(2), is(Vector.from(1, 2, 3, 4, 5)));
    }

    @Test
    public void addition_of_vectors_works_correctly() {
        Vector augend = Vector.from(1, 2, 3, 4, 5);
        Vector addend = Vector.from(2, 4, 6, 8, 10);
        assertThat(augend.add(addend), is(Vector.from(3, 6, 9, 12, 15)));
    }

    @Test
    public void subtraction_of_vectors_works_correctly() {
        Vector minuend = Vector.from(2, 4, 6, 8, 10);
        Vector subtrahend = Vector.from(1, 2, 3, 4, 5);
        assertThat(minuend.sub(subtrahend), is(Vector.from(1, 2, 3, 4, 5)));
    }

    @Test
    public void dot_product_of_vectors_works_correctly() {
        Vector vector1 = Vector.from(2, 4, 6, 8, 10);
        Vector vector2 = Vector.from(1, 2, 3, 4, 5);
        assertThat(vector1.dot(vector2), is(110.0));
    }

    @Test
    public void norm_of_vector_works_correctly() {
        Vector vector = Vector.from(1, 2, 3, 4, 5);
        assertThat(vector.norm(), is(7.4161984870956629487113974408007));
    }

    @Test
    public void normalized_vector_is_returned() {
        Vector vector = Vector.from(3, 4);
        assertThat(vector.normalize(), is(Vector.from(0.6, 0.8)));
    }

    @Test
    public void normalized_zero_vector_is_zero_vector() {
        Vector zero = Vector.from(0, 0);
        assertThat(zero.normalize(), is(zero));
    }
}
