package frigo.exploratory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class Java7Test {

    @Test
    public void testBinaryLiterals() {
        assertEquals((byte) 0b00100001, (byte) 0x21);
        assertEquals((short) 0B1010000101000101, (short) 0xa145);
        assertEquals(0b10100001010001011010000101000101, 0xa145a145);
        assertEquals(0B101, 0x5);
        assertEquals(0b1010000101000101101000010100010110100001010001011010000101000101L, 0xa145a145a145a145l);
    }

    @Test
    public void testUnderscoresInNumericLiterals() {
        assertEquals(1234_5678_9012_3456l, 1234567890123456l);
        assertEquals(999_99_9999l, 999999999l);
        assertEquals(3.14_15f, 3.1415f, 0.0);
        assertEquals(0xff_ec_de_5e, 0xffecde5e);
        assertEquals(0xCAFE_BABE, 0xcafebabe);
        assertEquals(0x7fff_ffff_ffff_ffffL, 0x7fffffffffffffffl);
        assertEquals((byte) 0b0010_0101, (byte) 0x25);
        assertEquals(0b11010010_01101001_10010100_10010010, 0xd2699492);
    }

    @Test
    public void testStringSwitch() {
        fail("Not yet implemented");
    }

}
