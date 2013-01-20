
package frigo.projecteuler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class Problem357Test {

    private int n;
    private Problem357 problem;

    @Test
    public void desirableNumbersUnder30 () {
        int[] desirables = {1, 2, 6, 10, 22, 30};
        for( int desirable : desirables ){
            assertThat("" + desirable + " should be desirable", problem.isDesirableNumber(desirable), is(true));
        }
    }

    @Before
    public void setUp () {
        n = 30;
        problem = new Problem357();
        problem.run(n);
    }

    @Test
    public void sumOfDesirablesUnder30IsCorrect () {
        assertThat(problem.calculateResult(), is(1L + 2L + 6L + 10L + 22L + 30L));
    }

    @Test
    public void undesirableNumbersUnder30 () {
        int[] undesirables =
            {0, 3, 4, 5, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 23, 24, 25, 26, 27, 28, 29};
        for( int undesirable : undesirables ){
            assertThat("" + undesirable + " should be undesirable", problem.isDesirableNumber(undesirable), is(false));
        }
    }
}
