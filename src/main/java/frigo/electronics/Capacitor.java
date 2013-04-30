
package frigo.electronics;

import static frigo.electronics.IEC60063.E12;
import static frigo.electronics.IEC60063.E192;
import static frigo.electronics.IEC60063.E24;
import static frigo.electronics.IEC60063.E48;
import static frigo.electronics.IEC60063.E6;
import static frigo.electronics.IEC60063.E96;
import static frigo.electronics.IEC60063.applyDecades;
import static frigo.electronics.Prefix.toUnit;

public class Capacitor {

    private static double[] DECADES = {toUnit("10p"), toUnit("100p"), toUnit("1n"), toUnit("10n"), toUnit("100n"),
        toUnit("1u"), toUnit("10u")};

    public static double[] E6_CAPACITORS = applyDecades(E6, DECADES);
    public static double[] E12_CAPACITORS = applyDecades(E12, DECADES);
    public static double[] E24_CAPACITORS = applyDecades(E24, DECADES);
    public static double[] E48_CAPACITORS = applyDecades(E48, DECADES);
    public static double[] E96_CAPACITORS = applyDecades(E96, DECADES);
    public static double[] E192_CAPACITORS = applyDecades(E192, DECADES);

}
