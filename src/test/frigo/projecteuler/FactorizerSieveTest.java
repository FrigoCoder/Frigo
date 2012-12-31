
package frigo.projecteuler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class FactorizerSieveTest {

    private final int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private FactorizerSieve sieve = new FactorizerSieve(20);

    @Test
    public void compositenessIsDeterminedCorrectly () {
        for( int number : numbers ){
            assertThat("Compositeness of " + number, sieve.isComposite(number), is(isComposite(number)));
        }
    }

    @Test
    public void divisorsAreCorrect () {
        for( int number : numbers ){
            assertThat("Divisors of " + number, sieve.getDivisors(number), is(getDivisors(number)));
        }
    }

    @Test
    public void factorsAreCorrect () {
        for( int number : numbers ){
            assertThat("Factors of " + number, sieve.getFactors(number), is(getFactors(number)));
        }
    }

    @Test
    public void primalityIsDeterminedCorrectly () {
        for( int number : numbers ){
            assertThat("Primality of " + number, sieve.isPrime(number), is(isPrime(number)));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void sieveIsOnlyValidForSqrShortMaxValue () {
        int maxLimit = Short.MAX_VALUE * Short.MAX_VALUE;
        sieve = new FactorizerSieve(maxLimit + 1);
    }

    @Test
    public void smallestFactorsAreCorrect () {
        for( int number : numbers ){
            if( isSpecial(number) ){
                continue;
            }
            assertThat("" + number + " should have factors", sieve.hasFactors(number), is(true));
            int smallestFactor = getFactors(number).get(0);
            assertThat("Smallest factor of " + number + " should be " + smallestFactor,
                       sieve.getSmallestFactor(number),
                       is(smallestFactor));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void specialOneHasNoSmallestFactor () {
        sieve.getSmallestFactor(1);
    }

    @Test
    public void specialsAreNotComposite () {
        for( int special : numbers ){
            if( !isSpecial(special) ){
                continue;
            }
            assertThat("" + special + " should not be composite", sieve.isComposite(special), is(false));
        }
    }

    @Test
    public void specialsAreNotPrime () {
        for( int special : numbers ){
            if( !isSpecial(special) ){
                continue;
            }
            assertThat("" + special + " should not be prime", sieve.isPrime(special), is(false));
        }
    }

    @Test
    public void specialsDoNotHaveFactors () {
        for( int special : numbers ){
            if( !isSpecial(special) ){
                continue;
            }
            assertThat("" + special + " should not have prime factors", sieve.hasFactors(special), is(false));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void specialZeroHasNoSmallestFactor () {
        sieve.getSmallestFactor(0);
    }

    private List<Integer> getDivisors (int number) {
        List<Integer> divisors = new LinkedList<>();
        divisors.add(1);
        for( int divisor = 2; divisor <= number; divisor++ ){
            if( number % divisor == 0 ){
                divisors.add(divisor);
            }
        }
        return divisors;
    }

    private List<Integer> getFactors (int number) {
        List<Integer> factors = new LinkedList<>();
        int chunk = number;
        for( int factor = 2; factor <= chunk; factor++ ){
            while( chunk % factor == 0 && isPrime(factor) ){
                factors.add(factor);
                chunk /= factor;
            }
        }
        return factors;
    }

    private boolean isComposite (int number) {
        for( int divisor = 2; divisor * divisor <= number; divisor++ ){
            if( number % divisor == 0 ){
                return true;
            }
        }
        return false;
    }

    private boolean isPrime (int number) {
        return !isComposite(number) && !isSpecial(number);
    }

    private boolean isSpecial (int number) {
        return number == 0 || number == 1;
    }
}
