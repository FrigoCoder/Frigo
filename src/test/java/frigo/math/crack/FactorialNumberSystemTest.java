
package frigo.math.crack;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FactorialNumberSystemTest {

    @Test
    public void test_place_values () {
        assertPlaceValues(0, 1);
        assertPlaceValues(1, 1);
        assertPlaceValues(2, 1, 2);
        assertPlaceValues(5, 1, 2);
        assertPlaceValues(6, 1, 2, 6);
        assertPlaceValues(24, 1, 2, 6, 24);
    }

    private void assertPlaceValues (int highest, long... placeValues) {
        FactorialNumberSystem system = new FactorialNumberSystem(highest);
        for( int i = 0; i < placeValues.length; i++ ){
            assertThat(system.placeValue(i), is(placeValues[i]));
        }
    }

    @Test
    public void test_highest_digits () {
        assertHighestDigits(0, 1);
        assertHighestDigits(1, 1);
        assertHighestDigits(2, 1, 2);
        assertHighestDigits(5, 1, 2);
        assertHighestDigits(6, 1, 2, 3);
        assertHighestDigits(24, 1, 2, 3, 4);
    }

    private void assertHighestDigits (int highest, long... highestDigits) {
        FactorialNumberSystem system = new FactorialNumberSystem(highest);
        for( int i = 0; i < highestDigits.length; i++ ){
            assertThat(system.highestDigit(i), is(highestDigits[i]));
        }
    }

}
