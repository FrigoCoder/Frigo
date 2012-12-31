
package frigo.math;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import java.util.logging.Level;
import java.util.logging.Logger;
import frigo.util.ArraysAux;

public class TransformTestBase {

    protected final double epsilon;
    protected final Logger logger;
    protected final int n;

    public TransformTestBase (int n, double epsilon) {
        this.n = n;
        this.epsilon = epsilon;
        logger = Logger.getLogger(this.getClass().getName());
    }

    protected void checkDifference (Complex[] expected, Complex[] actual) {
        assertThat(actual.length, is(expected.length));
        double error = ArraysAux.squaredEuclideanDistance(actual, expected);
        log("Difference: " + Double.toString(error));
        assertThat(error, lessThan(epsilon));
    }

    protected void checkDifference (double[] expected, double[] actual) {
        double error = ArraysAux.squaredEuclideanDistance(expected, actual);
        log("Difference: " + Double.toString(error));
        assertThat(error, lessThan(epsilon));
    }

    protected void log (String message) {
        logger.log(Level.INFO, message);
    }
}
