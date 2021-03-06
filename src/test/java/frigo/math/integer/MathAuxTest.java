
package frigo.math.integer;

import static frigo.math.Complex.complex;
import static frigo.math.integer.MathAux.ceil;
import static frigo.math.integer.MathAux.floor;
import static frigo.math.integer.MathAux.sinc;
import static frigo.math.integer.MathAux.sqr;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MathAuxTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private double epsilon = 1.0e-15;
    private float epsilonFloat = (float) 1.0e-15;

    @Test
    public void testSincDouble () {
        assertThat(sinc(-2.0), closeTo(0.0, epsilon));
        assertThat(sinc(-1.0), closeTo(0.0, epsilon));
        assertThat(sinc(0.0), closeTo(1.0, epsilon));
        assertThat(sinc(1.0), closeTo(0.0, epsilon));
        assertThat(sinc(2.0), closeTo(0.0, epsilon));
        assertThat(sinc(-1.5), closeTo(-0.21220659078919378102517835116335, epsilon));
        assertThat(sinc(-0.5), closeTo(0.63661977236758134307553505349004, epsilon));
        assertThat(sinc(0.5), closeTo(0.63661977236758134307553505349004, epsilon));
        assertThat(sinc(1.5), closeTo(-0.21220659078919378102517835116335, epsilon));
    }

    @Test
    public void testSincFloat () {
        assertThat((double) sinc(-2.0f), closeTo(0.0f, epsilonFloat));
        assertThat((double) sinc(-1.0f), closeTo(0.0f, epsilonFloat));
        assertThat((double) sinc(0.0f), closeTo(1.0f, epsilonFloat));
        assertThat((double) sinc(1.0f), closeTo(0.0f, epsilonFloat));
        assertThat((double) sinc(2.0f), closeTo(0.0f, epsilonFloat));
        assertThat((double) sinc(-1.5f), closeTo(-0.21220659078919378102517835116335f, epsilonFloat));
        assertThat((double) sinc(-0.5f), closeTo(0.63661977236758134307553505349004f, epsilonFloat));
        assertThat((double) sinc(0.5f), closeTo(0.63661977236758134307553505349004f, epsilonFloat));
        assertThat((double) sinc(1.5f), closeTo(-0.21220659078919378102517835116335f, epsilonFloat));
    }

    @Test
    public void testSqrComplex () {
        assertThat(complex(-3.0, 4.0), is(sqr(complex(1.0, 2.0))));
    }

    @Test
    public void testSqrDouble () {
        assertThat(sqr(1.0), closeTo(1.0, epsilon));
        assertThat(sqr(2.0), closeTo(4.0, epsilon));
        assertThat(sqr(3.0), closeTo(9.0, epsilon));
        assertThat(sqr(4.5), closeTo(20.25, epsilon));
    }

    @Test
    public void testSqrFloat () {
        assertThat((double) sqr(1.0f), closeTo(1.0f, epsilonFloat));
        assertThat((double) sqr(2.0f), closeTo(4.0f, epsilonFloat));
        assertThat((double) sqr(3.0f), closeTo(9.0f, epsilonFloat));
        assertThat((double) sqr(4.5f), closeTo(20.25f, epsilonFloat));
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
