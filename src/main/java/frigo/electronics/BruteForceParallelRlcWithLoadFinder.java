
package frigo.electronics;

import static frigo.electronics.IEC60063.higher;
import static frigo.electronics.IEC60063.lower;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import frigo.math.MathAux;

public class BruteForceParallelRlcWithLoadFinder {

    private static final Logger logger = LoggerFactory.getLogger(BruteForceParallelRlcWithLoadFinder.class);

    private double f0;
    private double gain;
    private double q;
    private double load;
    private double[] tolerance;

    public static void main (String[] args) {
        dump(4800, -8, 0.5, 35, IEC60063.E6);
        dump(4800, -8, 0.5, 35, IEC60063.E12);
        dump(4800, -8, 0.5, 35, IEC60063.E24);
        dump(4800, -8, 0.5, 35, IEC60063.E48);
        dump(4800, -8, 0.5, 35, IEC60063.E96);
        dump(4800, -8, 0.5, 35, IEC60063.E192);
    }

    private static void dump (int f0, int gain, double q, int load, double[] tolerance) {
        ParallelRlcWithLoad rlc = new BruteForceParallelRlcWithLoadFinder(f0, gain, q, load, tolerance).getBestRlc();
        logger.info("f0 = " + rlc.f0 + " Hz, gain = " + rlc.gain + " dB, Q = " + rlc.q() + ", OBW = " + rlc.obw
            + ", R = " + rlc.R + " ohm , L = " + rlc.L * 1_000 + " mH, C = " + rlc.C * 1_000_000_000 + " nF");
    }

    public BruteForceParallelRlcWithLoadFinder (double f0, double gain, double q, double load, double[] tolerance) {
        this.f0 = f0;
        this.gain = gain;
        this.q = q;
        this.load = load;
        this.tolerance = tolerance;
    }

    public ParallelRlcWithLoad getBestRlc () {
        ParallelRlcWithLoadFinder finder = new ParallelRlcWithLoadFinder(f0, gain, q, load);
        ParallelRlcWithLoad ideal = finder.getFilter();

        double[] Rs = {lower(ideal.R, tolerance), higher(ideal.R, tolerance)};
        double[] Ls = {lower(ideal.L, tolerance), higher(ideal.L, tolerance)};
        double[] Cs = {lower(ideal.C, tolerance), higher(ideal.C, tolerance)};

        Minimum<ParallelRlcWithLoad> minimum = new Minimum<>();
        for( double R : Rs ){
            for( double L : Ls ){
                for( double C : Cs ){
                    ParallelRlcWithLoad rlc = new ParallelRlcWithLoad(R, L, C, load);
                    double score = evaluate(rlc);
                    minimum.add(rlc, score);
                }
            }
        }
        return minimum.bestObject;
    }

    private double evaluate (ParallelRlcWithLoad rlc) {
        try{
            return d(rlc.f0, f0) + d(rlc.gain, gain) + d(rlc.q(), q);
        }catch( IllegalArgumentException e ){
            System.out.println("Gain should be < -3.0");
            return Double.POSITIVE_INFINITY;
        }
    }

    private double d (double actual, double expected) {
        return MathAux.sqr((expected - actual) / expected);
    }

}
