
package frigo.util;

import static frigo.math.Complex.complex;
import static frigo.math.MathAux.sqr;
import static frigo.util.ArraysAux.bytes;
import static frigo.util.ArraysAux.squaredEuclideanDistance;
import static frigo.util.ArraysAux.toList;
import static frigo.util.ArraysAux.toObjectArray;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import frigo.math.Complex;

public class ArraysAuxTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void arraysOfDifferentDimensionsDoNotHaveComplexSquaredEuclideanDistance () {
        Complex[] v = {complex(1.0, 2.0)};
        Complex[] w = {complex(2.0, 3.0), complex(3.0, 4.0)};
        thrown.expect(IllegalArgumentException.class);
        squaredEuclideanDistance(v, w);
    }

    @Test
    public void arraysOfDifferentDimensionsDoNotHaveDoubleSquaredEuclideanDistance () {
        double[] v = {1};
        double[] w = {1, 2};
        thrown.expect(IllegalArgumentException.class);
        squaredEuclideanDistance(v, w);
    }

    @Test
    public void complexSquaredEuclideanDistance () {
        Complex[] v = {complex(1.0, 2.0), complex(2.0, 3.0), complex(3.0, 4.0), complex(4.0, 5.0)};
        Complex[] w = {complex(5.0, 6.0), complex(6.0, 7.0), complex(7.0, 8.0), complex(8.0, 9.0)};
        double actual = squaredEuclideanDistance(v, w);
        double expected =
            v[0].sub(w[0]).sqrAbs() + v[1].sub(w[1]).sqrAbs() + v[2].sub(w[2]).sqrAbs() + v[3].sub(w[3]).sqrAbs();
        assertThat(actual, is(expected));
    }

    @Test
    public void doubleSquaredEuclideanDistance () {
        double[] v = {1, 2, 3, 4};
        double[] w = {2, 4, 8, 16};
        double actual = squaredEuclideanDistance(v, w);
        double expected = sqr(v[0] - w[0]) + sqr(v[1] - w[1]) + sqr(v[2] - w[2]) + sqr(v[3] - w[3]);
        assertThat(actual, is(expected));
    }

    @Test
    public void testToListInt () {
        int[] array = {1, 2};
        List<Integer> actual = toList(array);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        assertThat(actual, is(expected));
    }

    @Test
    public void toObjectArray_for_object_array () {
        Integer[] array = {1, 2};
        assertThat(toObjectArray(array), sameInstance(array));
    }

    @Test
    public void toObjectArray_for_byte_array () {
        byte[] array = {1, 2};
        Byte[] expected = {1, 2};
        assertThat(toObjectArray(array), is(expected));
    }

    @Test
    public void toObjectArray_for_short_array () {
        short[] array = {1, 2};
        Short[] expected = {1, 2};
        assertThat(toObjectArray(array), is(expected));
    }

    @Test
    public void toObjectArray_for_int_array () {
        int[] array = {1, 2};
        Integer[] expected = {1, 2};
        assertThat(toObjectArray(array), is(expected));
    }

    @Test
    public void toObjectArray_for_long_array () {
        long[] array = {1, 2};
        Long[] expected = {1L, 2L};
        assertThat(toObjectArray(array), is(expected));
    }

    @Test
    public void toObjectArray_for_float_array () {
        float[] array = {1, 2};
        Float[] expected = {1F, 2F};
        assertThat(toObjectArray(array), is(expected));
    }

    @Test
    public void toObjectArray_for_double_array () {
        double[] array = {1, 2};
        Double[] expected = {1D, 2D};
        assertThat(toObjectArray(array), is(expected));
    }

    @Test
    public void toObjectArray_for_boolean_array () {
        boolean[] array = {true, false};
        Boolean[] expected = {true, false};
        assertThat(toObjectArray(array), is(expected));
    }

    @Test
    public void toObjectArray_for_char_array () {
        char[] array = {'a', 'b'};
        Character[] expected = {'a', 'b'};
        assertThat(toObjectArray(array), is(expected));
    }

    @Test
    public void bytes_works_for_positive_values () {
        int[] array = {0x12, 0x24, 0x48};
        byte[] expected = {0x12, 0x24, 0x48};
        assertThat(bytes(array), is(expected));
    }

    @Test
    public void bytes_works_for_negative_values () {
        int[] array = {0x80, 0x84, 0xff};
        byte[] expected = {(byte) 0x80, (byte) 0x84, (byte) 0xff};
        assertThat(bytes(array), is(expected));
    }

    @Test
    public void toHexString_works_correctly () {
        byte[] bytes = bytes(0x01, 0x23, 0x45, 0x67, 0x89, 0xab, 0xcd, 0xef);
        assertThat(ArraysAux.toHexString(bytes), is("0123456789ABCDEF"));
    }
}
