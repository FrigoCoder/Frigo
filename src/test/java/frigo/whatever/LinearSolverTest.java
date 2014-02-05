
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
        assertThat(solver.solve(A, b), is(Vector.from(1)));
    }

    @Test
    public void can_solve_2_by_2_problem() {
        Matrix A = new Matrix(2, 2);
        A.setRow(0, 1, 2);
        A.setRow(1, 2, 3);

        Vector b1 = Vector.from(1, 0);
        Vector b2 = Vector.from(0, 1);
        assertThat(solver.solve(A, b1), is(Vector.from(-3, 2)));
        assertThat(solver.solve(A, b2), is(Vector.from(-2, -1)));
    }

}
