
package frigo.math.numbertheory;

import java.util.List;

public interface IncrementalPrimeGenerator {

    List<Integer> getPrimesSoFar ();

    int nextPrime ();

    void reset ();
}
