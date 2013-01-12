
package frigo.util;

import static frigo.math.Complex.complex;
import static frigo.math.MathAux.sqr;
import static frigo.util.ArraysAux.asArrayList;
import static frigo.util.ArraysAux.asVector;
import static frigo.util.ArraysAux.getRandomDoubleArray;
import static frigo.util.ArraysAux.squaredEuclideanDistance;
import static frigo.util.ArraysAux.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import org.mockito.stubbing.Stubber;
import frigo.math.Complex;

public class ArraysAuxTest {

    @Test(expected = IllegalArgumentException.class)
    public void arraysOfDifferentDimensionsDoNotHaveComplexSquaredEuclideanDistance () {
        Complex[] v = {complex(1.0, 2.0)};
        Complex[] w = {complex(2.0, 3.0), complex(3.0, 4.0)};
        squaredEuclideanDistance(v, w);
    }

    @Test(expected = IllegalArgumentException.class)
    public void arraysOfDifferentDimensionsDoNotHaveDoubleSquaredEuclideanDistance () {
        double[] v = {1};
        double[] w = {1, 2};
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
    public void testAsArrayList () {
        List<Integer> actual = asArrayList(1, 2);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        assertThat(actual, is(expected));
    }

    @Test
    public void testAsVector () {
        List<Integer> actual = asVector(1, 2);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        assertThat(actual, is(expected));
    }

    @Test
    public void testGetRandomRealArray () {
        double[] expected = {1, 2, 3, 4, 5, 6};
        Random random = mock(Random.class);
        doReturnInOrder(expected).when(random).nextDouble();
        double[] actual = getRandomDoubleArray(expected.length, random);
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

    private Stubber doReturnInOrder (double[] v) {
        Stubber stubber = doReturn(v[0]);
        for( int i = 1; i < v.length; i++ ){
            stubber = stubber.doReturn(v[i]);
        }
        return stubber;
    }
}
