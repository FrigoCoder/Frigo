
package frigo.math.numbertheory;

public class Functions {

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
