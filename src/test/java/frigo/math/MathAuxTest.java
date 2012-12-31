
package frigo.math;

import static frigo.math.Complex.complex;
import static frigo.math.MathAux.ceil;
import static frigo.math.MathAux.floor;
import static frigo.math.MathAux.sinc;
import static frigo.math.MathAux.sqr;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class MathAuxTest {

    public double epsilon;
    public float epsilonFloat;

    @Before
    public void setUp () {
        epsilon = 1.0e-15;
        epsilonFloat = (float) epsilon;
    }

    @Test
    public void testSincDouble () {
        assertEquals(0.0, sinc(-2.0), epsilon);
        assertEquals(0.0, sinc(-1.0), epsilon);
        assertEquals(1.0, sinc(0.0), epsilon);
        assertEquals(0.0, sinc(1.0), epsilon);
        assertEquals(0.0, sinc(2.0), epsilon);
        assertEquals(-0.21220659078919378102517835116335, sinc(-1.5), epsilon);
        assertEquals(0.63661977236758134307553505349004, sinc(-0.5), epsilon);
        assertEquals(0.63661977236758134307553505349004, sinc(0.5), epsilon);
        assertEquals(-0.21220659078919378102517835116335, sinc(1.5), epsilon);
    }

    @Test
    public void testSincFloat () {
        assertEquals(0.0f, sinc(-2.0f), epsilonFloat);
        assertEquals(0.0f, sinc(-1.0f), epsilonFloat);
        assertEquals(1.0f, sinc(0.0f), epsilonFloat);
        assertEquals(0.0f, sinc(1.0f), epsilonFloat);
        assertEquals(0.0f, sinc(2.0f), epsilonFloat);
        assertEquals(-0.21220659078919378102517835116335f, sinc(-1.5f), epsilonFloat);
        assertEquals(0.63661977236758134307553505349004f, sinc(-0.5f), epsilonFloat);
        assertEquals(0.63661977236758134307553505349004f, sinc(0.5f), epsilonFloat);
        assertEquals(-0.21220659078919378102517835116335f, sinc(1.5f), epsilonFloat);
    }

    @Test
    public void testSqrComplex () {
        assertThat(complex(-3.0, 4.0), is(sqr(complex(1.0, 2.0))));
    }

    @Test
    public void testSqrDouble () {
        assertEquals(1.0, sqr(1.0), epsilon);
        assertEquals(4.0, sqr(2.0), epsilon);
        assertEquals(9.0, sqr(3.0), epsilon);
        assertEquals(20.25, sqr(4.5), epsilon);
    }

    @Test
    public void testSqrFloat () {
        assertEquals(1.0f, sqr(1.0f), epsilonFloat);
        assertEquals(4.0f, sqr(2.0f), epsilonFloat);
        assertEquals(9.0f, sqr(3.0f), epsilonFloat);
        assertEquals(20.25f, sqr(4.5f), epsilonFloat);
    }

    @Test
    public void testSqrInt () {
        assertThat(1l, is(sqr(1)));
        assertThat(4l, is(sqr(2)));
        assertThat(9l, is(sqr(3)));
    }

    @Test
    public void testCeil () {
        assertThat(ceil(-1.0), is(-1));
        assertThat(ceil(-0.9), is(0));
        assertThat(ceil(0.0), is(0));
        assertThat(ceil(0.1), is(1));
        assertThat(ceil(0.9), is(1));
        assertThat(ceil(1.0), is(1));
    }

    @Test
    public void testFloor () {
        assertThat(floor(-1.0), is(-1));
        assertThat(floor(-0.9), is(-1));
        assertThat(floor(0.0), is(0));
        assertThat(floor(0.1), is(0));
        assertThat(floor(0.9), is(0));
        assertThat(floor(1.0), is(1));
    }

}
