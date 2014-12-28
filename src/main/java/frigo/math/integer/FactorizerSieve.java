
package frigo.math.integer;

import java.util.LinkedList;
import java.util.List;

public class FactorizerSieve {

    private int[] table;

    public FactorizerSieve (int limit) {
        table = new int[limit + 1];
        fill();
    }

    private void fill () {
        table[0] = -1;
        table[1] = -1;
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

    public boolean prime (int x) {
        return x > 1 && table[x] == x;
    }

    public boolean biprime (int n) {
        int p = table[n];
        int q = n / p;
        return p != q && prime(q);
    }

}
