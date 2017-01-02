
package frigo.graph;

import java.util.HashSet;

public class EdgeSet extends HashSet<Edge> {

    public EdgeSet () {}

    public EdgeSet (EdgeSet set) {
        super(set);
    }

    @Override
    public EdgeSet clone () {
        return new EdgeSet(this);
    }

}
