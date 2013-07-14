
package frigo.electronics;

import static frigo.electronics.IEC60063.higher;
import static frigo.electronics.IEC60063.lower;
import static java.lang.Math.abs;
import static java.lang.Math.max;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Function;

public class BruteForceParallelRlcWithLoadFinder {

    public static void main (String[] args) {
        dump(4800, -8, 0.5, 35, IEC60063.E6);
        dump(4800, -8, 0.5, 35, IEC60063.E12);
        dump(4800, -8, 0.5, 35, IEC60063.E24);
        dump(4800, -8, 0.5, 35, IEC60063.E48);
        dump(4800, -8, 0.5, 35, IEC60063.E96);
        dump(4800, -8, 0.5, 35, IEC60063.E192);
    }

    private static void dump (int f0, int gain, double q, int load, double[] tolerance) {
        new BruteForceParallelRlcWithLoadFinder(f0, gain, q, load, tolerance).run();
    }

    private ParallelRlcWithLoad ideal;
    private List<ParallelRlcWithLoad> candidates;

    private BruteForceParallelRlcWithLoadFinder (double f0, double gain, double q, double load, double[] tolerance) {
        ideal = new ParallelRlcWithLoadFinder(f0, gain, q, load).getFilter();
        candidates = candidates(load, tolerance);
    }

    private List<ParallelRlcWithLoad> candidates (double load, double[] tolerance) {
        List<ParallelRlcWithLoad> result = new LinkedList<>();
        double[] Rs = {lower(ideal.R, tolerance), higher(ideal.R, tolerance)};
        double[] Ls = {lower(ideal.L, tolerance), higher(ideal.L, tolerance)};
        double[] Cs = {lower(ideal.C, tolerance), higher(ideal.C, tolerance)};
        for( double R : Rs ){
            for( double L : Ls ){
                for( double C : Cs ){
                    ParallelRlcWithLoad rlc = new ParallelRlcWithLoad(R, L, C, load);
                    result.add(rlc);
                }
            }
        }
        return result;
    }

    private void run () {
        log("----");
        log("Ideal: " + ideal);
        for( ParallelRlcWithLoad rlc : candidates ){
            log("Candidate: " + rlc);
        }
        log("Abs best: " + getBest(evaluateAbs));
        log("Max best: " + getBest(evaluateMax));
    }

    private void log (String message) {
        System.out.println(message);
    }

    private ParallelRlcWithLoad getBest (Function<ParallelRlcWithLoad, Double> evaluator) {
        Minimum<ParallelRlcWithLoad> minimum = new Minimum<>();
        for( ParallelRlcWithLoad rlc : candidates ){
            double score = evaluator.apply(rlc);
            minimum.add(rlc, score);
        }
        return minimum.bestObject;
    }

    private Function<ParallelRlcWithLoad, Double> evaluateAbs = new Function<ParallelRlcWithLoad, Double>() {

        @Override
        public Double apply (ParallelRlcWithLoad rlc) {
            double distortion = 0.0;
            for( double f = 1.0; f < 22100.0; f += 1.0 ){
                distortion += abs(ideal.response(f) - rlc.response(f));
            }
            return distortion;
        }
    };

    private Function<ParallelRlcWithLoad, Double> evaluateMax = new Function<ParallelRlcWithLoad, Double>() {

        @Override
        public Double apply (ParallelRlcWithLoad rlc) {
            double distortion = 0.0;
            for( double f = 1.0; f < 22100.0; f += 1.0 ){
                distortion = max(distortion, ideal.response(f) - rlc.response(f));
            }
            return distortion;
        }
    };

}
