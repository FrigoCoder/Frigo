
package frigo.graph;

import java.util.HashSet;

public class NodeSet extends HashSet<Node> {

    public NodeSet () {}

    public NodeSet (NodeSet set) {
        super(set);
    }

    @Override
    public NodeSet clone () {
        return new NodeSet(this);
    }

}
