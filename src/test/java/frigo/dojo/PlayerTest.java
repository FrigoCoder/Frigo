
package frigo.dojo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Dice dice;
    private Player player;

    @Test
    public void damageGetsSubtactedFromHealth () {
        int damageAmount = 10;
        player.damage(damageAmount);
        assertThat(player.getHealth(), is(Player.INITIAL_HEALTH - damageAmount));
    }

    @Test
    public void healthIsInitializedProperly () {
        assertThat(player.getHealth(), is(Player.INITIAL_HEALTH));
    }

    @Test
    public void playerIsDamagedDoubleWhenRollingTwelveAsDamage () {
        setupDice(6, 6);
        player.damage();
        assertThat(player.getHealth(), is(Player.INITIAL_HEALTH - 24));
    }

    @Test
    public void playerIsNotDamagedWhenRollingTwoAsDamage () {
        setupDice(1, 1);
        player.damage();
        assertThat(player.getHealth(), is(Player.INITIAL_HEALTH));
    }

    @Test
    public void playerRollsForDamage () {
        player.damage();
        verify(dice, times(2)).roll();
    }

    @Test
    public void rolledDamageGetsSubtractedFromHealth () {
        setupDice(3, 6);
        player.damage();
        assertThat(player.getHealth(), is(Player.INITIAL_HEALTH - 3 - 6));
    }

    @Before
    public void setUp () {
        setupPlayer();
        setupDice(3, 6);
    }

    private void setupDice (int firstRoll, int secondRoll) {
        dice = mock(Dice.class);
        doReturn(firstRoll).doReturn(secondRoll).when(dice).roll();
        player.dice = dice;
    }

    private void setupPlayer () {
        player = new Player();
    }
}
