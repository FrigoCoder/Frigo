
package frigo.filter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SamplerTest {

    private Sampler sampler = new Sampler(new Linear());
    private double[] source = {1, 4, 9, 16, 25, 36};

    @Test
    public void sample_integer_center_and_one_size_should_return_the_array_value () {
        for( int i = 0; i < source.length; i++ ){
            assertThat(sampler.sample(source, i + 0.5, 1.0), is(source[i]));
        }
    }

    @Test
    public void sample_integer_center_and_two_size_should_blur_linearly () {
        double[] expected = {2, 4.5, 9.5, 16.5, 25.5, 32.333333333333333333333333333333};
        for( int i = 0; i < source.length; i++ ){
            assertThat(sampler.sample(source, i + 0.5, 2.0), is(expected[i]));
        }
    }

    @Test
    public void sample_empty_interval () {
        assertThat(sampler.sample(source, 0.5, 0.0), is(0.0));
    }

}
