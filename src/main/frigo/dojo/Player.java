
package frigo.dojo;

public class Player {

    public static final int INITIAL_HEALTH = 100;
    Dice dice;
    private int health = INITIAL_HEALTH;

    public Player () {
        dice = new Dice();
    }

    public void damage () {
        int amount = dice.roll() + dice.roll();
        switch( amount ){
            case 2:
                damage(0);
                break;
            case 12:
                damage(24);
                break;
            default:
                damage(amount);
                break;
        }
    }

    public int getHealth () {
        return health;
    }

    void damage (int amount) {
        health -= amount;
    }
}
