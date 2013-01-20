
package frigo.graph;

import java.util.concurrent.atomic.AtomicLong;
import com.google.common.annotations.VisibleForTesting;

public class Edge implements Comparable<Edge> {

    @VisibleForTesting
    static final AtomicLong serials = new AtomicLong();

    int id;

    @VisibleForTesting
    final long serial;

    public Edge (int id) {
        this.id = id;
        serial = serials.getAndIncrement();
    }

    @Override
    public int compareTo (Edge edge) {
        return serial <= edge.serial ? serial < edge.serial ? -1 : 0 : 1;
    }

    @Override
    public boolean equals (Object obj) {
        if( !(obj instanceof Edge) ){
            return false;
        }
        return serial == ((Edge) obj).serial;
    }

    @Override
    public int hashCode () {
        return (int) (serial + (serial >> 32) * 31);
    }
}
