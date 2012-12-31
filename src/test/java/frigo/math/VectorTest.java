
package frigo.math;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class VectorTest {

    private double[] array;
    private Vector expected;
    private int n;
    private Vector other;
    private Vector vector;

    @Test(expected = IllegalArgumentException.class)
    public void addDifferentSize () {
        other = new Vector(n - 1);
        vector.add(other);
    }

    @Test
    public void addLinearAndLinear () {
        fillLinear(vector, 1.0);
        fillLinear(expected, 2.0);
        assertThat(vector.add(vector), is(expected));
    }

    @Test
    public void addLinearAndTwiceLinear () {
        fillLinear(vector, 1.0);
        fillLinear(other, 2.0);
        fillLinear(expected, 3.0);
        assertThat(vector.add(other), is(expected));
    }

    @Test
    public void divLinearByTwo () {
        fillLinear(vector, 1.0);
        fillLinear(other, 0.5);
        assertThat(vector.div(2.0), is(other));
    }

    @Test
    public void divLinearByTwoThirds () {
        fillLinear(vector, 2.0);
        fillLinear(other, 3.0);
        assertThat(vector.div(2.0 / 3.0), is(other));
    }

    @Test(expected = IllegalArgumentException.class)
    public void dotDifferentSize () {
        other = new Vector(n - 1);
        vector.dot(other);
    }

    @Test
    public void dotLinearAndThreeLinear () {
        fillLinear(vector, 1.0);
        fillLinear(other, 3.0);
        double dotProduct = 0.0;
        for( int i = 0; i < vector.size(); i++ ){
            dotProduct += vector.get(i) * other.get(i);
        }
        assertThat(vector.dot(other), is(dotProduct));
    }

    @Test
    public void equalsDifferentSize () {
        vector = new Vector(n);
        other = new Vector(n - 1);
        assertThat(vector.equals(other), is(false));
    }

    @Test
    public void equalsLinearAndLinear () {
        fillLinear(vector, 1.0);
        fillLinear(expected, 1.0);
        assertThat(vector, is(expected));
    }

    @Test
    public void equalsLinearAndObject () {
        fillLinear(vector, 1.0);
        assertThat(vector, not(new Object()));
    }

    @Test
    public void equalsLinearAndZero () {
        fillLinear(vector, 1.0);
        fillLinear(expected, 0.0);
        assertThat(vector, not(expected));
    }

    @Test
    public void getLinearCoefficient () {
        fillLinear(vector, 1.0);
        for( int i = 0; i < vector.size(); i++ ){
            assertThat(vector.get(i), is((double) i));
        }
    }

    @Test
    public void getZeroCoefficient () {
        fillLinear(vector, 0.0);
        for( int i = 0; i < vector.size(); i++ ){
            assertThat(vector.get(i), is(0.0));
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void hashCodeThrows () {
        fillLinear(vector, 1.0);
        vector.hashCode();
    }

    @Test
    public void mulLinearByTwo () {
        fillLinear(vector, 1.0);
        fillLinear(expected, 2.0);
        assertThat(vector.mul(2.0), is(expected));
    }

    @Test
    public void mulTwoLinearByOneHalf () {
        fillLinear(vector, 2.0);
        fillLinear(expected, 3.0);
        assertThat(vector.mul(1.5), is(expected));
    }

    @Test
    public void newVectorFromArrayHasSameContentsAsArray () {
        vector = new Vector(array);
        assertThat(vector.size(), is(array.length));
        for( int i = 0; i < array.length; i++ ){
            assertThat("At index " + i, vector.get(i), is(array[i]));
        }
    }

    @Test
    public void newVectorFromArrayIsClone () {
        vector = new Vector(array);
        array[0] = 0.0;
        assertThat(vector.get(0), not(array[0]));
    }

    @Test
    public void newVectorFromIntHasCorrectSize () {
        assertThat(vector.size(), is(n));
    }

    @Test
    public void newVectorHasSizeOne () {
        vector = new Vector();
        assertThat(vector.size(), is(1));
    }

    @Test
    public void setLinear () {
        fillLinear(vector, 1.0);
        for( int i = 0; i < vector.size(); i++ ){
            assertThat(vector.get(i), is((double) i));
        }
    }

    @Before
    public void setUp () {
        n = 1024;
        array = new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        expected = new Vector(n);
        other = new Vector(n);
        vector = new Vector(n);
    }

    @Test(expected = IllegalArgumentException.class)
    public void subDifferentSize () {
        other = new Vector(n - 1);
        vector.sub(other);
    }

    @Test
    public void subFromLinearLinear () {
        fillLinear(vector, 1.0);
        fillLinear(expected, 0.0);
        assertThat(vector.sub(vector), is(expected));
    }

    @Test
    public void subFromLinearTwoLinear () {
        fillLinear(vector, 1.0);
        fillLinear(other, 2.0);
        fillLinear(expected, -1.0);
        assertThat(vector.sub(other), is(expected));
    }

    @Test
    public void toStringSameAsArraysToString () {
        vector = new Vector(array);
        assertThat(vector.toString(), is(Arrays.toString(array)));
    }

    private void fillLinear (Vector v, double factor) {
        for( int i = 0; i < v.size(); i++ ){
            v.set(i, factor * i);
        }
    }
}
