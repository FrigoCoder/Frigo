
package frigo.dojo;

import static frigo.dojo.Dice.NUMBER_OF_VALUES;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class DiceTest {

    private static final int NUMBER_OF_TEST_ROLLS = 1000;

    private Dice dice = new Dice();

    @Test
    public void dice_rolls_all_values () {
        int[] rolls = new int[NUMBER_OF_VALUES];
        for( int i = 0; i < NUMBER_OF_TEST_ROLLS; i++ ){
            int roll = dice.roll();
            rolls[roll - 1]++;
        }
        for( int i = 0; i < NUMBER_OF_VALUES; i++ ){
            assertThat("" + (i + 1) + " was not rolled at least once", rolls[i], greaterThan(0));
        }
    }

    @Test
    public void dice_rolls_between_1_and_6 () {
        for( int i = 0; i < NUMBER_OF_TEST_ROLLS; i++ ){
            int roll = dice.roll();
            assertThat(roll, greaterThanOrEqualTo(1));
            assertThat(roll, lessThanOrEqualTo(6));
        }
    }

}
