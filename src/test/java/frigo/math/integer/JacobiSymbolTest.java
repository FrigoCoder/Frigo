
package frigo.math.integer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.math.integer.JacobiSymbol;

public class JacobiSymbolTest {

    @Test
    public void jacobi_examples_from_wikipedia_table () {
        assertJacobi(0, 1, 0);
        assertJacobi(0, 3, 0);
        assertJacobi(0, 5, 0);
        assertJacobi(0, 7, 0);
        assertJacobi(0, 9, 0);
        assertJacobi(0, 11, 0);

        assertJacobi(1, 3, 1);
        assertJacobi(1, 5, 1);
        assertJacobi(1, 7, 1);
        assertJacobi(1, 9, 1);
        assertJacobi(1, 11, 1);

        assertJacobi(2, 3, -1);
        assertJacobi(2, 5, -1);
        assertJacobi(2, 7, 1);
        assertJacobi(2, 9, 1);
        assertJacobi(2, 11, -1);

        assertJacobi(3, 5, -1);
        assertJacobi(3, 7, -1);
        assertJacobi(3, 9, 0);
        assertJacobi(3, 11, 1);

        assertJacobi(4, 5, 1);
        assertJacobi(4, 7, 1);
        assertJacobi(4, 9, 1);
        assertJacobi(4, 11, 1);

        assertJacobi(5, 7, -1);
        assertJacobi(5, 9, 1);
        assertJacobi(5, 11, 1);

        assertJacobi(6, 7, -1);
        assertJacobi(6, 9, 0);
        assertJacobi(6, 11, -1);

        assertJacobi(7, 9, 1);
        assertJacobi(7, 11, -1);

        assertJacobi(9, 11, 1);

        assertJacobi(10, 11, -1);
    }

    @Test
    public void jacobi_examples_from_wikipedia_article () {
        assertJacobi(1001, 9907, -1);
        assertJacobi(19, 45, 1);
        assertJacobi(8, 21, -1);
        assertJacobi(5, 21, 1);
    }

    @Test
    public void jacobi_reversed_examples_from_wikipedia () {
        assertJacobi(3, 1, 0);
        assertJacobi(5, 1, 0);
        assertJacobi(7, 1, 0);
        assertJacobi(9, 1, 0);
        assertJacobi(11, 1, 0);

        assertJacobi(5, 3, -1);
        assertJacobi(7, 3, 1);
        assertJacobi(9, 3, 0);
        assertJacobi(11, 3, -1);

        assertJacobi(7, 5, -1);
        assertJacobi(9, 5, 1);
        assertJacobi(11, 5, 1);

        assertJacobi(9, 7, 1);
        assertJacobi(11, 7, 1);

        assertJacobi(11, 9, 1);

        assertJacobi(9907, 1001, -1);
        assertJacobi(45, 19, 1);
        assertJacobi(21, 5, 1);
    }

    @Test
    public void jacobi_for_non_coprime_integers () {
        assertJacobi(66, 187, 0);
        assertJacobi(68, 187, 0);
    }

    private void assertJacobi (long m, long n, long j) {
        assertThat("Jacobi Symbol (" + m + "/" + n + ") should be " + j, JacobiSymbol.jacobi(m, n), is(j));
    }

}
