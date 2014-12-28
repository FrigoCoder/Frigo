
package frigo.math.integer;

import frigo.math.Complex;

public class MathAux {

    public static double sinc (double x) {
        if( x == 0.0 ){
            return 1.0;
        }
        double piX = Math.PI * x;
        return Math.sin(piX) / piX;
    }

    public static float sinc (float x) {
        if( x == 0.0 ){
            return 1.0f;
        }
        double piX = Math.PI * x;
        return (float) (Math.sin(piX) / piX);
    }

    public static Complex sqr (Complex complex) {
        return complex.sqr();
    }

    public static double sqr (double x) {
        return x * x;
    }

    public static float sqr (float x) {
        return x * x;
    }

    public static long sqr (int x) {
        return (long) x * x;
    }

    public static int ceil (double x) {
        return (int) Math.round(Math.ceil(x));
    }

    public static int floor (double x) {
        return (int) Math.round(Math.floor(x));
    }

    public static boolean isPowerOfTwo (int x) {
        return (x & x - 1) == 0 && x > 0;
    }

    public static int isqrt (int n) {
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

    public static long isqrt (long n) {
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

    public static int log2 (int x) {
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
        if( x == 0 ){
            throw new IllegalArgumentException();
        }
        int log2 = -1;
        for( long r = x; r != 0; r >>>= 1 ){
            log2++;
        }
        return log2;
    }

}
