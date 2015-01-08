
package frigo.math.numbertheory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

}
