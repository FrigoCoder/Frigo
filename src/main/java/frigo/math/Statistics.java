
package frigo.math;

import static com.google.common.base.Preconditions.checkArgument;
import static frigo.math.MathAux.sqr;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Statistics {

    public static double average (double[] values) {
        return sum(values) / values.length;
    }

    public static double sum (double[] values) {
        double sum = 0.0;
        for( double value : values ){
            sum += value;
        }
        return sum;
    }

    public static double variance (double[] values) {
        double variance = 0.0;
        double average = average(values);
        for( double value : values ){
            variance += sqr(value - average);
        }
        variance /= values.length;
        return variance;
    }

    public static double standardDeviation (double[] values) {
        return sqrt(variance(values));
    }

    public static double manhattanDistance (double[] v, double[] w) {
        return norm(minus(v, w), 1);
    }

    public static double euclideanDistance (double[] v, double[] w) {
        return norm(minus(v, w), 2);
    }

    public static double norm (double[] v, double p) {
        double sum = 0.0;
        for( double x : v ){
            sum += pow(abs(x), p);
        }
        return pow(sum, 1.0 / p);
    }

    private static double[] minus (double[] v, double[] w) {
        checkArgument(v.length == w.length);
        double[] result = new double[v.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = v[i] - w[i];
        }
        return result;
    }

    public static double max (double[] v) {
        double max = Double.NEGATIVE_INFINITY;
        for( double value : v ){
            max = Math.max(max, value);
        }
        return max;
    }

    public static double maxAbs (double[] v) {
        double max = Double.NEGATIVE_INFINITY;
        for( double value : v ){
            max = Math.max(max, abs(value));
        }
        return max;
    }

    public static double min (double[] v) {
        double min = Double.POSITIVE_INFINITY;
        for( double value : v ){
            min = Math.min(min, value);
        }
        return min;
    }

    public static double minAbs (double[] v) {
        double min = Double.POSITIVE_INFINITY;
        for( double value : v ){
            min = Math.min(min, abs(value));
        }
        return min;
    }

}
