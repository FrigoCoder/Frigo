
package frigo.math;

import static frigo.math.Complex.I;
import static frigo.math.Complex.add;
import static frigo.math.Complex.cis;
import static frigo.math.Complex.complex;
import static frigo.math.Complex.div;
import static frigo.math.Complex.mul;
import static frigo.math.Complex.pow;
import static frigo.math.Complex.sub;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ComplexTest {

    private static final double EPSILON = 1e-14;

    @Test
    public void test_abs () {
        assertEquals(complex(1.0, 0.0).abs(), 1.0);
        assertEquals(complex(0.0, 1.0).abs(), 1.0);
        assertEquals(complex(1.0, 1.0).abs(), sqrt(2.0));
        assertEquals(complex(sqrt(2.0), -sqrt(2.0)).abs(), 2.0);
    }

    @Test
    public void test_add_Complex () {
        assertEquals(complex(1.0, -1.0).add(complex(-1.0, 1.0)), complex(0.0, 0.0));
        assertEquals(complex(1.0, 2.0).add(complex(3.0, 4.0)), complex(4.0, 6.0));
    }

    @Test
    public void test_add_double () {
        assertEquals(complex(1.0, -1.0).add(1.0), complex(2.0, -1.0));
        assertEquals(complex(-1.0, 0.0).add(1.0), complex(0.0, 0.0));
    }

    @Test
    public void test_arg () {
        assertEquals(cis(0.1).arg(), 0.1);
        assertEquals(cis(0.1).mul(2.0).arg(), 0.1);
        assertEquals(cis(-0.2).mul(0.1).arg(), -0.2);
    }

    @Test
    public void test_cis () {
        assertEquals(complex(0.0, 0.0).cis(), cis(0.0));
        assertEquals(complex(3.0, 0.0).cis(), cis(3.0));
        assertEquals(complex(1.0, 2.0).cis(),
            complex(1.0, 2.0).sin().mul(complex(0.0, 1.0)).add(complex(1.0, 2.0).cos()));
        assertEquals(complex(2.0, 1.0).cis(),
            complex(2.0, 1.0).sin().mul(complex(0.0, 1.0)).add(complex(2.0, 1.0).cos()));
    }

    @Test
    public void test_conj () {
        assertEquals(complex(1.0, 1.0).conj(), complex(1.0, -1.0));
        assertEquals(complex(2.0, 1.0).conj(), complex(2.0, -1.0));
    }

    @Test
    public void test_constructors () {
        assertEquals(complex(), complex(0.0));
        assertEquals(complex(0.0), complex(0.0, 0.0));
        assertEquals(complex(1.0), complex(1.0, 0.0));
        assertEquals(complex(-1.0), complex(-1.0, 0.0));
    }

    @Test
    public void test_cos () {
        assertEquals(complex(1.0, 0.0).cos(), complex(cos(1.0), 0.0));
        assertEquals(complex(2.0, 0.0).cos(), complex(cos(2.0), 0.0));
        assertEquals(complex(1.0, 2.0).cos(), complex(1.0, 2.0).mul(I).cosh());
        assertEquals(complex(2.0, 1.0).cos(), complex(2.0, 1.0).mul(I).cosh());
    }

    @Test
    public void test_cosh () {
        assertEquals(complex(1.0, 0.0).cosh(), complex((exp(1.0) + exp(-1.0)) / 2.0, 0.0));
        assertEquals(complex(3.0, 0.0).cosh(), complex((exp(3.0) + exp(-3.0)) / 2.0, 0.0));
        assertEquals(complex(1.0, 2.0).cosh(), complex(1.0, 2.0).exp().add(complex(1.0, 2.0).negate().exp()).mul(0.5));
        assertEquals(complex(2.0, 1.0).cosh(), complex(2.0, 1.0).exp().add(complex(2.0, 1.0).negate().exp()).mul(0.5));
    }

    @Test
    public void test_div_Complex () {
        assertEquals(complex(1.0, 2.0).div(complex(1.0, 2.0)), complex(1.0, 0.0));
        assertEquals(complex(2.0, 1.0).div(complex(2.0, 1.0)), complex(1.0, 0.0));
        assertEquals(complex(2.0, 4.0).div(complex(1.0, 2.0)), complex(2.0, 0.0));
        double a = 1.0, b = 2.0;
        double x = 3.0, y = 4.0;
        assertEquals(complex(a, b).div(complex(x, y)), complex(a * x + b * y, b * x - a * y).div(x * x + y * y));
    }

    @Test
    public void test_div_double () {
        assertEquals(complex(1.0, 2.0).div(0.5), complex(2.0, 4.0));
        assertEquals(complex(3.0, 1.0).div(2.0), complex(3.0 / 2.0, 1.0 / 2.0));
    }

    @Test
    public void test_equals () {
        assertThat(complex(0.0, 0.0), not(new Object()));
        assertThat(complex(0.0, 0.0).equals(null), is(false));
    }

    @Test
    public void test_exp () {
        assertEquals(complex(1.0, 0.0).exp(), complex(exp(1.0), 0.0));
        assertEquals(complex(3.0, 0.0).exp(), complex(exp(3.0), 0.0));
        assertEquals(complex(2.0, 3.0).exp(), cis(3.0).mul(exp(2.0)));
        assertEquals(complex(1.0, 2.0).exp().log(), complex(1.0, 2.0));
    }

    @Test
    public void test_hashCode () {
        assertThat(complex(0.0, 0.0).hashCode(), not(complex(0.0, 1.0).hashCode()));
    }

    @Test
    public void test_inv () {
        assertEquals(complex(1.0, 2.0).mul(complex(2.0, 1.0)).mul(complex(2.0, 1.0).inv()), complex(1.0, 2.0));
        assertEquals(complex(1.0, 2.0).inv().mul(complex(1.0, 2.0)), complex(1.0, 0.0));
    }

    @Test
    public void test_log () {
        assertEquals(complex(1.0, 2.0).log().exp(), complex(1.0, 2.0));
    }

    @Test
    public void test_mul_Complex () {
        assertEquals(complex(1.0, 2.0).mul(complex(3.0, 4.0)), complex(-5.0, 10.0));
        assertEquals(complex(2.0, 3.0).mul(complex(4.0, 5.0)), complex(-7.0, 22.0));
    }

    @Test
    public void test_mul_double () {
        assertEquals(complex(1.0, 2.0).mul(2.0), complex(2.0, 4.0));
        assertEquals(complex(2.0, 3.0).mul(4.0), complex(8.0, 12.0));
    }

    @Test
    public void test_negate () {
        assertEquals(complex(1.0, 2.0).negate(), complex(-1.0, -2.0));
        assertEquals(complex(2.0, 3.0).negate(), complex(-2.0, -3.0));
    }

    @Test
    public void test_norm () {
        assertEquals(cis(0.1).sqrAbs(), 1.0);
        assertEquals(cis(3.0).sqrAbs(), 1.0);
    }

    @Test
    public void test_pow_Complex () {
        assertEquals(complex(2.0, 0.0).pow(complex(4.0, 0.0)), complex(16.0, 0.0));
        assertEquals(complex(2.0, 1.0).pow(complex(2.0, 0.0)), complex(3.0, 4.0));
        assertEquals(complex(3.0, 4.0).pow(complex(0.0, 1.0)), complex(3.0, 4.0).log().mul(I).exp());
    }

    @Test
    public void test_pow_double () {
        assertEquals(complex(2.0, 0.0).pow(2.0), complex(4.0, 0.0));
        assertEquals(complex(2.0, 0.0).pow(0.5), complex(sqrt(2.0), 0.0));
        double abs = 10.0, phi = 0.3;
        assertEquals(cis(phi).mul(abs).pow(0.5), cis(phi / 2.0).mul(sqrt(abs)));
    }

    @Test
    public void test_sin () {
        assertEquals(complex(1.0, 0.0).sin(), complex(sin(1.0), 0.0));
        assertEquals(complex(2.0, 0.0).sin(), complex(sin(2.0), 0.0));
        assertEquals(complex(1.0, 2.0).sin(), complex(1.0, 2.0).mul(I).sinh().div(I));
        assertEquals(complex(2.0, 1.0).sin(), complex(2.0, 1.0).mul(I).sinh().div(I));
    }

    @Test
    public void test_sinh () {
        assertEquals(complex(1.0, 0.0).sinh(), complex((exp(1.0) - exp(-1.0)) / 2.0, 0.0));
        assertEquals(complex(3.0, 0.0).sinh(), complex((exp(3.0) - exp(-3.0)) / 2.0, 0.0));
        assertEquals(complex(1.0, 2.0).sinh(), complex(1.0, 2.0).exp().sub(complex(1.0, 2.0).negate().exp()).mul(0.5));
        assertEquals(complex(2.0, 1.0).sinh(), complex(2.0, 1.0).exp().sub(complex(2.0, 1.0).negate().exp()).mul(0.5));
    }

    @Test
    public void test_sqr () {
        assertEquals(complex(2.0, 0.0).sqr(), complex(4.0, 0.0));
        assertEquals(complex(3.0, 0.0).sqr(), complex(9.0, 0.0));
        assertEquals(complex(1.0, 2.0).sqr(), complex(1.0, 2.0).pow(2.0));
        assertEquals(complex(2.0, 3.0).sqr(), complex(2.0, 3.0).pow(2.0));
    }

    @Test
    public void test_sqrt () {
        assertEquals(complex(4.0, 0.0).sqrt(), complex(2.0, 0.0));
        assertEquals(complex(-1.0, 0.0).sqrt(), complex(0.0, 1.0));
        assertEquals(complex(2.0, 3.0).sqrt().sqr(), complex(2.0, 3.0));
    }

    @Test
    public void test_static_add () {
        assertEquals(add(1.0, complex(2.0, 3.0)), complex(3.0, 3.0));
        assertEquals(add(-1.0, complex(0.0, 1.0)), complex(-1.0, 1.0));
    }

    @Test
    public void test_static_cis () {
        assertEquals(cis(0.1), complex(cos(0.1), sin(0.1)));
        assertEquals(cis(3.0), complex(cos(3.0), sin(3.0)));
    }

    @Test
    public void test_static_complex () {
        assertEquals(complex(), complex(0.0));
        assertEquals(complex(0.1), complex(0.1, 0.0));
        assertEquals(complex(3.0), complex(3.0, 0.0));
        assertEquals(complex(3.0, 2.0), new Complex(3.0, 2.0));
        assertEquals(complex(2.0, 3.0), new Complex(2.0, 3.0));
    }

    @Test
    public void test_static_div () {
        assertEquals(div(1.0, complex(2.0, 3.0)), complex(2.0, 3.0).inv().mul(1.0));
        assertEquals(div(1.0, complex(2.0, 3.0)).mul(complex(2.0, 3.0)), complex(1.0, 0.0));
    }

    @Test
    public void test_static_mul () {
        assertEquals(mul(2.0, complex(3.0, 4.0)), complex(3.0, 4.0).mul(2.0));
        assertEquals(mul(2.0, complex(3.0, 4.0)).div(complex(3.0, 4.0)), complex(2.0, 0.0));
    }

    @Test
    public void test_static_pow () {
        assertEquals(pow(2.0, complex(3.0, 4.0)), complex(2.0, 0.0).pow(complex(3.0, 4.0)));
        assertEquals(pow(3.0, complex(4.0, 5.0)), complex(3.0, 0.0).pow(complex(4.0, 5.0)));
        assertEquals(pow(2.0, complex(3.0, 4.0)).pow(complex(3.0, 4.0).inv()), complex(2.0, 0.0));
        assertEquals(pow(1.0, complex(2.0, 3.0)).pow(complex(2.0, 3.0).inv()), complex(1.0, 0.0));
    }

    @Test
    public void test_static_sub () {
        assertEquals(sub(2.0, complex(3.0, 4.0)), complex(-1.0, -4.0));
        assertEquals(sub(3.0, complex(4.0, 5.0)), complex(-1.0, -5.0));
    }

    @Test
    public void test_sub_Complex () {
        assertEquals(complex(1.0, 0.0).sub(complex(0.0, 1.0)), complex(1.0, -1.0));
        assertEquals(complex(1.0, 1.0).sub(complex(0.0, 1.0)), complex(1.0, 0.0));
        assertEquals(complex(0.0, 1.0).sub(I), complex(0.0));
    }

    @Test
    public void test_sub_double () {
        assertEquals(complex(1.0, 1.0).sub(1.0), I);
        assertEquals(complex(16.0, 1.0).sub(8.5), complex(7.5, 1.0));
    }

    @Test
    public void test_toString () {
        assertThat(complex(1.0, 2.0).toString(), is(format("%20.16f", 1.0) + format("%+20.16f", 2.0) + "i"));
    }

    private void assertEquals (Complex actual, Complex expected) {
        assertEquals(actual.re, expected.re);
        assertEquals(actual.im, expected.im);
    }

    private void assertEquals (double actual, double expected) {
        assertThat(actual, closeTo(expected, EPSILON));
    }

}
