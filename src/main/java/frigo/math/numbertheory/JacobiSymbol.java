
package frigo.math.numbertheory;

/**
 * Based on http://en.wikipedia.org/wiki/Jacobi_symbol#Calculating_the_Jacobi_symbol
 */

public class JacobiSymbol {

    public static long jacobi (long m, long n) {
        if( m == 0 || m == 1 ){
            return m;
        }
        if( m >= n ){
            return jacobi(m % n, n);
        }
        switch( (int) (m % 4) ){
            case 0:
                return jacobi(m / 4, n);
            case 1:
                return jacobi(n % m, m);
            case 2:
                switch( (int) (n % 8) ){
                    case 1:
                    case 7:
                        return jacobi(m / 2, n);
                    case 3:
                    case 5:
                        return -jacobi(m / 2, n);
                    default:
                        throw new IllegalArgumentException();
                }
            case 3:
                switch( (int) (n % 4) ){
                    case 1:
                        return jacobi(n % m, m);
                    case 3:
                        return -jacobi(n % m, m);
                    default:
                        throw new IllegalArgumentException();
                }
            default:
                throw new IllegalArgumentException();
        }
    }

}
