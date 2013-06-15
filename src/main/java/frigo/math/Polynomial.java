
package frigo.math;

import java.util.Arrays;

public final class Polynomial {

    public static final Polynomial ZERO = new Polynomial(0.0);
    public static final Polynomial ONE = new Polynomial(1.0);
    public static final Polynomial X = new Polynomial(1.0, 0.0);
    public static final Polynomial X2 = new Polynomial(1.0, 0.0, 0.0);
    public static final Polynomial X3 = new Polynomial(1.0, 0.0, 0.0, 0.0);

    public static Polynomial poly (double... coeffs) {
        return new Polynomial(coeffs);
    }

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

    private Polynomial (int degree) {
        v = new double[Math.max(degree + 1, 1)];
    }

    public Polynomial add (double scalar) {
        Polynomial result = new Polynomial(getDegree());
        result.setCoeff(0, getCoeff(0) + scalar);
        for( int i = 1; i <= getDegree(); i++ ){
            result.setCoeff(i, getCoeff(i));
        }
        return result;
    }

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
     * Calculates the derivative of the polynomal, based on <a
     * href=http://en.wikipedia.org/wiki/Polynomial#Polynomials_and_calculus>the Wikipedia page</a>
     **/

    public Polynomial derivate () {
        Polynomial result = new Polynomial(getDegree() - 1);
        for( int i = 1; i <= getDegree(); i++ ){
            result.setCoeff(i - 1, getCoeff(i) * i);
        }
        return result;
    }

    public Polynomial div (double scalar) {
        Polynomial result = new Polynomial(getDegree());
        for( int i = 0; i <= getDegree(); i++ ){
            result.setCoeff(i, getCoeff(i) / scalar);
        }
        result.normalize();
        return result;
    }

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

    public double evaluate (double scalar) {
        double s = getCoeff(0);
        double z = scalar;
        for( int i = 1; i <= getDegree(); i++ ){
            s += getCoeff(i) * z;
            z *= scalar;
        }
        return s;
    }

    public double getCoeff (int degree) {
        return v[degree];
    }

    private void setCoeff (int degree, double coefficient) {
        v[degree] = coefficient;
    }

    public int getDegree () {
        return v.length - 1;
    }

    @Override
    public int hashCode () {
        return Arrays.hashCode(v);
    }

    /**
     * Calculates the indefinite integral or primitive function of the polynomial, based on <a
     * href=http://en.wikipedia.org/wiki/Polynomial#Polynomials_and_calculus>the Wikipedia page</a>. The integral of a
     * zero polynomial is zero (+ constant, which is not addressed here).
     **/

    public Polynomial integrate () {
        Polynomial result = new Polynomial(getDegree() + 1);
        for( int i = 0; i <= getDegree(); i++ ){
            result.setCoeff(i + 1, getCoeff(i) / (i + 1.0));
        }
        result.normalize();
        return result;
    }

    public double integrate (double a, double b) {
        Polynomial F = integrate();
        return F.evaluate(b) - F.evaluate(a);
    }

    public Polynomial mul (double scalar) {
        Polynomial result = new Polynomial(getDegree());
        for( int i = 0; i <= getDegree(); i++ ){
            result.setCoeff(i, getCoeff(i) * scalar);
        }
        result.normalize();
        return result;
    }

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

    public Polynomial negate () {
        Polynomial result = new Polynomial(getDegree());
        for( int i = 0; i <= getDegree(); i++ ){
            result.setCoeff(i, -getCoeff(i));
        }
        return result;
    }

    public Polynomial sub (double scalar) {
        Polynomial result = new Polynomial(getDegree());
        result.setCoeff(0, getCoeff(0) - scalar);
        for( int i = 1; i <= getDegree(); i++ ){
            result.setCoeff(i, getCoeff(i));
        }
        return result;
    }

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

    @Override
    public String toString () {
        return toString("x");
    }

    public String toString (String variable) {
        if( getDegree() == 0 ){
            return Double.toString(getCoeff(getDegree()));
        }
        StringBuilder builder = new StringBuilder();
        builder.append(getTerm(variable, false, getDegree()));
        for( int degree = getDegree() - 1; degree >= 0; degree-- ){
            builder.append(getTerm(variable, true, degree));
        }
        return builder.toString();
    }

    private String getTerm (String variable, boolean sign, int degree) {
        double coeff = getCoeff(degree);
        return coeff == 0 ? "" : getValue(sign, degree, coeff) + getPower(variable, degree);
    }

    private String getValue (boolean sign, int degree, double coeff) {
        if( coeff == -1 && degree != 0 ){
            return "-";
        }
        if( coeff == 1 && degree != 0 ){
            return sign ? "+" : "";
        }
        return sign && coeff >= 0.0 ? "+" + coeff : Double.toString(coeff);
    }

    private String getPower (String variable, int degree) {
        switch( degree ){
            case 0:
                return "";
            case 1:
                return variable;
            default:
                return variable + "^" + degree;
        }
    }

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

}
