
package frigo.electronics;

import static frigo.electronics.Prefix.CENTI;
import static frigo.electronics.Prefix.DECI;
import static frigo.electronics.Prefix.GIGA;
import static frigo.electronics.Prefix.HECTO;
import static frigo.electronics.Prefix.KILO;
import static frigo.electronics.Prefix.MEGA;
import static frigo.electronics.Prefix.MICRO;
import static frigo.electronics.Prefix.MILLI;
import static frigo.electronics.Prefix.NANO;
import static frigo.electronics.Prefix.TERA;
import static frigo.electronics.Prefix.UNIT;
import static frigo.electronics.Prefix.prefix;
import static frigo.electronics.Prefix.toUnit;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PrefixTest {

    @Test
    public void all_prefix_symbols_are_recognized () {
        assertThat(prefix("1T"), is(TERA));
        assertThat(prefix("2G"), is(GIGA));
        assertThat(prefix("3M"), is(MEGA));
        assertThat(prefix("4k"), is(KILO));
        assertThat(prefix("5h"), is(HECTO));
        assertThat(prefix("6"), is(UNIT));
        assertThat(prefix("7d"), is(DECI));
        assertThat(prefix("8c"), is(CENTI));
        assertThat(prefix("9m"), is(MILLI));
        assertThat(prefix("0u"), is(MICRO));
        assertThat(prefix("2n"), is(NANO));
    }

    @Test
    public void toUnit_works_for_all_prefixes () {
        assertThat(toUnit("1T"), is(toUnit("1000G")));
        assertThat(toUnit("1G"), is(toUnit("1000M")));
        assertThat(toUnit("1M"), is(toUnit("1000k")));
        assertThat(toUnit("1k"), is(toUnit("10h")));
        assertThat(toUnit("1h"), is(100.0));
        assertThat(toUnit("1"), is(toUnit("10d")));
        assertThat(toUnit("1d"), is(toUnit("10c")));
        assertThat(toUnit("1c"), is(toUnit("10m")));
        assertThat(toUnit("1m"), is(toUnit("1000u")));
        assertThat(toUnit("1m"), is(toUnit("1000000n")));

    }
}
