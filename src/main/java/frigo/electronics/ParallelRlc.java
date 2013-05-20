
package frigo.electronics;

public class ParallelRlc extends Parallel {

    public final double R;
    public final double L;
    public final double C;

    public ParallelRlc (double R, double L, double C) {
        super(new Resistor(R), new Inductor(L), new Capacitor(C));
        this.R = R;
        this.L = L;
        this.C = C;
    }

    // public double q () {
    // double w0 = 1.0 / sqrt(L * C);
    // double X = w0 * L;
    // return R / X;
    // }

}
