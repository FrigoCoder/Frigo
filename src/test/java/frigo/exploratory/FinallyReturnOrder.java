
package frigo.exploratory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FinallyReturnOrder {

    private String greeting = "";

    @Test
    public void finallyBlockReturnsNewValue() {
        assertThat(finallyBlockReturns(), is("Hello World!"));
        assertThat(greeting, is("Hello World!"));
    }

    @Test
    public void tryBlockReturnsOldValue() {
        assertThat(finallyBlockDoesNotReturn(), is("Hello "));
        assertThat(greeting, is("Hello World!"));
    }

    private String finallyBlockReturns() {
        try {
            return concatAndGet("Hello ");
        } finally {
            return concatAndGet("World!");
        }
    }

    private String finallyBlockDoesNotReturn() {
        try {
            return concatAndGet("Hello ");
        } finally {
            concatAndGet("World!");
        }
    }

    private String concatAndGet(String word) {
        greeting += word;
        return greeting;
    }
}
