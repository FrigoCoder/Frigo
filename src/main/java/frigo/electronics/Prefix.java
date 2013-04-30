
package frigo.electronics;

import static java.lang.Double.parseDouble;

import java.util.LinkedList;
import java.util.List;

public enum Prefix {

    TERA("T", 1_000_000_000_000.0),
    GIGA("G", 1_000_000_000),
    MEGA("M", 1_000_000),
    KILO("k", 1_000),
    HECTO("h", 100),
    UNIT("", 1),
    DECI("d", 0.1),
    CENTI("c", 0.01),
    MILLI("m", 0.001),
    MICRO("u", 0.000_001),
    NANO("n", 0.000_000_001),
    PICO("p", 0.000_000_000_001);

    public static Prefix prefix (String string) {
        List<Prefix> candidates = new LinkedList<>();
        for( Prefix prefix : values() ){
            if( string.endsWith(prefix.symbol) ){
                candidates.add(prefix);
            }
        }
        Prefix longest = UNIT;
        for( Prefix prefix : candidates ){
            if( longest.symbol.length() < prefix.symbol.length() ){
                longest = prefix;
            }
        }
        return longest;
    }

    public static double toUnit (String string) {
        Prefix prefix = prefix(string);
        String amountString = string.substring(0, string.length() - prefix.symbol.length());
        double amount = parseDouble(amountString);
        return prefix.toUnit(amount);
    }

    private double factor;
    private String symbol;

    private Prefix (String symbol, double factor) {
        this.factor = factor;
        this.symbol = symbol;
    }

    private double toUnit (double amount) {
        return amount * factor;
    }

}
