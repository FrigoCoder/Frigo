
package frigo.math.integer;

import static com.google.common.base.Preconditions.checkArgument;

public class MathUnsigned {

    public static boolean isPowerOfTwoUnsigned (int x) {
        return (x & x - 1) == 0 && x != 0;
    }

    public static boolean isPowerOfTwoUnsigned (long x) {
        return (x & x - 1) == 0 && x != 0;
    }

    public static int log2Unsigned (int x) {
        checkArgument(x != 0);
        return 31 - Integer.numberOfLeadingZeros(x);
    }

    public static int log2Unsigned (long x) {
        checkArgument(x != 0);
        return 63 - Long.numberOfLeadingZeros(x);
    }

    public static int sqrtUnsigned (int n) {
        int root = 0;
        int remainder = n;
        for( int bit = 15; bit >= 0; bit-- ){
            int trial = (root << 1) + (1 << bit) << bit;
            if( remainder >= trial || remainder < 0 ){
                remainder -= trial;
                root |= 1 << bit;
            }
        }
        return root;
    }

    public static long sqrtUnsigned (long n) {
        long root = 0;
        long remainder = n;
        for( long bit = 31; bit >= 0; bit-- ){
            long trial = (root << 1) + (1L << bit) << bit;
            if( remainder >= trial || remainder < 0 ){
                remainder -= trial;
                root |= 1L << bit;
            }
        }
        return root;
    }

}
