
package frigo.graph;

import java.util.List;

public interface Mst {

    <T, W extends Comparable<W>> List<Edge<T, W>> run (List<Edge<T, W>> edges);

}
