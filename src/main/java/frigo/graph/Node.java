
package frigo.graph;

import java.util.concurrent.atomic.AtomicLong;

public class Node implements Comparable<Node> {

    protected static final AtomicLong serials = new AtomicLong();

    protected static void resetSerials () {
        serials.set(0);
    }

    protected int id;
    protected final long serial;

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
