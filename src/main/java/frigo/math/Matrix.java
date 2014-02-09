
package frigo.math;

import com.google.common.base.Preconditions;

public class Matrix {

    public final int n;
    public final int m;
    private final Vector[] vectors;

    public static Matrix identity (int n) {
        Matrix matrix = new Matrix(n, n);
        for( int i = 0; i < n; i++ ){
            matrix.set(i, i, 1.0);
        }
        return matrix;
    }

    public Matrix (int rows, int columns) {
        n = rows;
        m = columns;
        vectors = new Vector[columns];
        for( int j = 0; j < columns; j++ ){
            vectors[j] = Vector.create(rows);
        }
    }

    public double get (int i, int j) {
        return vectors[j].get(i);
    }

    public void set (int i, int j, double value) {
        vectors[j].set(i, value);
    }

    public Vector vector (int j) {
        return vectors[j];
    }

    public void setRow (int i, double... values) {
        Preconditions.checkArgument(m == values.length);
        for( int j = 0; j < m; j++ ){
            set(i, j, values[j]);
        }
    }

    public Vector mul (Vector x) {
        Preconditions.checkArgument(m == x.n);
        Vector result = Vector.create(n);
        for( int j = 0; j < m; j++ ){
            result = result.add(vector(j).mul(x.get(j)));
        }
        return result;
    }

}
