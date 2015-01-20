
package frigo.math.crack;

import java.util.ArrayList;
import java.util.List;

public class FactorialNumberSystem {

    private List<Long> placeValues = new ArrayList<>();
    private List<Long> highestDigits = new ArrayList<>();

    public FactorialNumberSystem (long highest) {
        int i = 1;
        placeValues.add(factorial(i));
        highestDigits.add((long) i);
        for( i = 2; highest() < highest; i++ ){
            placeValues.add(factorial(i));
            highestDigits.add((long) i);
        }
        placeValues.add(factorial(i));
        highestDigits.add((long) i);
    }

    private long highest () {
        long highest = 0;
        for( int i = 0; i < placeValues.size(); i++ ){
            highest += highestDigit(i) * placeValue(i);
        }
        return highest;
    }

    private long factorial (int k) {
        return k == 0 ? 1 : k * factorial(k - 1);
    }

    public int size () {
        return placeValues.size();
    }

    public long placeValue (int radix) {
        return placeValues.get(radix);
    }

    public long highestDigit (int radix) {
        return highestDigits.get(radix);
    }

}
