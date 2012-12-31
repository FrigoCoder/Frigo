
package frigo.math;

import static frigo.math.Complex.add;
import static frigo.math.Complex.cis;
import static frigo.math.Complex.complex;
import static frigo.math.Complex.div;
import static frigo.math.Complex.i;
import static frigo.math.Complex.mul;
import static frigo.math.Complex.pow;
import static frigo.math.Complex.sub;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ComplexTest {

    private static final double EPSILON = 0.00000000000001;

    @Before
    public void setUp () {
        Complex.epsilon = EPSILON;
    }

    @Test
    public void testAbs () {
        assertEquals(complex(1.0, 0.0).abs(), 1.0, EPSILON);
        assertEquals(complex(0.0, 1.0).abs(), 1.0, EPSILON);
        assertEquals(complex(1.0, 1.0).abs(), Math.sqrt(2.0), EPSILON);
        assertEquals(complex(Math.sqrt(2.0), -Math.sqrt(2.0)).abs(), 2.0, EPSILON);
    }

    @Test
    public void testAddComplex () {
        assertEquals(complex(1.0, -1.0).add(complex(-1.0, 1.0)), complex(0.0, 0.0));
        assertEquals(complex(1.0, 2.0).add(complex(3.0, 4.0)), complex(4.0, 6.0));
    }

    @Test
    public void testAddDouble () {
        assertEquals(complex(1.0, -1.0).add(1.0), complex(2.0, -1.0));
        assertEquals(complex(-1.0, 0.0).add(1.0), complex(0.0, 0.0));
    }

    @Test
    public void testArg () {
        assertEquals(cis(0.1).arg(), 0.1, EPSILON);
        assertEquals(cis(0.1).mul(2.0).arg(), 0.1, EPSILON);
        assertEquals(cis(-0.2).mul(0.1).arg(), -0.2, EPSILON);
    }

    @Test
    public void testCis () {
        assertEquals(complex(0.0, 0.0).cis(), cis(0.0));
        assertEquals(complex(3.0, 0.0).cis(), cis(3.0));
        assertEquals(complex(1.0, 2.0).cis(),
                     complex(1.0, 2.0).sin().mul(complex(0.0, 1.0)).add(complex(1.0, 2.0).cos()));
        assertEquals(complex(2.0, 1.0).cis(),
                     complex(2.0, 1.0).sin().mul(complex(0.0, 1.0)).add(complex(2.0, 1.0).cos()));
    }

    @Test
    public void testConj () {
        assertEquals(complex(1.0, 1.0).conj(), complex(1.0, -1.0));
        assertEquals(complex(2.0, 1.0).conj(), complex(2.0, -1.0));
    }

    @Test
    public void testConstructors () {
        assertEquals(complex(), complex(0.0));
        assertEquals(complex(0.0), complex(0.0, 0.0));
        assertEquals(complex(1.0), complex(1.0, 0.0));
        assertEquals(complex(-1.0), complex(-1.0, 0.0));
    }

    @Test
    public void testCos () {
        assertEquals(complex(1.0, 0.0).cos(), complex(Math.cos(1.0), 0.0));
        assertEquals(complex(2.0, 0.0).cos(), complex(Math.cos(2.0), 0.0));
        assertEquals(complex(1.0, 2.0).cos(), complex(1.0, 2.0).mul(i).cosh());
        assertEquals(complex(2.0, 1.0).cos(), complex(2.0, 1.0).mul(i).cosh());
    }

    @Test
    public void testCosh () {
        assertEquals(complex(1.0, 0.0).cosh(), complex((Math.exp(1.0) + Math.exp(-1.0)) / 2.0, 0.0));
        assertEquals(complex(3.0, 0.0).cosh(), complex((Math.exp(3.0) + Math.exp(-3.0)) / 2.0, 0.0));
        assertEquals(complex(1.0, 2.0).cosh(), complex(1.0, 2.0).exp().add(complex(1.0, 2.0).negate().exp()).mul(0.5));
        assertEquals(complex(2.0, 1.0).cosh(), complex(2.0, 1.0).exp().add(complex(2.0, 1.0).negate().exp()).mul(0.5));
    }

    @Test
    public void testDivComplex () {
        assertEquals(complex(1.0, 2.0).div(complex(1.0, 2.0)), complex(1.0, 0.0));
        assertEquals(complex(2.0, 1.0).div(complex(2.0, 1.0)), complex(1.0, 0.0));
        assertEquals(complex(2.0, 4.0).div(complex(1.0, 2.0)), complex(2.0, 0.0));
        double a = 1.0, b = 2.0;
        double x = 3.0, y = 4.0;
        assertEquals(complex(a, b).div(complex(x, y)), complex(a * x + b * y, b * x - a * y).div(x * x + y * y));
    }

    @Test
    public void testDivDouble () {
        assertEquals(complex(1.0, 2.0).div(0.5), complex(2.0, 4.0));
        assertEquals(complex(3.0, 1.0).div(2.0), complex(3.0 / 2.0, 1.0 / 2.0));
    }

    @Test
    public void testEquals () {
        assertThat(complex(0.0, 0.0), not(new Object()));
        assertThat(complex(0.0, 0.0).equals(null), is(false));
    }

    @Test
    public void testExp () {
        assertEquals(complex(1.0, 0.0).exp(), complex(Math.exp(1.0), 0.0));
        assertEquals(complex(3.0, 0.0).exp(), complex(Math.exp(3.0), 0.0));
        assertEquals(complex(2.0, 3.0).exp(), cis(3.0).mul(Math.exp(2.0)));
        assertEquals(complex(1.0, 2.0).exp().log(), complex(1.0, 2.0));
    }

    @Test
    public void testHashCode () {
        assertThat(complex(0.0, 0.0).hashCode(), not(complex(0.0, 1.0).hashCode()));
    }

    @Test
    public void testInv () {
        assertEquals(complex(1.0, 2.0).mul(complex(2.0, 1.0)).mul(complex(2.0, 1.0).inv()), complex(1.0, 2.0));
        assertEquals(complex(1.0, 2.0).inv().mul(complex(1.0, 2.0)), complex(1.0, 0.0));
    }

    @Test
    public void testLog () {
        assertEquals(complex(1.0, 2.0).log().exp(), complex(1.0, 2.0));
    }

    @Test
    public void testMulComplex () {
        assertEquals(complex(1.0, 2.0).mul(complex(3.0, 4.0)), complex(-5.0, 10.0));
        assertEquals(complex(2.0, 3.0).mul(complex(4.0, 5.0)), complex(-7.0, 22.0));
    }

    @Test
    public void testMulDouble () {
        assertEquals(complex(1.0, 2.0).mul(2.0), complex(2.0, 4.0));
        assertEquals(complex(2.0, 3.0).mul(4.0), complex(8.0, 12.0));
    }

    @Test
    public void testNegate () {
        assertEquals(complex(1.0, 2.0).negate(), complex(-1.0, -2.0));
        assertEquals(complex(2.0, 3.0).negate(), complex(-2.0, -3.0));
    }

    @Test
    public void testNorm () {
        assertEquals(cis(0.1).sqrAbs(), 1.0, EPSILON);
        assertEquals(cis(3.0).sqrAbs(), 1.0, EPSILON);
    }

    @Test
    public void testPowComplex () {
        assertEquals(complex(2.0, 0.0).pow(complex(4.0, 0.0)), complex(16.0, 0.0));
        assertEquals(complex(2.0, 1.0).pow(complex(2.0, 0.0)), complex(3.0, 4.0));
        assertEquals(complex(3.0, 4.0).pow(complex(0.0, 1.0)), complex(3.0, 4.0).log().mul(i).exp());
    }

    @Test
    public void testPowDouble () {
        assertEquals(complex(2.0, 0.0).pow(2.0), complex(4.0, 0.0));
        assertEquals(complex(2.0, 0.0).pow(0.5), complex(Math.sqrt(2.0), 0.0));
        double abs = 10.0, phi = 0.3;
        assertEquals(cis(phi).mul(abs).pow(0.5), cis(phi / 2.0).mul(Math.sqrt(abs)));
    }

    @Test
    public void testSin () {
        assertEquals(complex(1.0, 0.0).sin(), complex(Math.sin(1.0), 0.0));
        assertEquals(complex(2.0, 0.0).sin(), complex(Math.sin(2.0), 0.0));
        assertEquals(complex(1.0, 2.0).sin(), complex(1.0, 2.0).mul(i).sinh().div(i));
        assertEquals(complex(2.0, 1.0).sin(), complex(2.0, 1.0).mul(i).sinh().div(i));
    }

    @Test
    public void testSinh () {
        assertEquals(complex(1.0, 0.0).sinh(), complex((Math.exp(1.0) - Math.exp(-1.0)) / 2.0, 0.0));
        assertEquals(complex(3.0, 0.0).sinh(), complex((Math.exp(3.0) - Math.exp(-3.0)) / 2.0, 0.0));
        assertEquals(complex(1.0, 2.0).sinh(), complex(1.0, 2.0).exp().sub(complex(1.0, 2.0).negate().exp()).mul(0.5));
        assertEquals(complex(2.0, 1.0).sinh(), complex(2.0, 1.0).exp().sub(complex(2.0, 1.0).negate().exp()).mul(0.5));
    }

    @Test
    public void testSqr () {
        assertEquals(complex(2.0, 0.0).sqr(), complex(4.0, 0.0));
        assertEquals(complex(3.0, 0.0).sqr(), complex(9.0, 0.0));
        assertEquals(complex(1.0, 2.0).sqr(), complex(1.0, 2.0).pow(2.0));
        assertEquals(complex(2.0, 3.0).sqr(), complex(2.0, 3.0).pow(2.0));
    }

    @Test
    public void testSqrt () {
        assertEquals(complex(4.0, 0.0).sqrt(), complex(2.0, 0.0));
        assertEquals(complex(-1.0, 0.0).sqrt(), complex(0.0, 1.0));
        assertEquals(complex(2.0, 3.0).sqrt().sqr(), complex(2.0, 3.0));
    }

    @Test
    public void testStaticAdd () {
        assertEquals(add(1.0, complex(2.0, 3.0)), complex(3.0, 3.0));
        assertEquals(add(-1.0, complex(0.0, 1.0)), complex(-1.0, 1.0));
    }

    @Test
    public void testStaticCis () {
        assertEquals(cis(0.1), complex(Math.cos(0.1), Math.sin(0.1)));
        assertEquals(cis(3.0), complex(Math.cos(3.0), Math.sin(3.0)));
    }

    @Test
    public void testStaticComplex () {
        assertEquals(complex(), complex(0.0));
        assertEquals(complex(0.1), complex(0.1, 0.0));
        assertEquals(complex(3.0), complex(3.0, 0.0));
        assertEquals(complex(3.0, 2.0), new Complex(3.0, 2.0));
        assertEquals(complex(2.0, 3.0), new Complex(2.0, 3.0));
    }

    @Test
    public void testStaticDiv () {
        assertEquals(div(1.0, complex(2.0, 3.0)), complex(2.0, 3.0).inv().mul(1.0));
        assertEquals(div(1.0, complex(2.0, 3.0)).mul(complex(2.0, 3.0)), complex(1.0, 0.0));
    }

    @Test
    public void testStaticMul () {
        assertEquals(mul(2.0, complex(3.0, 4.0)), complex(3.0, 4.0).mul(2.0));
        assertEquals(mul(2.0, complex(3.0, 4.0)).div(complex(3.0, 4.0)), complex(2.0, 0.0));
    }

    @Test
    public void testStaticPow () {
        assertEquals(pow(2.0, complex(3.0, 4.0)), complex(2.0, 0.0).pow(complex(3.0, 4.0)));
        assertEquals(pow(3.0, complex(4.0, 5.0)), complex(3.0, 0.0).pow(complex(4.0, 5.0)));
        assertEquals(pow(2.0, complex(3.0, 4.0)).pow(complex(3.0, 4.0).inv()), complex(2.0, 0.0));
        assertEquals(pow(1.0, complex(2.0, 3.0)).pow(complex(2.0, 3.0).inv()), complex(1.0, 0.0));
    }

    @Test
    public void testStaticSub () {
        assertEquals(sub(2.0, complex(3.0, 4.0)), complex(-1.0, -4.0));
        assertEquals(sub(3.0, complex(4.0, 5.0)), complex(-1.0, -5.0));
    }

    @Test
    public void testSubComplex () {
        assertEquals(complex(1.0, 0.0).sub(complex(0.0, 1.0)), complex(1.0, -1.0));
        assertEquals(complex(1.0, 1.0).sub(complex(0.0, 1.0)), complex(1.0, 0.0));
        assertEquals(complex(0.0, 1.0).sub(i), complex(0.0));
    }

    @Test
    public void testSubDouble () {
        assertEquals(complex(1.0, 1.0).sub(1.0), i);
        assertEquals(complex(16.0, 1.0).sub(8.5), complex(7.5, 1.0));
    }

    @Test
    public void testToString () {
        assertEquals(String.format("%20.16f", 1.0) + String.format("%+20.16f", 2.0) + "i", complex(1.0, 2.0).toString());
    }
}
