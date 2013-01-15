
package frigo.util;

import static frigo.util.MockitoAux.doReturnElements;
import static frigo.util.MockitoAux.doReturnValues;
import static frigo.util.MockitoAux.strictMock;
import static frigo.util.MockitoAux.verifyImplicit;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class MockitoAuxTest {

    private static class Originale {

        public int getSomething(int i) {
            return i;
        }

        public Object getObject() {
            return null;
        }

        public byte getByte() {
            return 0;
        }

        public short getShort() {
            return 0;
        }

        public int getInt() {
            return 0;
        }

        public long getLong() {
            return 0;
        }

        public float getFloat() {
            return 0;
        }

        public double getDouble() {
            return 0;
        }

        public boolean getBoolean() {
            return false;
        }

        public char getChar() {
            return 0x00;
        }

    }

    private Originale mock;

    @Before
    public void setUp() {
        mock = mock(Originale.class);
    }

    @Test
    public void strick_mock_behaves_like_a_mock_on_stubbed_invocation() {
        mock = strictMock(Originale.class);
        doReturn(2).when(mock).getInt();
        int one = mock.getInt();
        assertThat(one, is(2));
    }

    @Test(expected = UnstubbedInvocationInvoked.class)
    public void strict_mock_throws_exception_on_unstubbed_invocation() {
        mock = strictMock(Originale.class);
        mock.getInt();
    }

    @Test(expected = UnstubbedInvocationInvoked.class)
    public void strict_mock_throws_exception_if_stubbed_method_is_called_with_different_parameters() {
        mock = strictMock(Originale.class);
        doReturn(20).when(mock).getSomething(2);
        mock.getSomething(3);
    }

    @Test
    public void verifyImplicit_does_not_throw_if_stubbed_method_is_called() {
        doReturn(2).when(mock).getInt();
        mock.getInt();
        verifyImplicit(mock);
    }

    @Test(expected = ImplicitVerificationFailed.class)
    public void verifyImplicit_throws_if_stubbed_method_is_not_called() {
        doReturn(2).when(mock).getInt();
        verifyImplicit(mock);
    }

    @Test(expected = ImplicitVerificationFailed.class)
    public void verifyImplicit_throws_if_stubbed_method_is_called_with_different_parameters() {
        doReturn(20).when(mock).getSomething(2);
        mock.getSomething(3);
        verifyImplicit(mock);
    }

    @Test
    public void doReturnValues_for_objects() {
        Object[] values = {1, 2, 3};
        doReturnValues(values[0], values[1], values[2]).when(mock).getObject();
        assertThat(mock.getObject(), is(values[0]));
        assertThat(mock.getObject(), is(values[1]));
        assertThat(mock.getObject(), is(values[2]));
    }

    @Test
    public void doReturnValues_for_Integers() {
        Integer[] values = {1, 2, 3};
        doReturnValues(values[0], values[1], values[2]).when(mock).getInt();
        assertThat(mock.getInt(), is(values[0]));
        assertThat(mock.getInt(), is(values[1]));
        assertThat(mock.getInt(), is(values[2]));
    }

    @Test
    public void doReturnValues_for_ints() {
        int[] values = {1, 2, 3};
        doReturnValues(values[0], values[1], values[2]).when(mock).getInt();
        assertThat(mock.getInt(), is(values[0]));
        assertThat(mock.getInt(), is(values[1]));
        assertThat(mock.getInt(), is(values[2]));
    }

    @Test
    public void doReturnElements_for_Object_array() {
        Object[] values = {1, 2, 3};
        doReturnElements(values).when(mock).getObject();
        assertThat(mock.getObject(), is(values[0]));
        assertThat(mock.getObject(), is(values[1]));
        assertThat(mock.getObject(), is(values[2]));
    }

    @Test
    public void doReturnElements_for_byte_array() {
        byte[] values = {1, 2};
        doReturnElements(values).when(mock).getByte();
        assertThat(mock.getByte(), is(values[0]));
        assertThat(mock.getByte(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_Byte_array() {
        Byte[] values = {1, 2};
        doReturnElements(values).when(mock).getByte();
        assertThat(mock.getByte(), is(values[0]));
        assertThat(mock.getByte(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_short_array() {
        short[] values = {1, 2};
        doReturnElements(values).when(mock).getShort();
        assertThat(mock.getShort(), is(values[0]));
        assertThat(mock.getShort(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_Short_array() {
        Short[] values = {1, 2};
        doReturnElements(values).when(mock).getShort();
        assertThat(mock.getShort(), is(values[0]));
        assertThat(mock.getShort(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_int_array() {
        int[] values = {1, 2};
        doReturnElements(values).when(mock).getInt();
        assertThat(mock.getInt(), is(values[0]));
        assertThat(mock.getInt(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_Integer_array() {
        Integer[] values = {1, 2};
        doReturnElements(values).when(mock).getInt();
        assertThat(mock.getInt(), is(values[0]));
        assertThat(mock.getInt(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_long_array() {
        long[] values = {1, 2};
        doReturnElements(values).when(mock).getLong();
        assertThat(mock.getLong(), is(values[0]));
        assertThat(mock.getLong(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_Long_array() {
        Long[] values = {1L, 2L};
        doReturnElements(values).when(mock).getLong();
        assertThat(mock.getLong(), is(values[0]));
        assertThat(mock.getLong(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_float_array() {
        float[] values = {1, 2};
        doReturnElements(values).when(mock).getFloat();
        assertThat(mock.getFloat(), is(values[0]));
        assertThat(mock.getFloat(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_Float_array() {
        Float[] values = {1F, 2F};
        doReturnElements(values).when(mock).getFloat();
        assertThat(mock.getFloat(), is(values[0]));
        assertThat(mock.getFloat(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_double_array() {
        double[] values = {1, 2};
        doReturnElements(values).when(mock).getDouble();
        assertThat(mock.getDouble(), is(values[0]));
        assertThat(mock.getDouble(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_Double_array() {
        Double[] values = {1D, 2D};
        doReturnElements(values).when(mock).getDouble();
        assertThat(mock.getDouble(), is(values[0]));
        assertThat(mock.getDouble(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_boolean_array() {
        boolean[] values = {true, false};
        doReturnElements(values).when(mock).getBoolean();
        assertThat(mock.getBoolean(), is(values[0]));
        assertThat(mock.getBoolean(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_Boolean_array() {
        Boolean[] values = {true, false};
        doReturnElements(values).when(mock).getBoolean();
        assertThat(mock.getBoolean(), is(values[0]));
        assertThat(mock.getBoolean(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_char_array() {
        char[] values = {'a', 'b'};
        doReturnElements(values).when(mock).getChar();
        assertThat(mock.getChar(), is(values[0]));
        assertThat(mock.getChar(), is(values[1]));
    }

    @Test
    public void doReturnElements_for_Character_array() {
        Character[] values = {'a', 'b'};
        doReturnElements(values).when(mock).getChar();
        assertThat(mock.getChar(), is(values[0]));
        assertThat(mock.getChar(), is(values[1]));
    }

    // @Test(expected = ImplicitVerificationFailed.class)
    // public void verifyImplicit_throws_if_stubbed_method_is_called_less_than_expected () {
    // Originale mock = mock(Originale.class);
    // doReturn(2).doReturn(3).when(mock).getOne();
    // mock.getOne();
    // verifyImplicit(mock);
    // }

    // @Test
    // public void whatever() {
    // Originale mock = mock(Originale.class);
    // doReturn(13).when(mock).getSomething(intThat(greaterThanOrEqualTo(3)));
    // doReturn(12).when(mock).getSomething(intThat(greaterThanOrEqualTo(2)));
    // doReturn(11).when(mock).getSomething(intThat(greaterThanOrEqualTo(1)));
    // System.out.println(mock.getSomething(0));
    // System.out.println(mock.getSomething(1));
    // System.out.println(mock.getSomething(2));
    // System.out.println(mock.getSomething(3));
    // }

}
