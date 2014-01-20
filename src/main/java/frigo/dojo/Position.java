
package frigo.dojo;

public class Position {

    public int x;
    public int y;

    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode () {
        return 31 * x + y;
    }

    @Override
    public boolean equals (Object obj) {
        if( !(obj instanceof Position) ){
            return false;
        }
        Position other = (Position) obj;
        return x == other.x && y == other.y;
    }

}
