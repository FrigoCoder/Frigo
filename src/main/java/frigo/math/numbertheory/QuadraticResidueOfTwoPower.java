
package frigo.math.numbertheory;

import frigo.math.integer.MathInt;

public class QuadraticResidueOfTwoPower {

    public static boolean isQuadraticResidue (long x, long p) {
        if( !MathInt.isPowerOfTwo(p) ){
            throw new IllegalArgumentException();
        }
        long odd = stripPowersOf4(x, p);
        return odd == 0 || odd % 8 == 1;
    }

    private static long stripPowersOf4 (long x, long p) {
        long odd = x % p;
        while( odd % 4 == 0 && odd != 0 ){
            odd /= 4;
        }
        return odd;
    }

}
