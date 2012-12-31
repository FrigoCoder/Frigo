
package frigo.math;

import org.junit.Test;

public class FHTTest extends HartleyTransformTestBase {

    public FHTTest () {
        super(new FHT(), new DHT(), 1024, 1.0e-20);
    }

    @Test
    public void testFHTAgainstDHT () {
        testForwardAgainstOtherForward();
    }

    @Test
    public void testFHTAgainstIDHT () {
        testForwardAgainstOtherInverse();
    }

    @Test
    public void testFHTAgainstIFHT () {
        testForwardAgainstInverse();
    }

    @Test
    public void testFHTAgainstPureFrequency () {
        testForwardAgainstPureFrequency();
    }

    @Override
    @Test
    public void testFromFourier () {
        super.testFromFourier();
    }

    @Test
    public void testIFHTAgainstIDHT () {
        testInverseAgainstOtherInverse();
    }

    @Override
    @Test
    public void testToFourier () {
        super.testToFourier();
    }
}
