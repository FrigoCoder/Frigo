
package frigo.math;

import org.junit.Test;

public class DHTTest extends HartleyTransformTestBase {

    public DHTTest () {
        super(new DHT(), new FHT(), 1024, 1.0e-20);
    }

    @Test
    public void testDHTAgainstFHT () {
        testForwardAgainstOtherForward();
    }

    @Test
    public void testDHTAgainstIDHT () {
        testForwardAgainstInverse();
    }

    @Test
    public void testDHTAgainstIFHT () {
        testForwardAgainstOtherInverse();
    }

    @Test
    public void testDHTAgainstPureFrequency () {
        testForwardAgainstPureFrequency();
    }

    @Override
    @Test
    public void testFromFourier () {
        super.testFromFourier();
    }

    @Test
    public void testIDHTAgainstIFHT () {
        testInverseAgainstOtherInverse();
    }

    @Override
    @Test
    public void testToFourier () {
        super.testToFourier();
    }
}
