
package frigo.projecteuler;

import static com.google.common.base.Preconditions.checkArgument;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import com.google.common.base.Preconditions;

public class FactorizerSieve {

    private static final int PRIME = -1;
    private static final int SPECIAL = -2;
    private static final int UNPROCESSED = 0;
    private final short[] table;

    public FactorizerSieve (int limit) {
        int maxLimit = Short.MAX_VALUE * Short.MAX_VALUE;
        checkArgument(limit <= maxLimit, "This sieve is only correct for limit <= " + maxLimit);
        table = new short[limit];
        fillTable();
    }

    public List<Integer> getDivisors (int number) {
        Set<Integer> divisors = new HashSet<>();
        divisors.add(1);
        for( int factor : getFactors(number) ){
            divisors.addAll(getMultipliedSet(divisors, factor));
        }
        return new LinkedList<>(divisors);
    }

    public List<Integer> getFactors (int number) {
        List<Integer> factors = new LinkedList<>();
        for( int current = number; hasFactors(current); current /= getSmallestFactor(current) ){
            factors.add(getSmallestFactor(current));
        }
        return factors;
    }

    public int getSmallestFactor (int number) {
        checkArgument(hasFactors(number), number + " does not have any prime factors");
        return isPrime(number) ? number : table[number];
    }

    public boolean hasFactors (int number) {
        return !isSpecial(number);
    }

    public boolean isComposite (int i) {
        return !isPrime(i) && !isSpecial(i);
    }

    public boolean isPrime (int number) {
        return table[number] == PRIME;
    }

    private void fillTable () {
        setSpecial(0);
        setSpecial(1);
        for( int i = 2; i < table.length; i++ ){
            if( isUnprocessed(i) ){
                setPrime(i);
                markMultiples(i);
            }
        }
    }

    private Collection<Integer> getMultipliedSet (Set<Integer> divisors, int factor) {
        Set<Integer> result = new HashSet<>();
        for( int divisor : divisors ){
            result.add(divisor * factor);
        }
        return result;
    }

    private boolean isSpecial (int i) {
        return table[i] == SPECIAL;
    }

    private boolean isUnprocessed (int i) {
        return table[i] == UNPROCESSED;
    }

    private void markMultiples (int i) {
        if( i > Short.MAX_VALUE ){
            return;
        }
        for( int j = i * i; j < table.length; j += i ){
            if( isUnprocessed(j) ){
                table[j] = (short) i;
            }
        }
    }

    private void setPrime (int i) {
        table[i] = PRIME;
    }

    private void setSpecial (int i) {
        table[i] = SPECIAL;
    }
}
