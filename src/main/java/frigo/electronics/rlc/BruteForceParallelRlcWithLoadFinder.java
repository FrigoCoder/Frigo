
package frigo.electronics.rlc;

import static frigo.electronics.component.IEC60063.higher;
import static frigo.electronics.component.IEC60063.lower;
import static java.lang.Math.abs;
import static java.lang.Math.max;

import java.util.LinkedList;
import java.util.List;

import frigo.electronics.component.IEC60063;
import frigo.util.Minimum;

public class BruteForceParallelRlcWithLoadFinder {

    public static void main (String[] args) {
        dump(4800, -8, 0.44, 35, IEC60063.E24);
        dump(4800, -8, 0.47, 35, IEC60063.E24);
        dump(4800, -8, 0.44, 35, IEC60063.E12);
    }

    private static void dump (int f0, int gain, double q, int load, double[] tolerance) {
        new BruteForceParallelRlcWithLoadFinder(f0, gain, q, load, tolerance).run();
    }

    private ParallelRlcWithLoad ideal;
    private double[] Rs;
    private double[] Ls;
    private double[] Cs;
    private List<ParallelRlcWithLoad> candidates = new LinkedList<>();

    private BruteForceParallelRlcWithLoadFinder (double f0, double gain, double q, double load, double[] tolerance) {
        ideal = new ParallelRlcWithLoadFinder(f0, gain, q, load).getFilter();
        Rs = new double[] {lower(ideal.R, tolerance), higher(ideal.R, tolerance)};
        Ls = new double[] {lower(ideal.L, tolerance), higher(ideal.L, tolerance)};
        Cs = new double[] {lower(ideal.C, tolerance), higher(ideal.C, tolerance)};
        for( double R : Rs ){
            for( double L : Ls ){
                for( double C : Cs ){
                    candidates.add(new ParallelRlcWithLoad(R, L, C, load));
                }
            }
        }
    }

    private void run () {
        log("----");
        log("Ideal: " + ideal);
        log("Best: " + getBest());
    }

    private void log (String message) {
        System.out.println(message);
    }

    private ParallelRlcWithLoad getBest () {
        Minimum<ParallelRlcWithLoad> minimum = new Minimum<>();
        for( ParallelRlcWithLoad rlc : candidates ){
            double score = evaluate(rlc);
            minimum.add(rlc, score);
        }
        return minimum.bestObject;
    }

    private double evaluate (ParallelRlcWithLoad rlc) {
        double distortion = 0.0;
        for( double f = 1.0; f < 22100.0; f += 1 ){
            distortion = max(distortion, abs(ideal.response(f) - rlc.response(f)));
        }
        return distortion;
    }

}
