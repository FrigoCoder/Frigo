
package frigo.graph;

import java.util.List;

import frigo.math.MedianOfMedians;

public class FrigoMst implements Mst {

    @Override
    public <T, W extends Comparable<W>> List<Edge<T, W>> run (List<Edge<T, W>> edges) {
        // W median = median(edges);
        //

        return edges;
    }

    private <T, W extends Comparable<W>> W median (List<Edge<T, W>> edges) {
        return MedianOfMedians.median(edges).weight;
    }

}
