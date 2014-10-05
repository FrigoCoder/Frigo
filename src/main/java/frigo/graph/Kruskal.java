
package frigo.graph;

import java.util.List;

public class Kruskal<T, W> {

    private Graph<T, W> graph;

    public Kruskal (Graph<T, W> graph) {
        this.graph = graph;
    }

    public List<Edge<T, W>> run () {
        return graph.getEdges();
    }

}
