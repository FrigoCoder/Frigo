
package frigo.exploratory.jremoting;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.InetSocketAddress;

import org.codehaus.jremoting.ConnectionException;
import org.codehaus.jremoting.RedirectedException;
import org.codehaus.jremoting.client.SocketDetails;
import org.codehaus.jremoting.client.monitors.ConsoleClientMonitor;
import org.codehaus.jremoting.client.resolver.ServiceResolver;
import org.codehaus.jremoting.client.transports.RmiTransport;
import org.codehaus.jremoting.server.monitors.ConsoleServerMonitor;
import org.codehaus.jremoting.server.transports.RmiServer;
import org.junit.Test;

public class JRemotingExampleTest {

    @Test
    public void adderService_is_published_and_accessible () throws Exception {
        RmiServer server = createServer(10333);
        server.publish(new AdderImpl(), "AdderService", Adder.class);
        try{
            Adder remoteAdder = resolve("localhost", 10333, "AdderService");
            assertThat(remoteAdder.add(2, 3), is(5));
        }finally{
            server.stop();
        }
    }

    private RmiServer createServer (int port) {
        RmiServer server = new RmiServer(new ConsoleServerMonitor(), new InetSocketAddress(port));
        server.start();
        return server;
    }

    private <T> T resolve (String host, int port, String serviceName) throws ConnectionException, RedirectedException {
        RmiTransport transport = new RmiTransport(new ConsoleClientMonitor(), new SocketDetails(host, port));
        ServiceResolver resolver = new ServiceResolver(transport);
        T remoteAdder = resolver.resolveService(serviceName);
        return remoteAdder;
    }

}
