
package frigo.math;

import static frigo.math.Polynomial.poly;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PolynomialTest {

    public void checkInvariant (Polynomial p) {
        assertThat(p.getDegree() >= 0, is(true));
        assertThat(p.getDegree() == 0 || p.getCoeff(p.getDegree()) != 0.0, is(true));
    }

    @Test
    public void testAddDouble () {
        assertThat(poly().add(1.0), is(poly(1.0)));
        assertThat(poly(1.0, 0.0).add(-1.0), is(poly(1.0, -1.0)));
    }

    @Test
    public void testAddPoly () {
        assertThat(poly(1.0).add(poly(1.0, 0.0)), is(poly(1.0, 1.0)));
        assertThat(poly(1.0, 0.0, 0.0).add(poly(1.0)), is(poly(1.0, 0.0, 1.0)));
        assertThat(poly(1.0, 2.0, 3.0, 4.0).add(poly(1.0, 2.0, 3.0)), is(poly(1.0, 3.0, 5.0, 7.0)));
    }

    @Test
    public void testConstructorConstant () {
        assertThat(poly(1.0).sub(1.0), is(poly(0.0)));
        assertThat(poly(-1.0).add(1.0), is(poly(0.0)));
        assertThat(poly(2.0).mul(2.0), is(poly(4.0)));
    }

    @Test
    public void testConstructorCubic () {
        assertThat(poly(-0.0, 1.0, 1.0, 1.0), is(poly(1.0, 1.0, 1.0)));
        assertThat(poly(1.0, 1.0, 1.0, 1.0).sub(poly(1.0, 0.0, 1.0, 1.0)), is(poly(1.0, 0.0, 0.0)));
    }

    @Test
    public void testConstructorLinear () {
        assertThat(poly(-0.0, 1.0), is(poly(1.0)));
        assertThat(poly(1.0, 1.0).sub(1.0), is(poly(1.0, 0.0)));
    }

    @Test
    public void testConstructorQuadratic () {
        assertThat(poly(-0.0, 1.0, 1.0), is(poly(1.0, 1.0)));
        assertThat(poly(1.0, 1.0, 1.0).sub(poly(1.0, 0.0, 1.0)), is(poly(1.0, 0.0)));
    }

    @Test
    public void testConstructorZero () {
        assertThat(poly(), is(poly(0.0)));
    }

    @Test
    public void testDerivate () {
        assertThat(poly(1.0).derivate(), is(poly(0.0)));
        assertThat(poly(1.0, 2.0).derivate(), is(poly(1.0)));
        assertThat(poly(1.0, 2.0, 3.0).derivate(), is(poly(2.0, 2.0)));
        assertThat(poly(1.0, 2.0, 3.0, 4.0).derivate(), is(poly(3.0, 4.0, 3.0)));
    }

    @Test
    public void testDivDouble () {
        assertThat(poly(1.0, 1.0, 0.0, 1.0).div(0.5), is(poly(2.0, 2.0, 0.0, 2.0)));
        assertThat(poly(2.0, 2.0, 0.0, 2.0).div(2.0), is(poly(1.0, 1.0, 0.0, 1.0)));
    }

    @Test
    public void testEquals () {
        assertThat(poly(-0.0, 1.0), is(poly(1.0)));
        assertThat(poly(+0.0, 2.0), is(poly(2.0)));
        assertThat(poly(+0.0, 1.0, 2.0), is(poly(1.0, 2.0)));
        assertThat(poly(1.0).add(poly(-1.0)), is(poly()));
    }

    @Test
    public void testEvaluate () {
        assertThat(poly(1.0).evaluate(3.0), is(1.0));
        assertThat(poly(1.0, 1.0).evaluate(3.0), is(4.0));
        assertThat(poly(1.0, 1.0, 1.0).evaluate(3.0), is(13.0));
        assertThat(poly(2.0, 1.0, 0.0).evaluate(3.0), is(21.0));
    }

    @Test
    public void testGetCoeff () {
        Polynomial p = poly(1.0, 2.0, 3.0, 4.0);
        assertThat(p.getCoeff(0), is(4.0));
        assertThat(p.getCoeff(1), is(3.0));
        assertThat(p.getCoeff(2), is(2.0));
        assertThat(p.getCoeff(3), is(1.0));
    }

    @Test
    public void testGetDegree () {
        assertThat(poly().getDegree(), is(0));
        assertThat(poly(1.0).getDegree(), is(0));
        assertThat(poly(-0.0, 1.0).getDegree(), is(0));
        assertThat(poly(1.0, 2.0).getDegree(), is(1));
        assertThat(poly(-0.0, 1.0, 2.0).getDegree(), is(1));
        assertThat(poly(1.0, 2.0, 3.0).getDegree(), is(2));
        assertThat(poly(-0.0, 1.0, 2.0, 3.0).getDegree(), is(2));
        assertThat(poly(1.0, 2.0, 3.0, 4.0).getDegree(), is(3));
    }

    @Test
    public void testIntegrate () {
        assertThat(poly().integrate(), is(poly(0.0)));
        assertThat(poly(1.0).integrate(), is(poly(1.0, 0.0)));
        assertThat(poly(2.0, 0.0).integrate(), is(poly(1.0, 0.0, 0.0)));
        assertThat(poly(3.0, 0.0, 0.0).integrate(), is(poly(1.0, 0.0, 0.0, 0.0)));
        assertThat(poly(4.0, 0.0, 0.0, 0.0).integrate(), is(poly(1.0, 0.0, 0.0, 0.0, 0.0)));
        assertThat(poly().integrate().derivate(), is(poly()));
        assertThat(poly(1.0).integrate().derivate(), is(poly(1.0)));
        assertThat(poly(1.0, 0.0).integrate().derivate(), is(poly(1.0, 0.0)));
        assertThat(poly(1.0, 0.0, 0.0).integrate().derivate(), is(poly(1.0, 0.0, 0.0)));
        assertThat(poly(1.0, 0.0, 0.0, 0.0).integrate().derivate(), is(poly(1.0, 0.0, 0.0, 0.0)));
    }

    @Test
    public void testIntegrateDefinite () {
        Polynomial p = poly(2.0, 1.0, 0.0);
        double a = -10.0;
        double b = 3.0;
        double c = 5.0;
        assertThat(p.integrate(a, b) + p.integrate(b, c), is(p.integrate(a, c)));
        assertThat(p.integrate(a, b), is(p.integrate(a, c) + p.integrate(c, b)));
    }

    @Test
    public void testInvariant () {
        checkInvariant(poly());
        checkInvariant(poly(0.0, 1.0));
        checkInvariant(poly(-0.0, 1.0, 2.0));
        checkInvariant(poly(0.0).derivate());
        checkInvariant(poly(1.0).derivate());
        checkInvariant(poly(1.0, 0.0).derivate());
        checkInvariant(poly(1.0, 0.0, 0.0).derivate());
        checkInvariant(poly(0.0).integrate());
        checkInvariant(poly(1.0).integrate());
        checkInvariant(poly(1.0, 0.0).integrate());
        checkInvariant(poly(1.0, 0.0, 0.0).integrate());
        checkInvariant(poly(0.0).add(1.0));
        checkInvariant(poly(-1.0).add(1.0));
        checkInvariant(poly(1.0).add(1.0));
        checkInvariant(poly(1.0, -1.0).add(1.0));
        checkInvariant(poly(0.0).add(poly(0.0)));
        checkInvariant(poly(1.0).add(poly(-1.0)));
        checkInvariant(poly(1.0, 0.0).add(poly(-1.0, 0.0)));
        checkInvariant(poly(1.0, 0.0, 0.0).add(poly(-1.0, 0.0, 0.0)));
        checkInvariant(poly(1.0).div(1.0));
        checkInvariant(poly(1.0).div(2.0));
        checkInvariant(poly(1.0).div(18446744073709551616.0));
        checkInvariant(poly(1.0).div(0.0));
        checkInvariant(poly(1.0).mul(1.0));
        checkInvariant(poly(1.0).mul(2.0));
        checkInvariant(poly(1.0).mul(18446744073709551616.0));
        checkInvariant(poly(1.0).mul(0.0));
        checkInvariant(poly(1.0).mul(poly(1.0)));
        checkInvariant(poly(1.0, 0.0).mul(poly(0.0, 0.0)));
        checkInvariant(poly(1.0, 0.0).mul(poly(1.0, 0.0)));
        checkInvariant(poly(-0.0, +0.0).negate());
        checkInvariant(poly(+0.0, -0.0).negate());
        checkInvariant(poly(0.0).sub(1.0));
        checkInvariant(poly(-1.0).sub(-1.0));
        checkInvariant(poly(1.0).sub(-1.0));
        checkInvariant(poly(1.0, -1.0).sub(-1.0));
        checkInvariant(poly(0.0).sub(poly(0.0)));
        checkInvariant(poly(1.0).sub(poly(1.0)));
        checkInvariant(poly(1.0, 0.0).sub(poly(1.0, 0.0)));
        checkInvariant(poly(1.0, 0.0, 0.0).sub(poly(1.0, 0.0, 0.0)));
        checkInvariant(Polynomial.ONE);
        checkInvariant(Polynomial.X);
        checkInvariant(Polynomial.X2);
        checkInvariant(Polynomial.X3);
    }

    @Test
    public void testMulDouble () {
        assertThat(poly(1.0, 1.0, 0.0, 1.0).mul(0.5), is(poly(0.5, 0.5, 0.0, 0.5)));
        assertThat(poly(2.0, 2.0, 0.0, 2.0).mul(2.0), is(poly(4.0, 4.0, 0.0, 4.0)));
    }

    @Test
    public void testMulPoly () {
        assertThat(poly(1.0, 1.0).mul(poly(1.0, 1.0)), is(poly(1.0, 2.0, 1.0)));
        assertThat(poly(1.0, 1.0).mul(poly(1.0, -1.0)), is(poly(1.0, 0.0, -1.0)));
        assertThat(poly(2.0, 1.0, 0.0).mul(poly(1.0, 0.0)), is(poly(2.0, 1.0, 0.0, 0.0)));
        assertThat(poly(1.0, 2.0, 3.0).mul(poly(1.0, 2.0)), is(poly(1.0, 4.0, 7.0, 6.0)));
    }

    @Test
    public void testNegate () {
        assertThat(poly().negate(), is(poly(0.0)));
        assertThat(poly(1.0).negate(), is(poly(-1.0)));
        assertThat(poly(1.0, -2.0, 3.0, -4.0, -5.0).negate(), is(poly(-1.0, 2.0, -3.0, 4.0, 5.0)));
    }

    @Test
    public void testPoly () {
        assertThat(poly(), is(new Polynomial(0.0)));
        assertThat(poly(1.0), is(new Polynomial(1.0)));
        assertThat(poly(-0.0, 1.0), is(new Polynomial(1.0)));
        assertThat(poly(-0.0, 2.0, 1.0), is(new Polynomial(+0.0, 2.0, 1.0)));
        assertThat(poly(3.0, 2.0, 1.0), is(new Polynomial(3.0, 2.0, 1.0)));
    }

    @Test
    public void testStaticVars () {
        assertThat(Polynomial.ONE, is(poly(1.0)));
        assertThat(Polynomial.X, is(poly(1.0, 0.0)));
        assertThat(Polynomial.X2, is(poly(1.0, 0.0, 0.0)));
        assertThat(Polynomial.X3, is(poly(1.0, 0.0, 0.0, 0.0)));
    }

    @Test
    public void testSubDouble () {
        assertThat(poly().sub(1.0), is(poly(-1.0)));
        assertThat(poly(1.0, 0.0).sub(-1.0), is(poly(1.0, 1.0)));
    }

    @Test
    public void testSubPoly () {
        assertThat(poly(1.0).sub(poly(1.0, 0.0)), is(poly(-1.0, 1.0)));
        assertThat(poly(1.0, 0.0, 0.0).sub(poly(1.0)), is(poly(1.0, 0.0, -1.0)));
        assertThat(poly(1.0, 2.0, 3.0, 4.0).sub(poly(1.0, 2.0, 3.0)), is(poly(1.0, 1.0, 1.0, 1.0)));
    }

    @Test
    public void testToString () {
        assertThat(poly().toString(), is("0.0"));
        assertThat(poly(1.0).toString(), is("1.0"));
        assertThat(poly(-1.0).toString(), is("-1.0"));
        assertThat(poly(1.0, 0.0).toString(), is("x"));
        assertThat(poly(-1.0, 0.0).toString(), is("-x"));
        assertThat(poly(2.0, 1.0).toString(), is("2.0x+1.0"));
        assertThat(poly(-3.0, -2.0, 1.0).toString("c"), is("-3.0c^2-2.0c+1.0"));
    }
}
