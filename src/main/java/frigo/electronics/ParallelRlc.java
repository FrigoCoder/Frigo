
package frigo.electronics;

public class ParallelRlc extends Parallel {

    public ParallelRlc (double R, double L, double C) {
        super(new Resistor(R), new Inductor(L), new Capacitor(C));
    }

}
