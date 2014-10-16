
package frigo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Kruskal implements Mst {

    public <T, W extends Comparable<W>> List<Edge<T, W>> run (Graph<T, W> graph) {
        return run(graph.getEdges());
    }

    @Override
    public <T, W extends Comparable<W>> List<Edge<T, W>> run (List<Edge<T, W>> edgesParameter) {
        List<Edge<T, W>> result = new LinkedList<>();

        List<Edge<T, W>> edges = new ArrayList<>(edgesParameter);
        Collections.sort(edges);

        DisjointSet<T> set = new DisjointSet<>();
        for( Edge<T, W> edge : edges ){
            set.add(edge.source);
            set.add(edge.target);
        }

        for( Edge<T, W> edge : edges ){
            if( !set.find(edge.source).equals(set.find(edge.target)) ){
                result.add(edge);
                set.union(edge.source, edge.target);
            }
        }

        return result;
    }

}
