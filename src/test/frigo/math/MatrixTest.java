
package frigo.math;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class MatrixTest {

    int m;
    Matrix matrix;
    int n;
    int x;
    int y;

    @Test
    public void getAfterConstructor () {
        assertThat(matrix.get(x, y), is(0.0));
    }

    @Test
    public void getAfterSet () {
        matrix.set(x, y, 1.0);
        assertThat(matrix.get(x, y), is(1.0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getLargeI () {
        matrix.get(n, y);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getLargeJ () {
        matrix.get(x, m);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getSmallI () {
        matrix.get(-1, y);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getSmallJ () {
        matrix.get(x, -1);
    }

    @Test
    public void getVectorReturnsColumnVector () {
        matrix.set(x, y, 1.0);
        Vector vector = matrix.getVector(y);
        assertThat(vector.get(x), is(1.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mulIncorrectSize () {
        matrix.mul(new Vector(n - 1));
    }

    @Test
    public void mulLeftOnesMatrixByVector () {
        for( int i = 0; i < matrix.n(); i++ ){
            matrix.set(i, 0, 1.0);
        }
        Vector vector = getLinearVector();
        Vector result = matrix.mul(vector);
        for( int i = 0; i < result.size(); i++ ){
            assertThat(result.get(i), is(vector.get(0)));
        }
    }

    @Test
    public void mulTopOnesMatrixByVector () {
        for( int j = 0; j < matrix.m(); j++ ){
            matrix.set(0, j, 1.0);
        }
        Vector vector = getLinearVector();
        Vector result = matrix.mul(vector);
        assertThat(result.get(0), is(getSum(vector)));
        for( int i = 1; i < result.size(); i++ ){
            assertThat(result.get(i), is(0.0));
        }
    }

    @Test
    public void newMatrixIntIntHasCorrectDimensions () {
        assertThat(matrix.n(), is(n));
        assertThat(matrix.m(), is(m));
    }

    @Test
    public void newMatrixIntIntIsFullOfZeros () {
        for( int i = 0; i < matrix.n(); i++ ){
            for( int j = 0; j < matrix.m(); j++ ){
                assertThat(matrix.get(i, j), is(0.0));
            }
        }
    }

    @Test
    public void newMatrixIsOneByOne () {
        matrix = new Matrix();
        assertThat(matrix.n(), is(1));
        assertThat(matrix.m(), is(1));
    }

    @Before
    public void setUp () {
        n = 10;
        m = 13;
        x = 3;
        y = 5;
        matrix = new Matrix(n, m);
    }

    Vector getLinearVector () {
        Vector vector = new Vector(m);
        for( int i = 0; i < vector.size(); i++ ){
            vector.set(i, i);
        }
        return vector;
    }

    private double getSum (Vector vector) {
        double expected = 0.0;
        for( int i = 0; i < vector.size(); i++ ){
            expected += vector.get(i);
        }
        return expected;
    }
}
