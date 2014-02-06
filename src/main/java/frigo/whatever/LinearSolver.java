
package frigo.whatever;

import com.google.common.base.Preconditions;

import frigo.math.Matrix;
import frigo.math.Vector;

public class LinearSolver {

    private Matrix A;
    private Vector b;

    private Vector x;

    public LinearSolver(Matrix A, Vector b) {
        Preconditions.checkArgument(A.n == b.n);
        this.A = A;
        this.b = b;
        x = Vector.create(A.m);
    }

    public Vector solve() {
        double q = 0.5;
        double scalar = 1.0;
        for (int step = 0; step < x.n * 2; step++) {
            for (int i = 0; i < x.n; i++) {
                tryToImproveWith(i, scalar);
                tryToImproveWith(i, -scalar);
            }
            scalar *= q;
        }
        return x.mul(b.norm() / A.mul(x).norm());
    }

    private void tryToImproveWith(int index, double scalar) {
        Vector xnew = (Vector) x.clone();
        xnew.set(index, xnew.get(index) + scalar);

        Vector Axnew = A.mul(xnew);
        if (Axnew.normalize().sub(b.normalize()).norm() < A.mul(x).normalize().sub(b.normalize()).norm()) {
            x = xnew;
        }
    }

}
