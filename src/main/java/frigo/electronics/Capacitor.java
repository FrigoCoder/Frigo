
package frigo.electronics;

import static frigo.electronics.IEC60063.E12;
import static frigo.electronics.IEC60063.E192;
import static frigo.electronics.IEC60063.E24;
import static frigo.electronics.IEC60063.E48;
import static frigo.electronics.IEC60063.E6;
import static frigo.electronics.IEC60063.E96;
import static frigo.electronics.IEC60063.applyDecades;
import static frigo.electronics.Prefix.toUnit;
import static frigo.math.Complex.complex;
import frigo.math.Complex;

public class Capacitor extends Element {

    private static final double[] DECADES = {toUnit("10p"), toUnit("100p"), toUnit("1n"), toUnit("10n"),
        toUnit("100n"), toUnit("1u"), toUnit("10u")};

    public static final double[] E6_CAPACITORS = applyDecades(E6, DECADES);
    public static final double[] E12_CAPACITORS = applyDecades(E12, DECADES);
    public static final double[] E24_CAPACITORS = applyDecades(E24, DECADES);
    public static final double[] E48_CAPACITORS = applyDecades(E48, DECADES);
    public static final double[] E96_CAPACITORS = applyDecades(E96, DECADES);
    public static final double[] E192_CAPACITORS = applyDecades(E192, DECADES);

    public final double C;

    public Capacitor (double C) {
        this.C = C;
    }

    @Override
    public Complex impedance (double w) {
        return complex(0, -1.0 / (w * C));
    }

}
