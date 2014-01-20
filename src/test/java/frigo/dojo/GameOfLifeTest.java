
package frigo.dojo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GameOfLifeTest {

    private GameOfLife game = new GameOfLife();

    @Test
    public void board_is_empty_at_beginning () {
        for( int x = 0; x <= 3; x++ ){
            for( int y = 0; y <= 3; y++ ){
                assertThat(game.isAlive(x, y), is(false));
            }
        }
    }

    @Test
    public void cell_can_be_set_alive () {
        game.setAlive(1, 1);
        assertThat(game.isAlive(1, 1), is(true));
    }

    @Test
    public void cell_can_be_set_dead () {
        game.setAlive(1, 1);
        game.setDead(1, 1);
        assertThat(game.isAlive(1, 1), is(false));
    }

    @Test
    public void alive_cell_dies_with_0_neighbors () {
        game.setAlive(1, 1);
        game = game.step();
        assertThat(game.isAlive(1, 1), is(false));
    }

    @Test
    public void alive_cell_dies_with_1_neighbors () {
        game.setAlive(1, 1);
        game.setAlive(2, 2);
        game = game.step();
        assertThat(game.isAlive(1, 1), is(false));
    }

    @Test
    public void alive_cell_survives_with_2_neighbors () {
        game.setAlive(1, 1);
        game.setAlive(0, 1);
        game.setAlive(1, 2);
        game = game.step();
        assertThat(game.isAlive(1, 1), is(true));
    }

    @Test
    public void alive_cell_survives_with_3_neighbors () {
        game.setAlive(1, 1);
        game.setAlive(0, 1);
        game.setAlive(1, 2);
        game.setAlive(2, 2);
        game = game.step();
        assertThat(game.isAlive(1, 1), is(true));
    }

    @Test
    public void alive_cell_dies_with_4_neighbors () {
        game.setAlive(1, 1);
        game.setAlive(0, 1);
        game.setAlive(1, 2);
        game.setAlive(2, 2);
        game.setAlive(2, 1);
        game = game.step();
        assertThat(game.isAlive(1, 1), is(false));
    }

    @Test
    public void alive_cell_dies_with_8_neighbors () {
        for( int x = 0; x < 3; x++ ){
            for( int y = 0; y < 3; y++ ){
                game.setAlive(x, y);
            }
        }
        game = game.step();
        assertThat(game.isAlive(1, 1), is(false));
    }

    @Test
    public void dead_cell_remains_dead_with_2_neighbors () {
        game.setAlive(0, 1);
        game.setAlive(1, 2);
        game = game.step();
        assertThat(game.isAlive(1, 1), is(false));
    }

    @Test
    public void dead_cell_comes_to_life_with_3_neighbors () {
        game.setAlive(0, 1);
        game.setAlive(1, 2);
        game.setAlive(2, 2);
        game = game.step();
        assertThat(game.isAlive(1, 1), is(true));
    }

    @Test
    public void dead_cell_comes_to_life_with_4_neighbors () {
        game.setAlive(0, 1);
        game.setAlive(1, 2);
        game.setAlive(2, 2);
        game.setAlive(2, 1);
        game = game.step();
        assertThat(game.isAlive(1, 1), is(false));
    }

}
