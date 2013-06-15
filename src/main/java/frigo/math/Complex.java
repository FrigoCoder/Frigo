
package frigo.math;

import static java.lang.Double.valueOf;

public final class Complex {

    public static final Complex ZERO = new Complex(0.0, 0.0);
    public static final Complex ONE = new Complex(1.0, 0.0);
    public static final Complex I = new Complex(0.0, 1.0);

    public static Complex add (double x, Complex c) {
        return new Complex(x + c.re, c.im);
    }

    public static Complex cis (double x) {
        return new Complex(Math.cos(x), Math.sin(x));
    }

    public static Complex complex () {
        return new Complex();
    }

    public static Complex complex (double x) {
        return new Complex(x);
    }

    public static Complex complex (double real, double imag) {
        return new Complex(real, imag);
    }

    public static Complex div (double x, Complex c) {
        return mul(x, c.inv());
    }

    public static Complex mul (double x, Complex c) {
        return new Complex(x * c.re, x * c.im);
    }

    public static Complex pow (double x, Complex c) {
        return mul(Math.log(x), c).exp();
    }

    public static Complex sub (double x, Complex c) {
        return new Complex(x - c.re, -c.im);
    }

    public final double im;
    public final double re;

    public Complex () {
        this(0.0, 0.0);
    }

    public Complex (double x) {
        this(x, 0.0);
    }

    public Complex (double real, double imag) {
        re = real;
        im = imag;
    }

    /**
     * Returns the absolute value of the Complex number based on <a
     * href=http://en.wikipedia.org/wiki/Complex_number#Absolute_value_and_argument>the Wikipedia page</a>.
     **/
    public double abs () {
        return Math.sqrt(sqrAbs());
    }

    public Complex add (Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    public Complex add (double x) {
        return new Complex(re + x, im);
    }

    /**
     * Returns the argument of the complex number based on <a
     * href=http://en.wikipedia.org/wiki/Complex_number#Absolute_value_and_argument>the Wikipedia page</a>.
     **/
    public double arg () {
        return Math.atan2(im, re);
    }

    public Complex cis () {
        Complex c = cos();
        Complex s = sin();
        return new Complex(c.re - s.im, c.im + s.re);
    }

    public Complex conj () {
        return new Complex(re, -im);
    }

    /**
     * Complex cosine function based on <a href=http://en.wikipedia.org/wiki/Trigonometric_functions#
     * Relationship_to_exponential_function_and_complex_numbers>a Wikipedia page</a>.
     **/
    public Complex cos () {
        Complex eiz = cis(re).mul(Math.exp(-im));
        Complex eizinv = eiz.inv();
        return eiz.add(eizinv).mul(0.5);
    }

    /**
     * Complex cosine hyperbolic function based on <a href=http://en.wikipedia.org/wiki/Trigonometric_functions#
     * Relationship_to_exponential_function_and_complex_numbers>a Wikipedia page</a>.
     **/
    public Complex cosh () {
        Complex ez = cis(im).mul(Math.exp(re));
        Complex ezinv = ez.inv();
        return ez.add(ezinv).mul(0.5);
    }

    public Complex div (Complex c) {
        return mul(c.inv());
    }

    public Complex div (double x) {
        return mul(1.0 / x);
    }

    @Override
    public boolean equals (Object obj) {
        if( !(obj instanceof Complex) ){
            return false;
        }
        Complex c = (Complex) obj;
        return re == c.re && im == c.im;
    }

    /**
     * Returns e to the power of the Complex number, based on Euler's identity.
     **/
    public Complex exp () {
        return cis(im).mul(Math.exp(re));
    }

    @Override
    public int hashCode () {
        return valueOf(re).hashCode() + valueOf(im).hashCode() * 31;
    }

    /**
     * Returns the inverse of the Complex number. It is equivalent to its conjugate divided by its norm.
     **/
    public Complex inv () {
        return conj().div(sqrAbs());
    }

    /**
     * Returns the principal value of the logarithm of the Complex number.
     **/
    public Complex log () {
        return new Complex(0.5 * Math.log(sqrAbs()), arg());
    }

    public Complex mul (Complex c) {
        double t = (re - im) * c.im;
        return new Complex(re * (c.re - c.im) + t, im * (c.re + c.im) + t);
    }

    public Complex mul (double x) {
        return new Complex(re * x, im * x);
    }

    public Complex negate () {
        return new Complex(-re, -im);
    }

    /**
     * Returns the Complex number raised to the power of another Complex number. Based on <a
     * href=http://en.wikipedia.org/wiki/Exponentiation#Computing_complex_powers>this page</a> and <a
     * href=http://www.cplusplus.com/reference/std/complex/pow>this one</a>.
     **/
    public Complex pow (Complex c) {
        return log().mul(c).exp();
    }

    /**
     * Returns the Complex number raised to the power of a double. Based on the Complex case.
     **/
    public Complex pow (double x) {
        return log().mul(x).exp();
    }

    /**
     * Complex sine function based on <a href=http://en.wikipedia.org/wiki/Trigonometric_functions#
     * Relationship_to_exponential_function_and_complex_numbers>a Wikipedia page</a>.
     **/
    public Complex sin () {
        Complex eiz = cis(re).mul(Math.exp(-im));
        Complex eizinv = eiz.inv();
        return eiz.sub(eizinv).mul(new Complex(0.0, -0.5));
    }

    /**
     * Complex sine hyperbolic function based on <a href=http://en.wikipedia.org/wiki/Trigonometric_functions#
     * Relationship_to_exponential_function_and_complex_numbers>a Wikipedia page</a>.
     **/
    public Complex sinh () {
        Complex ez = cis(im).mul(Math.exp(re));
        Complex ezinv = ez.inv();
        return ez.sub(ezinv).mul(0.5);
    }

    public Complex sqr () {
        return new Complex((re + im) * (re - im), 2 * re * im);
    }

    public double sqrAbs () {
        return re * re + im * im;
    }

    /**
     * Returns the principal value of the square roots of the Complex number.
     **/
    public Complex sqrt () {
        return pow(0.5);
    }

    public Complex sub (Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    public Complex sub (double x) {
        return new Complex(re - x, im);
    }

    @Override
    public String toString () {
        return String.format("%20.16f", re) + String.format("%+20.16f", im) + "i";
    }
}
