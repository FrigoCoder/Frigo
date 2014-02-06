
package frigo.whatever;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.math.Matrix;
import frigo.math.Vector;

public class LinearSolverTest {

    @Test
    public void can_solve_1_by_1_problem() {
        Matrix A = new Matrix(1, 1);
        A.setRow(0, 1);

        Vector b = Vector.from(1);

        LinearSolver solver = new LinearSolver(A, b);
        assertThat(solver.solve(), is(Vector.from(1)));
    }

    @Test
    public void can_solve_2_by_2_problem() {
        Matrix A = new Matrix(2, 2);
        A.setRow(0, 1, 2);
        A.setRow(1, 2, 3);

        Vector b1 = Vector.from(1, 0);
        LinearSolver solver1 = new LinearSolver(A, b1);
        assertThat(solver1.solve(), is(Vector.from(-3, 2)));

        Vector b2 = Vector.from(0, 1);
        LinearSolver solver2 = new LinearSolver(A, b2);
        assertThat(solver2.solve(), is(Vector.from(2, -1)));
    }

}
