
package frigo.graph;

public class Node {

    final int index;

    Node (int index) {
        this.index = index;
    }

    @Override
    public int hashCode () {
        return index;
    }

    @Override
    public String toString () {
        return "Node[" + index + "]";
    }

}
