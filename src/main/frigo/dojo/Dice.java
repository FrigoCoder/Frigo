
package frigo.dojo;

import java.util.Random;

public class Dice {

    public static final int NUMBER_OF_VALUES = 6;
    private final Random random;

    public Dice () {
        random = new Random();
    }

    public int roll () {
        return random.nextInt(NUMBER_OF_VALUES) + 1;
    }
}
