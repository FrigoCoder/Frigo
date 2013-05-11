
package frigo.electronics;

import static frigo.electronics.IEC60063.E12;
import static frigo.electronics.IEC60063.applyDecades;
import static frigo.electronics.Prefix.toUnit;
import static frigo.electronics.Util.qFactorToOctaveBandwidth;
import static frigo.math.MathAux.sqr;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class BruteForceParallelRlcWithLoadFinder {

    public static void main (String[] args) {
        String fileName = "response.txt";
        BruteForceParallelRlcWithLoadFinder finder = new BruteForceParallelRlcWithLoadFinder(fileName);

        ParallelRlcWithLoad rlc = finder.getBestRlc();
        System.out.println("Best: " + rlc);
        System.out.println("f0 = " + rlc.f0());
        System.out.println("gain = " + rlc.gain());
        System.out.println("q = " + rlc.q());
        System.out.println("bw = " + qFactorToOctaveBandwidth(rlc.q()));
    }

    private ResponseInterpolator headphone;
    private double load = 35;
    private double left = 20;
    private double right = 16000;
    private int samples = 100;
    private double[] samplingFrequencies = getSamplingFrequencies();

    private double[] Rs = applyDecades(E12, new double[] {10, 100});
    private double[] Ls = applyDecades(E12, new double[] {toUnit("100u"), toUnit("1m"), toUnit("10m"), toUnit("100m")});
    private double[] Cs = applyDecades(E12, new double[] {toUnit("1n"), toUnit("10n"), toUnit("100n"), toUnit("1m")});

    public BruteForceParallelRlcWithLoadFinder (String fileName) {
        headphone = new ResponseInterpolator(fileName);
        E12 = E12;
    }

    private double[] getSamplingFrequencies () {
        double[] points = new double[samples];
        double increment = (right - left) / (samples - 1);
        points[0] = left;
        for( int i = 1; i < points.length; i++ ){
            points[i] = points[i - 1] + increment;
        }
        return points;
    }

    public ParallelRlcWithLoad getBestRlc () {
        ParallelRlcWithLoad bestRlc = null;
        double bestVariance = Double.POSITIVE_INFINITY;
        for( double R : Rs ){
            for( double L : Ls ){
                for( double C : Cs ){
                    ParallelRlcWithLoad rlc = new ParallelRlcWithLoad(R, L, C, load);
                    System.out.println(rlc);
                    double variance = evaluate(rlc);
                    if( bestVariance > variance ){
                        bestRlc = rlc;
                        bestVariance = variance;
                    }
                }
            }
        }
        System.out.println(bestVariance);
        return bestRlc;
    }

    private double evaluate (ParallelRlcWithLoad rlc) {
        return getSquareRootDifference(rlc, getHeadphoneAverage());
        // return getDiff(4800, rlc.f0()) + getDiff(-10, rlc.gain()) + getDiff(-7, rlc.response(1683.749))
        // + getDiff(-7, rlc.response(13683.749));
    }

    private double getDiff (double target, double actual) {
        return sqr((target - actual) / target);
    }

    private double getHeadphoneAverage () {
        double average = 0.0;
        for( double frequency : samplingFrequencies ){
            average += headphone.response(frequency);
        }
        average /= samplingFrequencies.length;
        return average;
    }

    private double getSquareRootDifference (ParallelRlcWithLoad rlc, double average) {
        double difference = 0.0;
        for( double frequency : samplingFrequencies ){
            difference += sqrt(abs(headphone.response(frequency) - average + rlc.response(frequency)));
        }
        difference /= samplingFrequencies.length;
        return difference;
    }
    //
    // private double getAbsoluteDifference (ParallelRlcWithLoad rlc, double average) {
    // double difference = 0.0;
    // for( double frequency : samplingFrequencies ){
    // difference += abs(headphone.response(frequency) - average + rlc.response(frequency));
    // }
    // difference /= samplingFrequencies.length;
    // return difference;
    // }
    //
    // private double getSquareDifference (ParallelRlcWithLoad rlc, double average) {
    // double difference = 0.0;
    // for( double frequency : samplingFrequencies ){
    // difference += sqr(headphone.response(frequency) - average + rlc.response(frequency));
    // }
    // difference /= samplingFrequencies.length;
    // return difference;
    // }

}
