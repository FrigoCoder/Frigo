
package frigo.math.numbertheory;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import frigo.math.integer.MathInt;

public class QuadraticResidueOfTwoPower {

    public static boolean isQuadraticResidue (long x, long p) {
        checkArgument(MathInt.isPowerOfTwo(p));
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

    public static List<Long> sqrt (long x2, long p) {
        checkArgument(0 <= x2 && x2 < p && MathInt.isPowerOfTwo(p));
        if( p == 1 ){
            return Arrays.asList(0L);
        }
        long p2 = p / 2;
        List<Long> result = new ArrayList<>();
        List<Long> previous = sqrt(x2 % p2, p2);
        for( long x : previous ){
            if( x * x % p == x2 ){
                result.add(x);
            }
        }
        for( long x : previous ){
            long y = x + p2;
            if( y * y % p == x2 ){
                result.add(y);
            }
        }
        return result;
    }

}
