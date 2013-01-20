
package frigo.filter;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ConvoluterTest {

    private Convoluter convoluter = new Convoluter(new Linear());
    private double[] source = {1, 4, 9, 16, 25, 36};

    @Test
    public void convolute_scale_one_should_return_original_array () {
        assertThat(convoluter.convolute(source), equalTo(source));
    }

    @Test
    public void convolute_scale_two_should_return_blurred_array () {
        double[] expected = {2, 4.5, 9.5, 16.5, 25.5, 32.333333333333333333333333333333};
        assertThat(convoluter.convolute(source, 2.0), equalTo(expected));
    }

}
