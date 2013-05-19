
package frigo.math;

import static com.google.common.base.Preconditions.checkArgument;
import static frigo.math.MathAux.sqr;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Statistics {

    public static double average (double[] values) {
        double average = 0.0;
        for( double value : values ){
            average += value;
        }
        average /= values.length;
        return average;
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

    public static double manhattanDistance (double[] v, double[] w) {
        return norm(minus(v, w), 1);
    }

    public static double euclideanDistance (double[] v, double[] w) {
        return norm(minus(v, w), 2);
    }

    public static double norm (double[] v, double p) {
        double result = 0.0;
        for( int i = 0; i < v.length; i++ ){
            result += pow(abs(v[i]), p);
        }
        result = pow(result, 1.0 / p);
        return result;
    }

    private static double[] minus (double[] v, double[] w) {
        checkArgument(v.length == w.length);
        double[] result = new double[v.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = v[i] - w[i];
        }
        return result;
    }

}
