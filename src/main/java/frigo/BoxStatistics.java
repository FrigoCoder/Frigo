
package frigo;

import static frigo.math.MathAux.sqr;

public class BoxStatistics {

    private final int n;
    private final IntegralArray x;
    private final IntegralArray x2;

    public BoxStatistics (double[] source) {
        n = source.length;
        x = new IntegralArray(source);
        x2 = new IntegralArray(getSquareArray(source));
    }

    private double[] getSquareArray (double[] source) {
        double[] sourceSquare = new double[source.length];
        for( int i = 0; i < sourceSquare.length; i++ ){
            sourceSquare[i] = sqr(source[i]);
        }
        return sourceSquare;
    }

    public double getVarianceInside (Box box) {
        return x2.getAverageInside(box) - sqr(x.getAverageInside(box));
    }

    public double getVarianceOutside (Box box) {
        return x2.getAverageOutside(box) - sqr(x.getAverageOutside(box));
    }

    public double getWeightedVarianceInside (Box box) {
        return box.getCount() * getVarianceInside(box);
    }

    public double getWeightedVarianceOutside (Box box) {
        return (n - box.getCount()) * getVarianceOutside(box);
    }

    public double getWeightedVarianceSum (Box box) {
        return getWeightedVarianceInside(box) + getWeightedVarianceOutside(box);
    }

}
