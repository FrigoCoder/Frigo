
package frigo.math;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.Random;
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

    public static double[] getRandomDoubleArray (int length) {
        return TransformTestBase.getRandomDoubleArray(length, new Random());
    }

    public static double[] getRandomDoubleArray (int length, Random random) {
        double[] array = new double[length];
        for( int i = 0; i < array.length; i++ ){
            array[i] = random.nextDouble();
        }
        return array;
    }

    public static Complex[] getRandomComplexArray (int length) {
        return getRandomComplexArray(length, new Random());
    }

    public static Complex[] getRandomComplexArray (int length, Random random) {
        Complex[] array = new Complex[length];
        for( int i = 0; i < array.length; i++ ){
            array[i] = new Complex(random.nextDouble(), random.nextDouble());
        }
        return array;
    }

}
