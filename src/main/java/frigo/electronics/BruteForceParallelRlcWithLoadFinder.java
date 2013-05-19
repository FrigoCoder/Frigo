
package frigo.electronics;

import static frigo.electronics.IEC60063.E12;
import static frigo.electronics.IEC60063.applyDecades;
import static frigo.electronics.Prefix.toUnit;
import static frigo.electronics.Util.qFactorToOctaveBandwidth;
import frigo.math.Statistics;

public class BruteForceParallelRlcWithLoadFinder {

    public static void main (String[] args) {
        String sourceFile = "HD681.txt";
        String targetFile = "HD681EVO.txt";
        BruteForceParallelRlcWithLoadFinder finder = new BruteForceParallelRlcWithLoadFinder(sourceFile, targetFile);

        ParallelRlcWithLoad rlc = finder.getBestRlc();
        log("Best: " + rlc);
        log("f0 = " + rlc.f0());
        log("gain = " + rlc.gain());
        log("q = " + rlc.q());
        log("bw = " + qFactorToOctaveBandwidth(rlc.q()));
    }

    public static void log (Object message) {
        System.out.println(message);
    }

    private ResponseInterpolator source;
    private ResponseInterpolator target;
    private double load = 35;
    private double left = 1000;
    private double right = 16000;
    private int samples = 100;
    private double[] samplingFrequencies = getSamplingFrequencies();
    private double[] targetSamples;

    private double[] Rs = applyDecades(E12, new double[] {1, 10, 100});
    private double[] Ls = applyDecades(E12, new double[] {toUnit("100u"), toUnit("1m"), toUnit("10m"), toUnit("100m")});
    private double[] Cs = applyDecades(E12, new double[] {toUnit("1n"), toUnit("10n"), toUnit("100n"), toUnit("1m")});

    public BruteForceParallelRlcWithLoadFinder (String sourceFile, String targetFile) {
        source = new ResponseInterpolator(sourceFile);
        target = new ResponseInterpolator(targetFile);
        targetSamples = sampleTargetHeadphone();
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
        Minimum<ParallelRlcWithLoad> minimum = new Minimum<>();
        for( double R : Rs ){
            for( double L : Ls ){
                for( double C : Cs ){
                    ParallelRlcWithLoad rlc = new ParallelRlcWithLoad(R, L, C, load);
                    double value = evaluate(rlc);
                    minimum.add(rlc, value);
                }
            }
        }
        log(minimum.bestObject);
        log(minimum.bestValue);
        return minimum.bestObject;
    }

    private double evaluate (ParallelRlcWithLoad rlc) {
        // return Statistics.variance(sampleFilteredHeadphone(rlc));
        // return Statistics.euclideanDistance(sampleFilteredHeadphone(rlc), targetSamples);
        return Statistics.absoluteDifference(sampleFilteredHeadphone(rlc), targetSamples);
        // return d(rlc.f0(), 4800) + d(rlc.gain(), -8) + d(rlc.q(), 0.5);
    }

    // private double d (double actual, double expected) {
    // return abs(expected - actual) / expected;
    // }

    private double[] sampleFilteredHeadphone (ParallelRlcWithLoad rlc) {
        double[] sampledValues = new double[samples];
        for( int i = 0; i < samples; i++ ){
            double f = samplingFrequencies[i];
            sampledValues[i] = source.response(f) + rlc.response(f);
        }
        return sampledValues;
    }

    private double[] sampleTargetHeadphone () {
        double[] sampledValues = new double[samples];
        for( int i = 0; i < samples; i++ ){
            double f = samplingFrequencies[i];
            sampledValues[i] = target.response(f);
        }
        return sampledValues;
    }

}
