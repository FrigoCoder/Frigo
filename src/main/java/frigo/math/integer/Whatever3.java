
package frigo.math.integer;


public class Whatever3 {

    public static void main (String[] args) {

        long n = 23 * 103;
        // long n = 124989250231L;
        // long n = 103 * 499979L;

        Factorizer factorizer = new Factorizer(n);
        factorizer.factor();
    }

}
