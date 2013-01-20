
package frigo.math;

import java.util.Arrays;

public final class Polynomial {

    public static final Polynomial ZERO = new Polynomial(0.0);
    public static final Polynomial ONE = new Polynomial(1.0);
    public static final Polynomial X = new Polynomial(1.0, 0.0);
    public static final Polynomial X2 = new Polynomial(1.0, 0.0, 0.0);
    public static final Polynomial X3 = new Polynomial(1.0, 0.0, 0.0, 0.0);

    /**
     * Shortcut for new Polynomial(...)
     **/
    public static Polynomial poly (double... coeffs) {
        return new Polynomial(coeffs);
    }

    /**
     * Array of double holding the coefficients of the polynomial
     **/
    private double[] v;

    /**
     * Constructs a polynomial from its coefficients. Coefficients are ordered from highest to lowest degree, for
     * example (2.0, 1.0, 0.0) is equivalent to 2x^2+x. An empty parameter list corresponds to the constant zero
     * polynomial.
     **/
    public Polynomial (double... coeffs) {
        this(coeffs.length - 1);
        int degree = getDegree();
        for( double coeff : coeffs ){
            setCoeff(degree, coeff);
            degree--;
        }
        normalize();
    }

    /**
     * Creates a new polynomial of a particular degree with all of its coefficient initialized to zero. The resulting
     * polynomial is at least zeroth degree, having at least one coefficient. For internal use only
     **/
    private Polynomial (int degree) {
        v = new double[Math.max(degree + 1, 1)];
    }

    /**
     * Returns the sum of the polynomial and a real number
     **/
    public Polynomial add (double scalar) {
        Polynomial result = new Polynomial(getDegree());
        result.setCoeff(0, getCoeff(0) + scalar);
        for( int i = 1; i <= getDegree(); i++ ){
            result.setCoeff(i, getCoeff(i));
        }
        return result;
    }

    /**
     * Returns the sum of two polynomials
     **/
    public Polynomial add (Polynomial obj) {
        int max = Math.max(getDegree(), obj.getDegree());
        int min = Math.min(getDegree(), obj.getDegree());
        Polynomial result = new Polynomial(max);
        for( int i = 0; i <= min; i++ ){
            result.setCoeff(i, getCoeff(i) + obj.getCoeff(i));
        }
        for( int i = min + 1; i <= getDegree(); i++ ){
            result.setCoeff(i, getCoeff(i));
        }
        for( int i = min + 1; i <= obj.getDegree(); i++ ){
            result.setCoeff(i, obj.getCoeff(i));
        }
        result.normalize();
        return result;
    }

    /**
     * Calculates the derivative of the polynomal, based on the <a
     * href=http://en.wikipedia.org/wiki/Polynomial#Polynomials_and_calculus>Wikipedia page</a>
     **/
    public Polynomial derivate () {
        Polynomial result = new Polynomial(getDegree() - 1);
        for( int i = 1; i <= getDegree(); i++ ){
            result.setCoeff(i - 1, getCoeff(i) * i);
        }
        return result;
    }

    /**
     * Returns the division of the polynomial by a real number
     **/
    public Polynomial div (double scalar) {
        Polynomial result = new Polynomial(getDegree());
        for( int i = 0; i <= getDegree(); i++ ){
            result.setCoeff(i, getCoeff(i) / scalar);
        }
        result.normalize();
        return result;
    }

    /**
     * Compares two polynomials by their coefficients
     **/
    @Override
    public boolean equals (Object obj) {
        if( !(obj instanceof Polynomial) ){
            return false;
        }
        Polynomial poly = (Polynomial) obj;
        if( getDegree() != poly.getDegree() ){
            return false;
        }
        for( int i = 0; i <= getDegree(); i++ ){
            if( getCoeff(i) != poly.getCoeff(i) ){
                return false;
            }
        }
        return true;
    }

    /**
     * Evaluates the polynomial at an x real argument
     **/
    public double evaluate (double scalar) {
        double s = getCoeff(0);
        double z = scalar;
        for( int i = 1; i <= getDegree(); i++ ){
            s += getCoeff(i) * z;
            z *= scalar;
        }
        return s;
    }

    /**
     * Returns the coefficient of a particular degree
     **/
    public double getCoeff (int degree) {
        return v[degree];
    }

    /**
     * Returns the degree of the polynomial, which is the degree of the highest term. For example, for quadratic
     * polynomials, it returns 2.
     **/
    public int getDegree () {
        return v.length - 1;
    }

    @Override
    public int hashCode () {
        return Arrays.hashCode(v);
    }

    /**
     * Calculates the indefinite integral or primitive function of the polynomial, based on the <a
     * href=http://en.wikipedia.org/wiki/Polynomial#Polynomials_and_calculus>Wikipedia page</a>. The integral of a zero
     * polynomial is zero (+ constant, which is not addressed here).
     **/
    public Polynomial integrate () {
        Polynomial result = new Polynomial(getDegree() + 1);
        for( int i = 0; i <= getDegree(); i++ ){
            result.setCoeff(i + 1, getCoeff(i) / (i + 1.0));
        }
        result.normalize();
        return result;
    }

    /**
     * Calculates the definite integral of the polynomial defined between two arguments
     **/
    public double integrate (double a, double b) {
        Polynomial F = integrate();
        return F.evaluate(b) - F.evaluate(a);
    }

    /**
     * Returns the product of the polynomial and a real number
     **/
    public Polynomial mul (double scalar) {
        Polynomial result = new Polynomial(getDegree());
        for( int i = 0; i <= getDegree(); i++ ){
            result.setCoeff(i, getCoeff(i) * scalar);
        }
        result.normalize();
        return result;
    }

    /**
     * Returns the product of two polynomials
     **/
    public Polynomial mul (Polynomial obj) {
        Polynomial result = new Polynomial(getDegree() + obj.getDegree());
        for( int i = 0; i <= getDegree(); i++ ){
            for( int j = 0; j <= obj.getDegree(); j++ ){
                result.setCoeff(i + j, result.getCoeff(i + j) + getCoeff(i) * obj.getCoeff(j));
            }
        }
        result.normalize();
        return result;
    }

    /**
     * Returns the negative of the polynomial
     **/
    public Polynomial negate () {
        Polynomial result = new Polynomial(getDegree());
        for( int i = 0; i <= getDegree(); i++ ){
            result.setCoeff(i, -getCoeff(i));
        }
        return result;
    }

    /**
     * Returns the polynomial minus a real number
     **/
    public Polynomial sub (double scalar) {
        Polynomial result = new Polynomial(getDegree());
        result.setCoeff(0, getCoeff(0) - scalar);
        for( int i = 1; i <= getDegree(); i++ ){
            result.setCoeff(i, getCoeff(i));
        }
        return result;
    }

    /**
     * Returns the polynomial minus another polynomial
     **/
    public Polynomial sub (Polynomial obj) {
        int max = Math.max(getDegree(), obj.getDegree());
        int min = Math.min(getDegree(), obj.getDegree());
        Polynomial result = new Polynomial(max);
        for( int i = 0; i <= min; i++ ){
            result.setCoeff(i, getCoeff(i) - obj.getCoeff(i));
        }
        for( int i = min + 1; i <= getDegree(); i++ ){
            result.setCoeff(i, getCoeff(i));
        }
        for( int i = min + 1; i <= obj.getDegree(); i++ ){
            result.setCoeff(i, -obj.getCoeff(i));
        }
        result.normalize();
        return result;
    }

    /**
     * Returns a string representation of the polynomial
     **/
    @Override
    public String toString () {
        return toString("x");
    }

    /**
     * Returns a string representation of the polynomial, with a custom variable name
     **/
    public String toString (String variable) {
        String result = "";
        for( int i = getDegree(); i >= 0; i-- ){
            double coeff = getCoeff(i);
            // do not output zero terms at all, except if the polynomial is a constant zero
            if( coeff == 0.0 && getDegree() != 0 ){
                continue;
            }
            // output + and - if needed, take abs value of coefficient
            if( coeff > 0.0 ){
                if( i != getDegree() ){
                    result += "+";
                }
            }else if( coeff < 0.0 ){
                result += "-";
                coeff = -coeff;
            }
            // output coefficient if it is not +-1, or if it is a constant
            if( coeff != 1.0 || i == 0 ){
                result += Double.toString(coeff);
            }
            // output power of term
            switch( i ){
                case 0:
                    break;
                case 1:
                    result += variable;
                    break;
                default:
                    result += variable + "^" + Integer.toString(i);
            }
        }
        return result;
    }

    /**
     * Normalizes the polynomial by cutting off the highest degree zero coefficients, so the invariant of the data
     * structure holds. For internal use only
     **/
    private void normalize () {
        int degree = getDegree();
        while( degree > 0 && getCoeff(degree) == 0.0 ){
            degree--;
        }
        if( degree != getDegree() ){
            double[] w = new double[degree + 1];
            for( int i = 0; i <= degree; i++ ){
                w[i] = v[i];
            }
            v = w;
        }
    }

    /**
     * Sets the coefficient of a particular degree to a particular value. Used by the basic arithmetic operations like
     * addition or multipliciation. For internal use only
     **/
    private void setCoeff (int degree, double coefficient) {
        v[degree] = coefficient;
    }
}
