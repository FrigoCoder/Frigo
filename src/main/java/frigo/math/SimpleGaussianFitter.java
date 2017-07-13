
package frigo.math;

import static frigo.math.integer.MathAux.sqr;

/**
 * Estimation of the parameters of the normal distribution based on <a
 * href=http://en.wikipedia.org/wiki/Maximum_likelihood#Continuous_distribution.2C_continuous_parameter_space>maximum
 * likelihood</a>.
 */
public class SimpleGaussianFitter implements GaussianFitter {

    @Override
    public double getExpectedValue (double[] v) {
        double expectedValue = 0.0;
        for( double element : v ){
            expectedValue += element;
        }
        if( v.length != 0 ){
            expectedValue /= v.length;
        }
        return expectedValue;
    }

    @Override
    public double getStandardDeviation (double[] v) {
        return Math.sqrt(getVariance(v));
    }

    /**
     * Calculate the variance using the formula E[(X - Xmean)^2] = E[X^2] - E[X]^2.
     */
    @Override
    public double getVariance (double[] v) {
        return getExpectedSquare(v) - sqr(getExpectedValue(v));
    }

    private double getExpectedSquare (double[] v) {
        double expectedSquare = 0.0;
        for( double element : v ){
            expectedSquare += element * element;
        }
        if( v.length != 0 ){
            expectedSquare /= v.length;
        }
        return expectedSquare;
    }
}
