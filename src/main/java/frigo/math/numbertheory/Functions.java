
package frigo.math.numbertheory;

import static com.google.common.base.Preconditions.checkArgument;

public class Functions {

    public static final int MAX_FACTORIAL_INPUT = 12;
    public static final long MAX_FACTORIAL_LONG_INPUT = 20;

    public static int factorial (int n) {
        checkArgument(n <= MAX_FACTORIAL_INPUT);
        int result = 1;
        for( int i = 2; i <= n; i++ ){
            result *= i;
        }
        return result;
    }

    public static long factorialLong (long n) {
        checkArgument(n <= MAX_FACTORIAL_LONG_INPUT);
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
