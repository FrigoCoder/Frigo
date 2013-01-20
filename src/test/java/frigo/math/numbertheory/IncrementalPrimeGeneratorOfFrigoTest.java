
package frigo.math.numbertheory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class IncrementalPrimeGeneratorOfFrigoTest {

    @Test
    public void firstTenPrimesAreGeneratedCorrectly () {
        IncrementalPrimeGeneratorOfFrigo sieve = new IncrementalPrimeGeneratorOfFrigo();
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        for( int prime : primes ){
            assertThat(sieve.nextPrime(), is(prime));
        }
        List<Integer> primesSoFar = sieve.getPrimesSoFar();
        int i = 0;
        for( int prime : primesSoFar ){
            assertThat(prime, is(primes[i]));
            i++;
        }
    }
}
