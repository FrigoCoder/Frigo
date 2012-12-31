
package frigo.math;

public class Matrix {

    private final int columns;
    private final int rows;
    private final Vector[] vectors;

    public Matrix () {
        this(1, 1);
    }

    public Matrix (int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
        vectors = new Vector[columns];
        for( int j = 0; j < columns; j++ ){
            vectors[j] = new Vector(rows);
        }
    }

    public double get (int i, int j) {
        return vectors[j].get(i);
    }

    public Vector getVector (int j) {
        return vectors[j];
    }

    public int m () {
        return columns;
    }

    public Vector mul (Vector vector) {
        if( vector.size() != m() ){
            throw new IllegalArgumentException("Incorrect right hand side dimension");
        }
        Vector result = new Vector(n());
        for( int i = 0; i < n(); i++ ){
            double sum = 0.0;
            for( int j = 0; j < m(); j++ ){
                sum += get(i, j) * vector.get(j);
            }
            result.set(i, sum);
        }
        return result;
    }

    public int n () {
        return rows;
    }

    public void set (int i, int j, double coeff) {
        vectors[j].set(i, coeff);
    }
}
