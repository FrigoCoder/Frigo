
package frigo.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Kruskal {

    public <T, W extends Comparable<W>> List<Edge<T, W>> run (Graph<T, W> graph) {
        List<Edge<T, W>> result = new LinkedList<>();

        DisjointSet<T> set = new DisjointSet<>();
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
