
package frigo.dojo;

import static frigo.dojo.Player.INITIAL_HEALTH;
import static frigo.util.MockitoAux.doReturnValues;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Player player = new Player();
    private Dice dice = mock(Dice.class);

    @Before
    public void setUp () {
        player.dice = dice;
    }

    @Test
    public void health_is_initialized_properly () {
        assertThat(player.getHealth(), is(INITIAL_HEALTH));
    }

    @Test
    public void player_rolls_twice_for_damage () {
        player.damage();
        verify(dice, times(2)).roll();
    }

    @Test
    public void rolled_damage_gets_subtracted_from_health () {
        doReturnValues(3, 6).when(dice).roll();
        player.damage();
        assertThat(player.getHealth(), is(INITIAL_HEALTH - 3 - 6));
    }

    @Test
    public void player_is_damaged_double_when_rolling_12_as_damage () {
        doReturnValues(6, 6).when(dice).roll();
        player.damage();
        assertThat(player.getHealth(), is(INITIAL_HEALTH - 24));
    }

    @Test
    public void player_is_not_damaged_when_rolling_2_as_damage () {
        doReturnValues(1, 1).when(dice).roll();
        player.damage();
        assertThat(player.getHealth(), is(INITIAL_HEALTH));
    }

}
