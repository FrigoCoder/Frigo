
package frigo.crypto;

import static frigo.util.ArraysAux.toHexString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertThat;

import java.security.MessageDigest;

import org.junit.Test;

public class Sha256Test {

    @Test
    public void empty_string () throws Exception {
        assertHash("", "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");
    }

    @Test
    public void quick_brown_fox () throws Exception {
        assertHash("The quick brown fox jumps over the lazy dog",
            "d7a8fbb307d7809469ca9abcb0082e4f8d5651e46d3cdb762d02d0bf37c9e592");
    }

    @Test
    public void quick_brown_fox_dot () throws Exception {
        assertHash("The quick brown fox jumps over the lazy dog.",
            "ef537f25c895bfa782526529a9b63d97aa631564d5d789c2b765448c8635fb6c");
    }

    private void assertHash (String string, String expected) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(string.getBytes("UTF-8"));
        assertThat(toHexString(md.digest()), equalToIgnoringCase(expected));
    }
}
