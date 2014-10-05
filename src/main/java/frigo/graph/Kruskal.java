
package frigo.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Kruskal<T, W extends Comparable<W>> {

    private Graph<T, W> graph;
    private DisjointSet<T> set = new DisjointSet<>();

    public Kruskal (Graph<T, W> graph) {
        this.graph = graph;
    }

    public List<Edge<T, W>> run () {
        List<Edge<T, W>> result = new LinkedList<>();
        for( T node : graph.getNodes() ){
            set.add(node);
        }
        List<Edge<T, W>> edges = graph.getEdges();
        Collections.sort(edges);
        for( Edge<T, W> edge : edges ){
            if( !set.find(edge.source).equals(set.find(edge.target)) ){
                result.add(edge);
                set.union(edge.source, edge.target);
            }
        }
        return result;
    }
}
