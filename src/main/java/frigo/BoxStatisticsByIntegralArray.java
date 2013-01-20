
package frigo;

import static frigo.math.MathAux.sqr;

public class BoxStatisticsByIntegralArray extends BoxStatistics {

    private final IntegralArray x;
    private final IntegralArray xSqr;

    public BoxStatisticsByIntegralArray (double[] source) {
        super(source);
        x = new IntegralArray(source);
        xSqr = new IntegralArray(getSquareArray());
    }

    @Override
    public double getVariance (Box box) {
        return xSqr.getAverage(box) - sqr(x.getAverage(box));
    }

    @Override
    public double getVarianceOutside (Box box) {
        return xSqr.getAverageOutside(box) - sqr(x.getAverageOutside(box));
    }

    @Override
    public double getWeightedVariance (Box box) {
        return x.getCount(box) * getVariance(box);
    }

    @Override
    public double getWeightedVarianceOutside (Box box) {
        return x.getCountOutside(box) * getVarianceOutside(box);
    }

    private double[] getSquareArray () {
        double[] sourceSquare = new double[source.length];
        for( int i = 0; i < sourceSquare.length; i++ ){
            sourceSquare[i] = sqr(source[i]);
        }
        return sourceSquare;
    }
}
