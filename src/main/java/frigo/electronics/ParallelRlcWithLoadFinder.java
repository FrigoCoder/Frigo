
package frigo.electronics;

import static com.google.common.base.Preconditions.checkArgument;
import static frigo.electronics.Util.decibelToAmplitudeRatio;
import static frigo.electronics.Util.ordinaryToAngularFrequency;
import static frigo.math.MathAux.sqr;
import static java.lang.Math.sqrt;

public class ParallelRlcWithLoadFinder {

    private double f0;
    private double gain;
    private double q;
    private double load;

    double R;
    double LC;
    double f1;
    double f2;
    double gainAtMidPoint;

    public ParallelRlcWithLoadFinder (double f0, double gain, double q, double load) {
        checkArgument(gain < -3.0, "Sorry gain has to be at most -3dB");
        this.f0 = f0;
        this.gain = gain;
        this.q = q;
        this.load = load;
        R = (1.0 / decibelToAmplitudeRatio(gain) - 1.0) * load;
        LC = sqr(1.0 / ordinaryToAngularFrequency(f0));
        f1 = f0 * (sqrt(1 + 1 / (4 * q * q)) - 1 / (2 * q));
        f2 = f0 * (sqrt(1 + 1 / (4 * q * q)) + 1 / (2 * q));
        gainAtMidPoint = gain + 3.0;
    }

    public ParallelRlcWithLoad getFilter () {
        // double CperL = q * q / (R * load);
        // ParallelRlcWithLoad rlc = new ParallelRlcWithLoad(R, sqrt(LC / CperL), sqrt(LC * CperL), load);
        double L = R / (q * ordinaryToAngularFrequency(f0));

        ParallelRlcWithLoad rlc = new ParallelRlcWithLoad(R, L, LC / L, load);
        System.out.println("Expected gain at f0: " + gain);
        System.out.println("Actual gain at f0: " + rlc.response(f0));

        System.out.println("Expected gain at f1: " + gainAtMidPoint);
        System.out.println("Actual gain at f1: " + rlc.response(f1));
        System.out.println("Actaul gain at f2: " + rlc.response(f2));
        return rlc;

    }

    public static void main (String[] args) {
        ParallelRlcWithLoadFinder finder = new ParallelRlcWithLoadFinder(4800, -100, 0.3, 32);
        ParallelRlcWithLoad rlc = finder.getFilter();
        System.out.println(rlc);

    }

}
