
package frigo.math;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class VectorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private int n = 1024;
    private Vector vector = new Vector(n);
    private Vector expected = new Vector(n);
    private Vector other = new Vector(n);
    private double[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Test
    public void new_Vector_has_size_one () {
        vector = new Vector();
        assertThat(vector.size(), is(1));
    }

    @Test
    public void new_Vector_from_int_has_correct_size () {
        assertThat(vector.size(), is(n));
    }

    @Test
    public void new_Vector_from_array_has_same_contents () {
        vector = new Vector(array);
        assertThat(vector.size(), is(array.length));
        for( int i = 0; i < array.length; i++ ){
            assertThat("At index " + i, vector.get(i), is(array[i]));
        }
    }

    @Test
    public void new_Vector_from_array_is_clone () {
        vector = new Vector(array);
        array[0] = 0.0;
        assertThat(vector.get(0), not(array[0]));
    }

    @Test
    public void add_different_size () {
        other = new Vector(n - 1);
        thrown.expect(IllegalArgumentException.class);
        vector.add(other);
    }

    @Test
    public void add_linear_and_linear () {
        fillLinear(vector, 1.0);
        fillLinear(expected, 2.0);
        assertThat(vector.add(vector), is(expected));
    }

    @Test
    public void add_linear_and_twice_linear () {
        fillLinear(vector, 1.0);
        fillLinear(other, 2.0);
        fillLinear(expected, 3.0);
        assertThat(vector.add(other), is(expected));
    }

    @Test
    public void div_linear_by_two () {
        fillLinear(vector, 1.0);
        fillLinear(other, 0.5);
        assertThat(vector.div(2.0), is(other));
    }

    @Test
    public void div_linear_by_two_thirds () {
        fillLinear(vector, 2.0);
        fillLinear(other, 3.0);
        assertThat(vector.div(2.0 / 3.0), is(other));
    }

    @Test
    public void dot_different_size () {
        other = new Vector(n - 1);
        thrown.expect(IllegalArgumentException.class);
        vector.dot(other);
    }

    @Test
    public void dot_linear_and_three_linear () {
        fillLinear(vector, 1.0);
        fillLinear(other, 3.0);
        double dotProduct = 0.0;
        for( int i = 0; i < vector.size(); i++ ){
            dotProduct += vector.get(i) * other.get(i);
        }
        assertThat(vector.dot(other), is(dotProduct));
    }

    @Test
    public void equals_different_size () {
        vector = new Vector(n);
        other = new Vector(n - 1);
        assertThat(vector.equals(other), is(false));
    }

    @Test
    public void equals_linear_and_linear () {
        fillLinear(vector, 1.0);
        fillLinear(expected, 1.0);
        assertThat(vector, is(expected));
    }

    @Test
    public void equals_linear_and_object () {
        fillLinear(vector, 1.0);
        assertThat(vector, not(new Object()));
    }

    @Test
    public void equals_linear_and_zero () {
        fillLinear(vector, 1.0);
        fillLinear(expected, 0.0);
        assertThat(vector, not(expected));
    }

    @Test
    public void get_linear_coefficient () {
        fillLinear(vector, 1.0);
        for( int i = 0; i < vector.size(); i++ ){
            assertThat(vector.get(i), is((double) i));
        }
    }

    @Test
    public void get_zero_coefficient () {
        fillLinear(vector, 0.0);
        for( int i = 0; i < vector.size(); i++ ){
            assertThat(vector.get(i), is(0.0));
        }
    }

    @Test
    public void mul_linear_by_two () {
        fillLinear(vector, 1.0);
        fillLinear(expected, 2.0);
        assertThat(vector.mul(2.0), is(expected));
    }

    @Test
    public void mul_two_linear_by_one_half () {
        fillLinear(vector, 2.0);
        fillLinear(expected, 3.0);
        assertThat(vector.mul(1.5), is(expected));
    }

    @Test
    public void set_linear () {
        fillLinear(vector, 1.0);
        for( int i = 0; i < vector.size(); i++ ){
            assertThat(vector.get(i), is((double) i));
        }
    }

    @Test
    public void sub_different_size () {
        other = new Vector(n - 1);
        thrown.expect(IllegalArgumentException.class);
        vector.sub(other);
    }

    @Test
    public void sub_from_linear_linear () {
        fillLinear(vector, 1.0);
        fillLinear(expected, 0.0);
        assertThat(vector.sub(vector), is(expected));
    }

    @Test
    public void sub_from_linear_two_linear () {
        fillLinear(vector, 1.0);
        fillLinear(other, 2.0);
        fillLinear(expected, -1.0);
        assertThat(vector.sub(other), is(expected));
    }

    @Test
    public void toString_same_as_Arrays_toString () {
        vector = new Vector(array);
        assertThat(vector.toString(), is(Arrays.toString(array)));
    }

    private void fillLinear (Vector v, double factor) {
        for( int i = 0; i < v.size(); i++ ){
            v.set(i, factor * i);
        }
    }
}
