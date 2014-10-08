
package frigo.dojo.bowling;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BowlingTest {

    private Bowling bowling = new Bowling();

    @Test
    public void total_fluke () {
        rollMany(20, 0);
        assertScore(0);
    }

    @Test
    public void all_one_rolls () {
        rollMany(20, 1);
        assertScore(20);
    }

    @Test
    public void all_two_rolls () {
        rollMany(20, 2);
        assertScore(40);
    }

    @Test
    public void roll_after_spare_is_counted_twice () {
        roll(0);
        roll(10);
        roll(1);
        assertScore(12);
    }

    @Test
    public void second_roll_after_spare_is_not_counted_twice () {
        roll(0);
        roll(10);
        roll(1);
        roll(1);
        assertScore(13);
    }

    @Test
    public void roll_after_strike_is_counted_twice () {
        roll(10);
        roll(1);
        assertScore(12);
    }

    @Test
    public void second_roll_after_strike_is_counted_twice () {
        roll(10);
        roll(1);
        roll(1);
        assertScore(14);
    }

    @Test
    public void perfect_game () {
        rollMany(12, 10);
        assertScore(300);
    }

    @Test
    public void eleven_strikes_and_zero () {
        rollMany(11, 10);
        roll(0);
        assertScore(290);
    }

    @Test
    public void ten_strikes_and_two_zeros () {
        rollMany(10, 10);
        roll(0);
        roll(0);
        assertScore(270);
    }

    @Test
    public void strike_and_zero_is_not_mistaken_for_spare () {
        roll(10);
        roll(0);
        roll(1);
        roll(1);
        roll(0);
        assertScore(13);
    }

    private void rollMany (int times, int score) {
        for( int i = 0; i < times; i++ ){
            roll(score);
        }
    }

    private void roll (int roll) {
        bowling.roll(roll);
    }

    private void assertScore (int score) {
        assertThat(bowling.score(), is(score));
    }

}
