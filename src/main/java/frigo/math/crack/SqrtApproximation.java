
package frigo.math.crack;

import java.util.ArrayList;
import java.util.List;

import frigo.math.integer.FactorizerSieve;
import frigo.math.integer.MathInt;

public class SqrtApproximation {

    public static void main (String[] args) {
        int limit = 2_000_000;
        FactorizerSieve sieve = new FactorizerSieve(limit);

        RuntimeCalculator calculator = new RuntimeCalculator();
        for( long n = 1_000_000; n < limit; n++ ){
            List<Integer> factors = sieve.factor((int) n);
            if( factors.size() == 2 && factors.get(0) != factors.get(1) ){
                SqrtApproximation approx = new SqrtApproximation(n);
                int steps = approx.run();
                calculator.feed(n, steps);
            }
        }
        calculator.print();
    }

    private long n;
    private long x;
    private long y;

    private GeneralizedContinuedFractionUnderN fraction;

    public SqrtApproximation (long n) {
        this.n = n;
        // long ceil = MathAux.isqrt(n) + 1;
        long ceil = n / 2;
        // long ceil = MathAux.isqrt(n);
        x = 2 * ceil;
        y = n - ceil * ceil;
        fraction = new GeneralizedContinuedFractionUnderN(ceil, n);
    }

    public int run () {
        int steps = 2;
        while( !isDone() ){
            fraction.recurse(x, y);
            steps++;
        }
        return steps;
    }

    private boolean isDone () {
        long a = fraction.getA();
        long b = fraction.getB();
        List<Long> gcds = new ArrayList<>();
        gcds.add(MathInt.gcd(n, a + 1));
        gcds.add(MathInt.gcd(n, a - 1));
        gcds.add(MathInt.gcd(n, b + 1));
        gcds.add(MathInt.gcd(n, b - 1));
        gcds.add(MathInt.gcd(n, a + b));
        gcds.add(MathInt.gcd(n, Math.abs(a - b)));
        for( int i = 0; i < gcds.size(); i++ ){
            long gcd = gcds.get(i);
            if( gcd != 1 && gcd != n && gcd != -1 && gcd != -n ){
                return true;
            }
        }
        return false;
    }

}
