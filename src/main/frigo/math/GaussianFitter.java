
package frigo.math;

/**
 * Interface for the calculation of the parameters of the normal distribution. The terminology is consistent with
 * statistics.
 * 
 * @author Frigo
 */
public interface GaussianFitter {

    double getExpectedValue (double[] v);

    double getStandardDeviation (double[] v);

    double getVariance (double[] v);
}
