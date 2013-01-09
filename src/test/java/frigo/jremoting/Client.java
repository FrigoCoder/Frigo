
package frigo.jremoting;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.codehaus.jremoting.ConnectionException;
import org.codehaus.jremoting.client.SocketDetails;
import org.codehaus.jremoting.client.monitors.ConsoleClientMonitor;
import org.codehaus.jremoting.client.resolver.ServiceResolver;
import org.codehaus.jremoting.client.streams.ByteStream;
import org.codehaus.jremoting.client.transports.SocketTransport;

public class Client {

    public static void main(String[] args) throws ConnectionException {
        SocketTransport transport =
                new SocketTransport(new ConsoleClientMonitor(), new ByteStream(), new SocketDetails("localhost", 10333));
        ServiceResolver serviceResolver = new ServiceResolver(transport);
        Adder remoteAdder = serviceResolver.resolveService("AdderService");
        assertThat(remoteAdder.add(2, 3), is(5));
    }
}
