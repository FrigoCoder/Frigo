
package frigo.exploratory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UriParser {

    private static final String PROTOCOL = "protocol";
    private static final String HOST = "host";
    private static final int PORT = 8080;
    private static final String PATH = "path";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void newUriDoesNotPutSlashBeforePath() throws URISyntaxException {
        thrown.expect(URISyntaxException.class);
        thrown.expectMessage("Relative path in absolute URI: " + PROTOCOL + "://" + HOST + ":" + PORT + PATH);
        new URI(PROTOCOL, null, HOST, PORT, PATH, null, null);
    }

    @Test
    public void newUriCorreclyConstructsStringRepresentation() throws URISyntaxException {
        URI uri = new URI(PROTOCOL, null, HOST, PORT, "/" + PATH, null, null);
        assertThat(uri.toString(), is(PROTOCOL + "://" + HOST + ":" + PORT + "/" + PATH));
    }

}
