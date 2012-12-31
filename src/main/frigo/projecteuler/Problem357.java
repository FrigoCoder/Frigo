
package frigo.projecteuler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Problem357 {

    public static void main (String[] args) {
        new Problem357().run(100_000_000);
    }

    private final Logger logger;
    private int n;
    private FactorizerSieve sieve;

    public Problem357 () {
        logger = Logger.getLogger(this.getClass().getName());
    }

    public void run (int limit) {
        long startTime = getTime();
        n = limit;
        log("Sieving started");
        sieve = new FactorizerSieve(limit + 2);
        log("Elapsed time: " + (getTime() - startTime));
        log("Composite checking started");
        log("Result: " + calculateResult());
        log("Elapsed time: " + (getTime() - startTime));
    }

    long calculateResult () {
        long sum = 0;
        for( int number = 0; number <= n; number++ ){
            if( isDesirableNumber(number) ){
                sum += number;
            }
        }
        return sum;
    }

    boolean isDesirableNumber (int number) {
        if( number != 1 && isOdd(number) ){
            return false;
        }
        if( !sieve.isPrime(number + 1) ){
            return false;
        }
        if( !sieve.isPrime(number / 2 + 2) ){
            return false;
        }
        for( int factor : sieve.getFactors(number) ){
            if( !sieve.isPrime(number / factor + factor) ){
                return false;
            }
        }
        for( int divisor : sieve.getDivisors(number) ){
            if( !sieve.isPrime(number / divisor + divisor) ){
                return false;
            }
        }
        return true;
    }

    private long getTime () {
        return System.currentTimeMillis();
    }

    private boolean isOdd (int number) {
        return (number & 1) == 1;
    }

    private void log (String message) {
        logger.log(Level.INFO, message);
    }
}
