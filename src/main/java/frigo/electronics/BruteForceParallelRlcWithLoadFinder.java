
package frigo.electronics;

import static frigo.electronics.IEC60063.E192;
import static frigo.electronics.IEC60063.applyDecades;
import static frigo.electronics.Prefix.toUnit;
import static frigo.electronics.Util.qFactorToOctaveBandwidth;
import frigo.math.MathAux;

public class BruteForceParallelRlcWithLoadFinder {

    private double f0;
    private double gain;
    private double q;
    private double load;

    public static void main (String[] args) {
        BruteForceParallelRlcWithLoadFinder finder = new BruteForceParallelRlcWithLoadFinder(4800, -8, 0.5, 35);

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

    public BruteForceParallelRlcWithLoadFinder (double f0, double gain, double q, double load) {
        this.f0 = f0;
        this.gain = gain;
        this.q = q;
        this.load = load;
    }

    private double[] Rs = applyDecades(E192, new double[] {10});
    private double[] Ls =
        applyDecades(E192, new double[] {toUnit("100u"), toUnit("1m"), toUnit("10m"), toUnit("100m")});
    private double[] Cs = applyDecades(E192, new double[] {toUnit("1n"), toUnit("10n"), toUnit("100n"), toUnit("1m")});

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
        try{
            return d(rlc.f0(), f0) + d(rlc.gain(), gain) + d(rlc.q(), q);
        }catch( IllegalArgumentException e ){
            System.out.println("Encountered too small RLC");
            return Double.POSITIVE_INFINITY;
        }
    }

    private double d (double actual, double expected) {
        return MathAux.sqr((expected - actual) / expected);
    }

}
