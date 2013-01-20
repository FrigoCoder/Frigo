
package frigo.filter;

import static com.google.common.base.Preconditions.checkArgument;
import static frigo.math.MathAux.sqr;

/**
 * Gauss kernel implemented according to the probability density function of the normal distribution. See <a
 * href=http://en.wikipedia.org/wiki/Normal_distribution>Wikipedia</a>
 **/
public class Gauss extends Kernel {

    private final double k1;
    private final double k2;

    public Gauss () {
        this(0.5, 2.5);
    }

    /**
     * Creates a Gauss kernel with the given standard deviation and a cutoff radius that is 5 times this standard
     * deviation. This makes sure that the overwhelming majority of the area under the Gauss function remains inside the
     * cutoff interval, and there is insignificant information loss or distortion. Use the other constructor if you want
     * to finetune this behaviour, to tip the scales on the tradeoff between speed and quality.
     * @param sigma The standard deviation of the Gauss function, determining the "width" or spread of the function, and
     *            thus indirectly influencing the time resolution of the sampling process.
     */
    public Gauss (double sigma) {
        this(sigma, 5.0 * sigma);
    }

    /**
     * Creates a Gauss kernel with the given standard deviation and cutoff radius. Cutoff radius must be chosen
     * carefully, it directly influences the tradeoff between the speed and quality of the filter. See <a
     * href=http://en.wikipedia.org/wiki/68-95-99.7_rule#Higher_deviations>this Wikipedia page</a> for good cutoff
     * values in terms of the standard deviation. A ratio of cutoff radius per standard deviation of 2.0 gives ~95.49%
     * coverage, and should be enough for real-time tasks. 3.0 with ~99.73% coverage is enough for image processing, 4.0
     * and 5.0 with >99.99% coverage is best suited for audio processing. Defaults to 5.0, anything above is overkill.
     * @param sigma The standard deviation of the Gauss function, determining the "width" or spread of the function, and
     *            thus indirectly influencing the time resolution of the sampling process.
     * @param radius The cutoff radius
     */
    public Gauss (double sigma, double radius) {
        super(radius);
        checkArgument(sigma > 0.0, "sigma must be > 0.0");
        k1 = 1.0 / Math.sqrt(2.0 * Math.PI * sqr(sigma));
        k2 = -0.5 / sqr(sigma);
    }

    @Override
    public double evaluate (double x) {
        if( !isInDomain(x) ){
            return 0.0;
        }
        return k1 * Math.exp(k2 * x * x);
    }
}
