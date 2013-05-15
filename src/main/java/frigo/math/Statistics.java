
package frigo.math;

import static frigo.math.MathAux.sqr;

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

}
