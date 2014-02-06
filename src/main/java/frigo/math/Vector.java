
package frigo.math;

import com.google.common.base.Preconditions;

import frigo.lang.Value;

public class Vector extends Value {

    public final int n;
    private final double[] v;

    public static Vector create(int n) {
        return new Vector(new double[n]);
    }

    public static Vector from(double... values) {
        return new Vector(values.clone());
    }

    private Vector(double[] v) {
        n = v.length;
        this.v = v;
    }

    public double get(int i) {
        return v[i];
    }

    public void set(int i, double x) {
        v[i] = x;
    }

    public Vector mul(double x) {
        Vector result = create(n);
        for (int i = 0; i < n; i++) {
            result.set(i, get(i) * x);
        }
        return result;
    }

    public Vector div(double x) {
        Vector result = create(n);
        for (int i = 0; i < n; i++) {
            result.set(i, get(i) / x);
        }
        return result;
    }

    public Vector add(Vector that) {
        Preconditions.checkArgument(n == that.n);
        Vector result = create(n);
        for (int i = 0; i < result.n; i++) {
            result.set(i, get(i) + that.get(i));
        }
        return result;
    }

    public Vector sub(Vector that) {
        Preconditions.checkArgument(n == that.n);
        Vector result = create(n);
        for (int i = 0; i < n; i++) {
            result.set(i, get(i) - that.get(i));
        }
        return result;
    }

    public double dot(Vector that) {
        Preconditions.checkArgument(n == that.n);
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            result += get(i) * that.get(i);
        }
        return result;
    }

    public double norm() {
        return Math.sqrt(dot(this));
    }

    public Vector normalize() {
        double norm = norm();
        return norm == 0 ? (Vector) clone() : div(norm);
    }

}
