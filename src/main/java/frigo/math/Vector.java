
package frigo.math;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;

public class Vector {

    private static double epsilon = 1.0e-20;
    private final double[] v;

    public Vector () {
        this(1);
    }

    public Vector (double[] array) {
        v = array.clone();
    }

    public Vector (int n) {
        v = new double[n];
    }

    public Vector add (Vector that) {
        checkArgument(size() == that.size(), "Vectors must have the same dimension");
        Vector result = new Vector(size());
        for( int i = 0; i < result.size(); i++ ){
            result.set(i, get(i) + that.get(i));
        }
        return result;
    }

    public Vector div (double x) {
        Vector result = new Vector(size());
        for( int i = 0; i < size(); i++ ){
            result.set(i, get(i) / x);
        }
        return result;
    }

    public double dot (Vector that) {
        checkArgument(size() == that.size(), "Vectors must have the same dimension");
        double result = 0.0;
        for( int i = 0; i < size(); i++ ){
            result += get(i) * that.get(i);
        }
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if( !(obj instanceof Vector) ){
            return false;
        }
        Vector that = (Vector) obj;
        if( size() != that.size() ){
            return false;
        }
        for( int i = 0; i < size(); i++ ){
            if( !equals(get(i), that.get(i)) ){
                return false;
            }
        }
        return true;
    }

    public double get (int i) {
        return v[i];
    }

    @Override
    public int hashCode () {
        throw new UnsupportedOperationException();
    }

    public Vector mul (double x) {
        Vector result = new Vector(size());
        for( int i = 0; i < size(); i++ ){
            result.set(i, get(i) * x);
        }
        return result;
    }

    public void set (int i, double x) {
        v[i] = x;
    }

    public int size () {
        return v.length;
    }

    public Vector sub (Vector that) {
        checkArgument(size() == that.size(), "Vectors must have the same dimension");
        Vector result = new Vector(size());
        for( int i = 0; i < size(); i++ ){
            result.set(i, get(i) - that.get(i));
        }
        return result;
    }

    @Override
    public String toString () {
        return Arrays.toString(v);
    }

    private boolean equals (double x, double y) {
        return Math.abs(x - y) < epsilon;
    }
}
