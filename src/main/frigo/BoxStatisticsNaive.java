
package frigo;

import static frigo.math.MathAux.sqr;

public class BoxStatisticsNaive extends BoxStatistics {

    public BoxStatisticsNaive (double[] source) {
        super(source);
    }

    @Override
    public double getVariance (Box box) {
        return getWeightedVariance(box) / getCount(box.left, box.right);
    }

    @Override
    public double getVarianceOutside (Box box) {
        return getWeightedVarianceOutside(box) / getCountOutside(box.left, box.right);
    }

    @Override
    public double getWeightedVariance (Box box) {
        double average = getAverage(box.left, box.right);
        double variance = 0.0;
        for( int i = box.left; i <= box.right; i++ ){
            variance += sqr(source[i] - average);
        }
        return variance;
    }

    @Override
    public double getWeightedVarianceOutside (Box box) {
        double average = getAverageOutside(box.left, box.right);
        double variance = 0.0;
        for( int i = 0; i < box.left; i++ ){
            variance += sqr(source[i] - average);
        }
        for( int i = box.right + 1; i < source.length; i++ ){
            variance += sqr(source[i] - average);
        }
        return variance;
    }

    double getAverage (int left, int right) {
        double average = 0.0;
        for( int i = left; i <= right; i++ ){
            average += source[i];
        }
        average /= getCount(left, right);
        return average;
    }

    double getAverageOutside (int left, int right) {
        double average = 0.0;
        for( int i = 0; i < left; i++ ){
            average += source[i];
        }
        for( int i = right + 1; i < source.length; i++ ){
            average += source[i];
        }
        average /= getCountOutside(left, right);
        return average;
    }

    int getCount (int left, int right) {
        return right - left + 1;
    }

    int getCountOutside (int left, int right) {
        return source.length - (right - left + 1);
    }
}
