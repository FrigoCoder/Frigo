
package frigo.dojo;

import com.google.common.annotations.VisibleForTesting;

public class Player {

    public static final int INITIAL_HEALTH = 100;

    @VisibleForTesting
    Dice dice = new Dice();
    private int health = INITIAL_HEALTH;

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

    private void damage (int amount) {
        health -= amount;
    }
}
