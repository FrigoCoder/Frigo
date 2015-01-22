
package frigo.math.numbertheory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.primitives.Longs;

public class QuadraticResidueOfTwoPowerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void set_of_quadratic_residues_is_correct () {
        assertResidues(1, 0);
        assertResidues(2, 0, 1);
        assertResidues(4, 0, 1);
        assertResidues(8, 0, 1, 4);
        assertResidues(16, 0, 1, 4, 9);
        assertResidues(32, 0, 1, 4, 9, 16, 17, 25);
        assertResidues(64, 0, 1, 4, 9, 16, 17, 25, 33, 36, 41, 49, 57);
        assertResidues(128, 0, 1, 4, 9, 16, 17, 25, 33, 36, 41, 49, 57, 64, 65, 68, 73, 81, 89, 97, 100, 105, 113, 121);
        assertResidues(256, 0, 1, 4, 9, 16, 17, 25, 33, 36, 41, 49, 57, 64, 65, 68, 73, 81, 89, 97, 100, 105, 113, 121,
            129, 132, 137, 144, 145, 153, 161, 164, 169, 177, 185, 193, 196, 201, 209, 217, 225, 228, 233, 241, 249);
    }

    @Test
    public void not_power_of_two () {
        thrown.expect(IllegalArgumentException.class);
        assertResidues(127, 0);
    }

    private void assertResidues (long p, long... residues) {
        assertThat(getResidues(p), is(getExpected(residues)));
    }

    private Set<Long> getResidues (long p) {
        Set<Long> result = new TreeSet<>();
        for( long x = 0; x < p; x++ ){
            if( QuadraticResidueOfTwoPower.isQuadraticResidue(x, p) ){
                result.add(x);
            }
        }
        return result;
    }

    private Set<Long> getExpected (long... residues) {
        Set<Long> expected = new TreeSet<>();
        for( long residue : residues ){
            expected.add(residue);
        }
        return expected;
    }

    @Test
    public void test_sqrt () {
        assertSqrt(2, 0, 0);
        assertSqrt(2, 1, 1);

        assertSqrt(4, 0, 0, 2);
        assertSqrt(4, 1, 1, 3);
        assertSqrt(4, 2);
        assertSqrt(4, 3);

        assertSqrt(8, 0, 0, 4);
        assertSqrt(8, 1, 1, 3, 5, 7);
        assertSqrt(8, 4, 2, 6);
        assertSqrt(8, 2);
        assertSqrt(8, 3);
        assertSqrt(8, 5);
        assertSqrt(8, 6);
        assertSqrt(8, 7);

        assertSqrt(16, 0, 0, 4, 8, 12);
        assertSqrt(16, 1, 1, 7, 9, 15);
        assertSqrt(16, 4, 2, 6, 10, 14);
        assertSqrt(16, 9, 3, 5, 11, 13);
        assertSqrt(16, 2);
        assertSqrt(16, 3);
        assertSqrt(16, 5);
        assertSqrt(16, 6);
        assertSqrt(16, 7);
        assertSqrt(16, 8);
        assertSqrt(16, 10);
        assertSqrt(16, 11);
        assertSqrt(16, 12);
        assertSqrt(16, 13);
        assertSqrt(16, 14);
        assertSqrt(16, 15);
    }

    private void assertSqrt (long n, long x2, long... x) {
        assertThat(QuadraticResidueOfTwoPower.sqrt(x2, n), is(Longs.asList(x)));
    }

    @Test
    public void test_sqrt_negative () {
        thrown.expect(IllegalArgumentException.class);
        QuadraticResidueOfTwoPower.sqrt(-1, 16);
    }

    @Test
    public void test_sqrt_large () {
        thrown.expect(IllegalArgumentException.class);
        QuadraticResidueOfTwoPower.sqrt(16, 16);
    }

    @Test
    public void test_sqrt_non_power_of_two_modulus () {
        thrown.expect(IllegalArgumentException.class);
        QuadraticResidueOfTwoPower.sqrt(16, 17);
    }

}
