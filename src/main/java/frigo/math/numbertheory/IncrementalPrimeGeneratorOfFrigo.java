
package frigo.math.numbertheory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import frigo.math.MathAux;

public class IncrementalPrimeGeneratorOfFrigo implements IncrementalPrimeGenerator {

    private int current;
    private int currentSquareRoot;
    private List<Integer> primes;

    public IncrementalPrimeGeneratorOfFrigo () {
        reset();
    }

    @Override
    public List<Integer> getPrimesSoFar () {
        return Collections.unmodifiableList(primes);
    }

    @Override
    public int nextPrime () {
        increaseCurrent();
        while( !isCurrentPrime() ){
            increaseCurrent();
        }
        primes.add(current);
        return current;
    }

    @Override
    public void reset () {
        current = 1;
        currentSquareRoot = 1;
        primes = new ArrayList<>();
    }

    private void increaseCurrent () {
        current++;
        if( MathAux.sqr(currentSquareRoot + 1) <= current ){
            currentSquareRoot++;
        }
    }

    private boolean isCurrentPrime () {
        for( int prime : primes ){
            if( prime > currentSquareRoot ){
                return true;
            }
            if( current % prime == 0 ){
                return false;
            }
        }
        return true;
    }
}
