
package frigo.dojo.gameoflife;

import java.util.HashSet;
import java.util.Set;

public class GameOfLife {

    private Set<Position> cells = new HashSet<>();

    public boolean isAlive (int x, int y) {
        return cells.contains(new Position(x, y));
    }

    public void setAlive (int x, int y) {
        if( isAlive(x, y) ){
            return;
        }
        cells.add(new Position(x, y));
    }

    public void setDead (int x, int y) {
        cells.remove(new Position(x, y));
    }

    public GameOfLife step () {
        GameOfLife game = new GameOfLife();
        for( Position cell : getPotentialLiveCells() ){
            if( livesAtNext(cell.x, cell.y) ){
                game.setAlive(cell.x, cell.y);
            }
        }
        return game;
    }

    private HashSet<Position> getPotentialLiveCells () {
        HashSet<Position> potentialNewCells = new HashSet<>();
        for( Position cell : cells ){
            for( int x = cell.x - 1; x <= cell.x + 1; x++ ){
                for( int y = cell.y - 1; y <= cell.y + 1; y++ ){
                    potentialNewCells.add(new Position(x, y));
                }
            }
        }
        return potentialNewCells;
    }

    private boolean livesAtNext (int x, int y) {
        boolean alive = isAlive(x, y);
        int count = countNeighbors(x, y);
        if( alive && 2 <= count && count <= 3 ){
            return true;
        }
        if( !alive && 3 == count ){
            return true;
        }
        return false;
    }

    private int countNeighbors (int x0, int y0) {
        int count = 0;
        for( int x = x0 - 1; x <= x0 + 1; x++ ){
            for( int y = y0 - 1; y <= y0 + 1; y++ ){
                if( isAlive(x, y) ){
                    count++;
                }
            }
        }
        if( isAlive(x0, y0) ){
            count--;
        }
        return count;
    }

}
