
package frigo.filter;

/**
 * A simple resampler class operating with arrays and continuous kernels based on the ideas of <a
 * href=http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.116.7898>Filters For Common Resampling Tasks</a> and <a
 * href=http://www.virtualdub.org/blog/pivot/entry.php?id=86>How To Make A Resampler That Doesn't Suck</a>
 */
public class Resampler {

    private static final double DESTINATION_SAMPLE_CENTER = 0.5;

    private final Sampler sampler;

    public Resampler (Kernel kernel) {
        sampler = new Sampler(kernel);
    }

    public double[] resample (double[] source, int length) {
        double[] destination = new double[length];
        double location = mapDestinationIndexToSource(length, source.length, 0);
        double increment = mapDestinationIndexToSource(length, source.length, 1) - location;
        double scale = getScale(source.length, length);
        for( int index = 0; index < destination.length; index++ ){
            destination[index] = sampler.sample(source, location, scale);
            location += increment;
        }
        return destination;
    }

    private double getScale (int sourceLength, int destinationLength) {
        return Math.max((double) sourceLength / (double) destinationLength, 1.0);
    }

    private double mapDestinationIndexToSource (int destinationLength, int sourceLength, int index) {
        return (index + DESTINATION_SAMPLE_CENTER) / destinationLength * sourceLength;
    }

}
