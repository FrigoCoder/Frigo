
package frigo.math.numbertheory;

public class Functions {

    public static final int MAX_FACTORIAL_INPUT = 12;
    public static final long MAX_FACTORIAL_LONG_INPUT = 20;

    public static int factorial (int n) {
        if( n > MAX_FACTORIAL_INPUT ){
            throw new IllegalArgumentException();
        }
        int result = 1;
        for( int i = 2; i <= n; i++ ){
            result *= i;
        }
        return result;
    }

    public static long factorialLong (long n) {
        if( n > MAX_FACTORIAL_LONG_INPUT ){
            throw new IllegalArgumentException();
        }
        long result = 1;
        for( int i = 2; i <= n; i++ ){
            result *= i;
        }
        return result;
    }

    public static boolean isPrime (int n) {
        if( n <= 1 ){
            return false;
        }
        if( n == 2 ){
            return true;
        }
        if( n % 2 == 0 ){
            return false;
        }
        for( int divisor = 3; divisor * divisor <= n; divisor += 2 ){
            if( n % divisor == 0 ){
                return false;
            }
        }
        return true;
    }
}
