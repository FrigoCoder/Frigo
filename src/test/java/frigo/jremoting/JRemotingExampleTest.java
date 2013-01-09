
package frigo.jremoting;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.InetSocketAddress;

import org.codehaus.jremoting.ConnectionException;
import org.codehaus.jremoting.RedirectedException;
import org.codehaus.jremoting.client.SocketDetails;
import org.codehaus.jremoting.client.monitors.ConsoleClientMonitor;
import org.codehaus.jremoting.client.resolver.ServiceResolver;
import org.codehaus.jremoting.client.transports.SocketTransport;
import org.codehaus.jremoting.server.monitors.ConsoleServerMonitor;
import org.codehaus.jremoting.server.streams.ByteStream;
import org.codehaus.jremoting.server.transports.SocketServer;
import org.junit.Test;

public class JRemotingExampleTest {

    @Test
    public void adderService_is_published_and_accessible() throws Exception {
        SocketServer server =
                new SocketServer(new ConsoleServerMonitor(), new ByteStream(), new InetSocketAddress(10333));
        server.start();
        server.publish(new AdderImpl(), "AdderService", Adder.class);

        try {
            SocketTransport transport =
                    new SocketTransport(new ConsoleClientMonitor(),
                                        new org.codehaus.jremoting.client.streams.ByteStream(),
                                        new SocketDetails("localhost", 10333));
            ServiceResolver resolver = new ServiceResolver(transport);
            Adder remoteAdder = resolver.resolveService("AdderService");
            assertThat(remoteAdder.add(2, 3), is(5));

        } finally {
            server.stop();
        }
    }

    @Test
    public void adderService_is_published_and_accessible_refactored() throws Exception {
        SocketServer server = createServer(10333);
        server.publish(new AdderImpl(), "AdderService", Adder.class);
        try {
            Adder remoteAdder = resolve("localhost", 10333, "AdderService");
            assertThat(remoteAdder.add(2, 3), is(5));
        } finally {
            server.stop();
        }

    }

    private SocketServer createServer(int port) {
        SocketServer server =
                new SocketServer(new ConsoleServerMonitor(), new ByteStream(), new InetSocketAddress(port));
        server.start();
        return server;
    }

    private <T> T resolve(String host, int port, String serviceName) throws ConnectionException, RedirectedException {
        SocketTransport transport =
                new SocketTransport(new ConsoleClientMonitor(), new org.codehaus.jremoting.client.streams.ByteStream(),
                                    new SocketDetails(host, port));
        ServiceResolver resolver = new ServiceResolver(transport);
        T remoteAdder = resolver.resolveService(serviceName);
        return remoteAdder;
    }
}
