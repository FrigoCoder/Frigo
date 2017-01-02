
package frigo.graph;

import java.util.List;

public interface Mst {

    <T extends Comparable<T>> List<Edge> run (Graph graph, EdgeMap<T> weights);

}
