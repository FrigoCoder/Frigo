
package frigo.graph;

import java.util.concurrent.atomic.AtomicLong;

import com.google.common.annotations.VisibleForTesting;

public class Node implements Comparable<Node> {

    @VisibleForTesting
    static final AtomicLong serials = new AtomicLong();

    int id;

    @VisibleForTesting
    final long serial;

    public Node (int id) {
        this.id = id;
        serial = serials.getAndIncrement();
    }

    @Override
    public int compareTo (Node node) {
        return serial <= node.serial ? serial < node.serial ? -1 : 0 : 1;
    }

    @Override
    public boolean equals (Object obj) {
        if( !(obj instanceof Node) ){
            return false;
        }
        return serial == ((Node) obj).serial;
    }

    @Override
    public int hashCode () {
        return (int) (serial + (serial >> 32) * 31);
    }
}
