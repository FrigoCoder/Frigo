
package frigo.projecteuler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Problem383 {

    private static class F5FactorialCalculator {

        private long f5NFact = 0;
        private long n = 0;

        public F5FactorialCalculator (long n) {
            if( n < 0 ){
                throw new IllegalArgumentException();
            }
            while( this.n < n ){
                increase();
            }
        }

        public long getF5NFactorial () {
            return f5NFact;
        }

        public void increase () {
            n++;
            f5NFact += f5(n);
        }
    }

    private static final Logger logger = Logger.getLogger(Problem383.class.getName());

    public static long f5 (long n) {
        long result = 0;
        for( long stub = n; stub > 0 && stub % 5 == 0; stub /= 5 ){
            result++;
        }
        return result;
    }

    public static long f5Factorial (long n) {
        long result = 0;
        for( long stub = n; stub >= 5; stub /= 5 ){
            result += stub / 5;
        }
        return result;
    }

    public static void main (String[] args) {
        for( long x = 10; x <= 1_000_000_000; x *= 10 ){
            log("" + x + ": " + T5(x));
        }
    }

    public static long T5 (long n) {
        long count = 0;
        F5FactorialCalculator a = new F5FactorialCalculator(1);
        F5FactorialCalculator b = new F5FactorialCalculator(1);
        for( long i = 1; i <= n; i++ ){
            if( a.getF5NFactorial() < 2 * b.getF5NFactorial() ){
                count++;
            }
            a.increase();
            a.increase();
            b.increase();
        }
        return count;
    }

    private static void log (String message) {
        logger.log(Level.INFO, message);
    }
}
