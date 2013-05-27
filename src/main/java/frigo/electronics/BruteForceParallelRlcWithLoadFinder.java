
package frigo.electronics;

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

    public ParallelRlcWithLoad getBestRlc () {
        ParallelRlcWithLoadFinder finder = new ParallelRlcWithLoadFinder(f0, gain, q, load);
        ParallelRlcWithLoad ideal = finder.getFilter();

        double[] Rs = {IEC60063.lower(ideal.R, IEC60063.E48), IEC60063.higher(ideal.R, IEC60063.E48)};
        double[] Ls = {IEC60063.lower(ideal.L, IEC60063.E48), IEC60063.higher(ideal.L, IEC60063.E48)};
        double[] Cs = {IEC60063.lower(ideal.C, IEC60063.E48), IEC60063.higher(ideal.C, IEC60063.E48)};

        Minimum<ParallelRlcWithLoad> minimum = new Minimum<>();
        for( double R : Rs ){
            for( double L : Ls ){
                for( double C : Cs ){
                    ParallelRlcWithLoad rlc = new ParallelRlcWithLoad(R, L, C, load);
                    double score = evaluate(rlc);
                    minimum.add(rlc, score);
                    log("Candidate: " + rlc);
                    log("Score: " + score);
                    log("");
                }
            }
        }
        return minimum.bestObject;
    }

    private double evaluate (ParallelRlcWithLoad rlc) {
        try{
            return d(rlc.f0(), f0) + d(rlc.gain(), gain) + d(rlc.q(), q);
        }catch( IllegalArgumentException e ){
            System.out.println("Gain should be < -3.0");
            return Double.POSITIVE_INFINITY;
        }
    }

    private double d (double actual, double expected) {
        return MathAux.sqr((expected - actual) / expected);
    }

}
