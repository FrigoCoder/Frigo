
package frigo.electronics;

import static frigo.electronics.Util.ordinaryToAngularFrequency;
import static frigo.electronics.Util.powerRatioToDecibel;
import static frigo.math.Complex.div;
import frigo.math.Complex;

public class ParallelRlcWithLoad {

    private ParallelRlc rlc;
    private double Rload;

    public ParallelRlcWithLoad (double R, double L, double C, double Rload) {
        rlc = new ParallelRlc(R, L, C);
        this.Rload = Rload;
    }

    public double response (double f) {
        return powerRatioToDecibel(transfer(f).sqrAbs());
    }

    private Complex transfer (double f) {
        double w = ordinaryToAngularFrequency(f);
        return div(Rload, rlc.impedance(w).add(Rload));
    }
}
