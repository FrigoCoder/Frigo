
package frigo.math;

import static com.google.common.base.Preconditions.checkArgument;
import frigo.lang.Value;

public class Vector extends Value {

    public final int n;
    private final double[] v;

    public static Vector from(double... values) {
        Vector result = new Vector(values.length);
        for (int i = 0; i < values.length; i++) {
            result.set(i, values[i]);
        }
        return result;
    }

    public Vector(int n) {
        this.n = n;
        v = new double[n];
    }

    public double get(int i) {
        return v[i];
    }

    public void set(int i, double x) {
        v[i] = x;
    }

    public Vector add(Vector that) {
        checkArgument(n == that.n);
        Vector result = new Vector(n);
        for (int i = 0; i < result.n; i++) {
            result.set(i, get(i) + that.get(i));
        }
        return result;
    }

    public Vector sub(Vector that) {
        checkArgument(n == that.n);
        Vector result = new Vector(n);
        for (int i = 0; i < n; i++) {
            result.set(i, get(i) - that.get(i));
        }
        return result;
    }

    public Vector div(double x) {
        Vector result = new Vector(n);
        for (int i = 0; i < n; i++) {
            result.set(i, get(i) / x);
        }
        return result;
    }

    public double dot(Vector that) {
        checkArgument(n == that.n);
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            result += get(i) * that.get(i);
        }
        return result;
    }

    public Vector mul(double x) {
        Vector result = new Vector(n);
        for (int i = 0; i < n; i++) {
            result.set(i, get(i) * x);
        }
        return result;
    }

}
