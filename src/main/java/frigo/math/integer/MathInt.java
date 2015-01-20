
package frigo.math.integer;

public class MathInt {

    public static int factorial (int n) {
        if( n > 12 ){
            throw new IllegalArgumentException();
        }
        int factorial = 1;
        for( int i = 2; i <= n; i++ ){
            factorial *= i;
        }
        return factorial;
    }

    public static long factorial (long n) {
        if( n > 20 ){
            throw new IllegalArgumentException();
        }
        long factorial = 1;
        for( int i = 2; i <= n; i++ ){
            factorial *= i;
        }
        return factorial;
    }

    public static int gcd (int u, int v) {
        return v == 0 ? u : gcd(v, u % v);
    }

    public static long gcd (long u, long v) {
        return v == 0 ? u : gcd(v, u % v);
    }

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
        return 31 - Integer.numberOfLeadingZeros(x);
    }

    public static int log2 (long x) {
        if( x <= 0 ){
            throw new IllegalArgumentException();
        }
        return 63 - Long.numberOfLeadingZeros(x);
    }

    public static int pow (int base, int exponent) {
        if( exponent < 0 ){
            throw new IllegalArgumentException();
        }
        int result = 1;
        for( int actual = base, bits = exponent; bits != 0; bits >>= 1 ){
            if( (bits & 1) == 1 ){
                result *= actual;
            }
            actual *= actual;
        }
        return result;
    }

    public static long pow (long base, long exponent) {
        if( exponent < 0 ){
            throw new IllegalArgumentException();
        }
        long result = 1;
        for( long actual = base, bits = exponent; bits != 0; bits >>= 1 ){
            if( (bits & 1) == 1 ){
                result *= actual;
            }
            actual *= actual;
        }
        return result;
    }

    public static int powmod (int base, int exponent, int modulus) {
        if( base < 0 || exponent < 0 || modulus <= 0 ){
            throw new IllegalArgumentException();
        }
        int result = 1;
        for( int actual = base, bits = exponent; bits != 0; bits >>= 1 ){
            if( (bits & 1) == 1 ){
                result = mulmod(result, actual, modulus);
            }
            actual = mulmod(actual, actual, modulus);
        }
        return result;
    }

    private static int mulmod (int multiplicand, int multiplier, int modulus) {
        int result = 0;
        for( int actual = multiplicand % modulus, bits = multiplier; bits != 0; bits >>= 1 ){
            if( (bits & 1) == 1 ){
                result += actual;
                if( result >= modulus ){
                    result -= modulus;
                }
            }
            actual += actual;
            if( actual >= modulus ){
                actual -= modulus;
            }
        }
        return result;
    }

    public static long powmod (long base, long exponent, long modulus) {
        if( base < 0 || exponent < 0 || modulus <= 0 ){
            throw new IllegalArgumentException();
        }
        long result = 1;
        for( long actual = base, bits = exponent; bits != 0; bits >>= 1 ){
            if( (bits & 1) == 1 ){
                result = mulmod(result, actual, modulus);
            }
            actual = mulmod(actual, actual, modulus);
        }
        return result;

    }

    private static long mulmod (long multiplicand, long multiplier, long modulus) {
        long result = 0;
        for( long actual = multiplicand % modulus, bits = multiplier; bits != 0; bits >>= 1 ){
            if( (bits & 1) == 1 ){
                result += actual;
                if( result >= modulus ){
                    result -= modulus;
                }
            }
            actual += actual;
            if( actual >= modulus ){
                actual -= modulus;
            }
        }
        return result;
    }

    public static int sgn (int x) {
        return x == 0 ? 0 : x > 0 ? 1 : -1;
    }

    public static long sgn (long x) {
        return x == 0 ? 0 : x > 0 ? 1 : -1;
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

}
