
package frigo.filter;

import static frigo.math.MathAux.ceil;
import static frigo.math.MathAux.floor;

public class Sampler {

    private static final double sourceSampleCenter = 0.5;

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
        return floor(mapKernelToSource(center, scale, kernel.right) - sourceSampleCenter);
    }

    private int getSourceIndexOfKernelLeft (double center, double scale) {
        return ceil(mapKernelToSource(center, scale, kernel.left) - sourceSampleCenter);
    }

    private double mapSourceIndexToKernel (double center, double scale, int index) {
        return (index + sourceSampleCenter - center) / scale;
    }

    private double mapKernelToSource (double center, double scale, double location) {
        return center + location * scale;
    }

}
