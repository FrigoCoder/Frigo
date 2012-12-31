
package frigo;

public abstract class BoxStatistics {

    protected double[] source;

    public BoxStatistics (double[] source) {
        this.source = source.clone();
    }

    public abstract double getVariance (Box box);

    public abstract double getVarianceOutside (Box box);

    public abstract double getWeightedVariance (Box box);

    public abstract double getWeightedVarianceOutside (Box box);

    public double getWeightedVarianceSum (Box box) {
        return getWeightedVariance(box) + getWeightedVarianceOutside(box);
    }
}
