
package frigo.math;

import java.util.LinkedList;
import java.util.List;

public class FactorizerSieve {

    private int[] table;

    public FactorizerSieve (int limit) {
        table = new int[limit];
        fill();
    }

    private void fill () {
        for( int i = 2; i < table.length; i++ ){
            if( table[i] == 0 ){
                mark(i);
            }
        }
    }

    private void mark (int i) {
        table[i] = i;
        long i2 = (long) i * (long) i;
        if( i2 >= table.length ){
            return;
        }
        for( int j = (int) i2; j < table.length; j += i ){
            if( table[j] == 0 ){
                table[j] = i;
            }
        }
    }

    public List<Integer> factor (int x) {
        List<Integer> factors = new LinkedList<>();
        for( int current = x; current > 1; current /= table[current] ){
            factors.add(table[current]);
        }
        return factors;
    }

}
