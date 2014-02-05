
package frigo.whatever;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.math.Matrix;
import frigo.math.Vector;

public class LinearSolverTest {

    private LinearSolver solver = new LinearSolver();

    @Test
    public void can_solve_1_by_1_problem() {
        Matrix A = new Matrix(1, 1);
        A.setRow(0, 1);

        Vector b = Vector.from(1);

        Vector actual = solver.solve(A, b);
        assertThat(actual, is(Vector.from(1)));
    }
}
