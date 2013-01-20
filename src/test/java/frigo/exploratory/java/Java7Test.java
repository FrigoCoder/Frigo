
package frigo.exploratory.java;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class Java7Test {

    @Test
    public void binary_literals () {
        assertThat((byte) 0b00100001, is((byte) 0x21));
        assertThat((short) 0B1010000101000101, is((short) 0xa145));
        assertThat(0b10100001010001011010000101000101, is(0xa145a145));
        assertThat(0B101, is(0x5));
        assertThat(0b1010000101000101101000010100010110100001010001011010000101000101L, is(0xa145a145a145a145l));
    }

    @Test
    public void underscores_in_numeric_literals () {
        assertThat(1234_5678_9012_3456l, is(1234567890123456l));
        assertThat(999_99_9999l, is(999999999l));
        assertThat(3.14_15f, is(3.1415f));
        assertThat(0xff_ec_de_5e, is(0xffecde5e));
        assertThat(0xCAFE_BABE, is(0xcafebabe));
        assertThat(0x7fff_ffff_ffff_ffffL, is(0x7fffffffffffffffl));
        assertThat((byte) 0b0010_0101, is((byte) 0x25));
        assertThat(0b11010010_01101001_10010100_10010010, is(0xd2699492));
    }

    @Test
    public void string_switch () {
        String string = "something";
        switch( string ){
            case "something else":
                fail();
                break;
            case "something":
                break;
            case "another thing":
            case "something completely different":
                fail();
                break;
            default:
                fail();
                return;
        }
    }

}
