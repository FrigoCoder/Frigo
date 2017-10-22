
package frigo.filter;

import static frigo.math.integer.MathAux.ceil;
import static frigo.math.integer.MathAux.floor;

public class Sampler {

    private static final double SOURCE_SAMPLE_CENTER = 0.5;
    private static final double DESTINATION_SAMPLE_CENTER = 0.5;

    private Kernel kernel;

    public Sampler (Kernel kernel) {
        this.kernel = kernel;
    }

    public double sample (double[] source, double center, double scale) {
        int first = Math.max(getSourceIndexOfKernelLeft(center, scale), 0);
        int last = Math.min(getSourceIndexOfKernelRight(center, scale), source.length - 1);
        double result = 0.0;
        double sum = 0.0;
        double location = mapSourceIndexToKernel(center, scale, first);
        double increment = mapSourceIndexToKernel(center, scale, first + 1) - location;
        for( int index = first; index <= last; index++ ){
            double weight = kernel.evaluate(location);
            result += source[index] * weight;
            sum += Math.abs(weight);
            location += increment;
        }
        if( sum == 0.0 ){
            return 0.0;
        }
        return result / sum;
    }

    private int getSourceIndexOfKernelRight (double center, double scale) {
        return floor(mapKernelToSource(center, scale, kernel.right) - SOURCE_SAMPLE_CENTER);
    }

    private int getSourceIndexOfKernelLeft (double center, double scale) {
        return ceil(mapKernelToSource(center, scale, kernel.left) - SOURCE_SAMPLE_CENTER);
    }

    private double mapSourceIndexToKernel (double center, double scale, int index) {
        return (index + SOURCE_SAMPLE_CENTER - center) / scale;
    }

    private double mapKernelToSource (double center, double scale, double location) {
        return center + location * scale;
    }

    public double[] convolute (double[] source) {
        return convolute(source, 1.0);
    }

    public double[] convolute (double[] source, double scale) {
        double[] result = new double[source.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = sample(source, i + SOURCE_SAMPLE_CENTER, scale);
        }
        return result;
    }

    public double[] resample (double[] source, int length) {
        double[] destination = new double[length];
        double location = mapDestinationIndexToSource(length, source.length, 0);
        double increment = mapDestinationIndexToSource(length, source.length, 1) - location;
        double scale = getScale(source.length, length);
        for( int index = 0; index < destination.length; index++ ){
            destination[index] = sample(source, location, scale);
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
