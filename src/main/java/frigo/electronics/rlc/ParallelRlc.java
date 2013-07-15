
package frigo.electronics.rlc;

import frigo.electronics.component.Capacitor;
import frigo.electronics.component.Inductor;
import frigo.electronics.component.Parallel;
import frigo.electronics.component.Resistor;

public class ParallelRlc extends Parallel {

    public ParallelRlc (double R, double L, double C) {
        super(new Resistor(R), new Inductor(L), new Capacitor(C));
    }

}
