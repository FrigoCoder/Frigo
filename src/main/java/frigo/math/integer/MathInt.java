
package frigo.math.integer;

public class MathInt {

    public static boolean isPowerOfTwo (int x) {
        return (x & x - 1) == 0 && x > 0;
    }

    public static boolean isPowerOfTwo (long x) {
        return (x & x - 1) == 0 && x > 0;
    }

    public static int log2 (int x) {
        if( x <= 0 ){
            throw new IllegalArgumentException();
        }
        int log2 = -1;
        for( int r = x; r != 0; r >>= 1 ){
            log2++;
        }
        return log2;
    }

    public static int log2Unsigned (int x) {
        if( x == 0 ){
            throw new IllegalArgumentException();
        }
        int log2 = -1;
        for( int r = x; r != 0; r >>>= 1 ){
            log2++;
        }
        return log2;
    }

    public static int log2 (long x) {
        if( x <= 0 ){
            throw new IllegalArgumentException();
        }
        int log2 = -1;
        for( long r = x; r != 0; r >>>= 1 ){
            log2++;
        }
        return log2;
    }

    public static int log2Unsigned (long x) {
        if( x == 0 ){
            throw new IllegalArgumentException();
        }
        int log2 = -1;
        for( long r = x; r != 0; r >>>= 1 ){
            log2++;
        }
        return log2;
    }

    public static long sqr (int x) {
        return (long) x * x;
    }

    public static long sqr (long x) {
        return x * x;
    }

    public static int sqrt (int n) {
        if( n < 0 ){
            throw new IllegalArgumentException();
        }
        int root = 0;
        int remainder = n;
        for( int bit = 15; bit >= 0; bit-- ){
            int trial = (root << 1) + (1 << bit) << bit;
            if( remainder >= trial ){
                remainder -= trial;
                root |= 1 << bit;
            }
        }
        return root;
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

    public static long sqrt (long n) {
        if( n < 0 ){
            throw new IllegalArgumentException();
        }
        long root = 0;
        long remainder = n;
        for( long bit = 31; bit >= 0; bit-- ){
            long trial = (root << 1) + (1L << bit) << bit;
            if( remainder >= trial ){
                remainder -= trial;
                root |= 1L << bit;
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
