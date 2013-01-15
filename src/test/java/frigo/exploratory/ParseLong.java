
package frigo.exploratory;

import org.junit.Test;

public class ParseLong {

    @Test(expected = NumberFormatException.class)
    public void testLongParseLongFailsToMsisdn() {
        Long.parseLong("\"+36201234567\"");
    }

}
