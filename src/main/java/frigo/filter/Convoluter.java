
package frigo.filter;

public class Convoluter {

    private static final double sourceSampleCenter = 0.5;

    private Sampler sampler;

    public Convoluter (Kernel kernel) {
        sampler = new Sampler(kernel);
    }

    public double[] convolute (double[] source) {
        return convolute(source, 1.0);
    }

    public double[] convolute (double[] source, double scale) {
        double[] result = new double[source.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = sampler.sample(source, i + sourceSampleCenter, scale);
        }
        return result;
    }

}
