
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

public class Resistor extends Element {

    private static double[] DECADES = {1, 10, 100, toUnit("1k"), toUnit("10k"), toUnit("100k"), toUnit("1M")};

    public static double[] E6_RESISTORS = applyDecades(E6, DECADES);
    public static double[] E12_RESISTORS = applyDecades(E12, DECADES);
    public static double[] E24_RESISTORS = applyDecades(E24, DECADES);
    public static double[] E48_RESISTORS = applyDecades(E48, DECADES);
    public static double[] E96_RESISTORS = applyDecades(E96, DECADES);
    public static double[] E192_RESISTORS = applyDecades(E192, DECADES);

    public final double R;

    public Resistor (double R) {
        this.R = R;
    }

    @Override
    public Complex impedance (double w) {
        return complex(R, 0);
    }

}
