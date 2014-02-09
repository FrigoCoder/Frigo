
package frigo.math;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MatrixTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private int n = 2;
    private int m = 3;
    private Matrix matrix = new Matrix(n, m);

    @Test
    public void dimensions_are_correct () {
        assertThat(matrix.n, is(n));
        assertThat(matrix.m, is(m));
    }

    @Test
    public void coefficients_are_zero_by_default () {
        for( int i = 0; i < n; i++ ){
            for( int j = 0; j < m; j++ ){
                assertThat(matrix.get(i, j), is(0.0));
            }
        }
    }

    @Test
    public void sets_value_properly () {
        int i = 1;
        int j = 2;
        matrix.set(i, j, 1);
        assertThat(matrix.get(i, j), is(1.0));
    }

    @Test
    public void column_vector_is_returned () {
        int i = 1;
        int j = 2;
        matrix.set(i, j, 1);
        assertThat(matrix.vector(j).get(i), is(1.0));
    }

    @Test
    public void setRow_works_properly () {
        matrix.setRow(0, 1, 2, 3);
        matrix.setRow(1, 4, 5, 6);
        assertThat(matrix.vector(0), is(Vector.from(1, 4)));
        assertThat(matrix.vector(1), is(Vector.from(2, 5)));
        assertThat(matrix.vector(2), is(Vector.from(3, 6)));
    }

    @Test
    public void multiplication_works_properly () {
        matrix.setRow(0, 1, 2, 3);
        matrix.setRow(1, 4, 5, 6);
        assertThat(matrix.mul(Vector.from(1, 2, 3)), is(Vector.from(14, 32)));
    }

    @Test
    public void identity_matrix_is_correct () {
        matrix = Matrix.identity(5);
        for( int i = 0; i < matrix.n; i++ ){
            for( int j = 0; j < matrix.m; j++ ){
                if( i == j ){
                    assertThat(matrix.get(i, j), is(1.0));
                }else{
                    assertThat(matrix.get(i, j), is(0.0));
                }
            }
        }
    }

}
