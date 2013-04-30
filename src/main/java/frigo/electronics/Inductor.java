
package frigo.electronics;

import static frigo.electronics.IEC60063.E12;
import static frigo.electronics.IEC60063.E192;
import static frigo.electronics.IEC60063.E24;
import static frigo.electronics.IEC60063.E48;
import static frigo.electronics.IEC60063.E6;
import static frigo.electronics.IEC60063.E96;
import static frigo.electronics.IEC60063.applyDecades;
import static frigo.electronics.Prefix.toUnit;

public class Inductor {

    private static double[] DECADES = {toUnit("1n"), toUnit("10n"), toUnit("100n"), toUnit("1u"), toUnit("10u"),
        toUnit("100u"), toUnit("1m")};

    public static double[] E6_INDUCTORS = applyDecades(E6, DECADES);
    public static double[] E12_INDUCTORS = applyDecades(E12, DECADES);
    public static double[] E24_INDUCTORS = applyDecades(E24, DECADES);
    public static double[] E48_INDUCTORS = applyDecades(E48, DECADES);
    public static double[] E96_INDUCTORS = applyDecades(E96, DECADES);
    public static double[] E192_INDUCTORS = applyDecades(E192, DECADES);

}
