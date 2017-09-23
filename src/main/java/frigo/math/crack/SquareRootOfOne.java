
package frigo.math.crack;

import java.util.LinkedList;
import java.util.List;

import frigo.math.integer.MathInt;

public class SquareRootOfOne {

    public static void main (String[] args) {
        long n = 13 * 23;
        while( n > 0 ){
            System.out.println("n=" + n + ", sqrt(1)=" + squareRootsOfOne(n));
            n ^= MathInt.highestBit(n);
            n >>= 2;
        }
    }

    private static List<Long> squareRootsOfOne (long n) {
        List<Long> result = new LinkedList<>();
        for( long i = 0; i < n; i++ ){
            if( MathInt.powmod(i, 2, n) == 1 ){
                result.add(i);
            }
        }
        return result;
    }

}
