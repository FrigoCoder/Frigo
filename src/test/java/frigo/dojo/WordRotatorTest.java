
package frigo.dojo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class WordRotatorTest {

    private WordRotator rotator;

    @Test
    public void evenCharactersAreCorrect () {
        assertThat(rotator.getEvenCharacters("abcde"), is("bd"));
    }

    @Test
    public void oddCharactersAreCorrect () {
        assertThat(rotator.getOddCharacters("abcde"), is("ace"));
    }

    @Test
    public void rotateLeftWorksCorrectly () {
        assertThat(rotator.rotateLeft(""), is(""));
        assertThat(rotator.rotateLeft("a"), is("a"));
        assertThat(rotator.rotateLeft("abcde"), is("bcdea"));
    }

    @Test
    public void rotateRightWorksCorrectly () {
        assertThat(rotator.rotateRight(""), is(""));
        assertThat(rotator.rotateRight("a"), is("a"));
        assertThat(rotator.rotateRight("abcde"), is("eabcd"));
    }

    @Test
    public void rotateWorksForLongWords () {
        assertThat("First and third letters should be swapped", rotator.rotateWord("abc"), is("cba"));
        assertThat("First and third, and second and last should swapped", rotator.rotateWord("abcd"), is("cdab"));
        assertThat("Five letter word should be correct", rotator.rotateWord("abcde"), is("edabc"));
        assertThat("Six letter word should be correct", rotator.rotateWord("abcdef"), is("edafcb"));
    }

    @Test
    public void rotateWorksForShortWords () {
        assertThat("Empty string should remain the same", rotator.rotateWord(""), is(""));
        assertThat("One letter string should remain the same", rotator.rotateWord("a"), is("a"));
        assertThat("Two letter string should remain the same", rotator.rotateWord("ab"), is("ab"));
    }

    @Before
    public void setUp () {
        rotator = new WordRotator();
    }

    @Test
    public void testInterleave () {
        assertThat(rotator.interleave("ac", "bd"), is("abcd"));
    }
}
