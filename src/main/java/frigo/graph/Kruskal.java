
package frigo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Kruskal implements Mst {

    @Override
    public List<Edge> run (Graph graph) {
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);

        DisjointSet<Object> set = new DisjointSet<>();
        for( Edge edge : edges ){
            set.add(edge.source);
            set.add(edge.target);
        }

        List<Edge> result = new LinkedList<>();
        for( Edge edge : edges ){
            if( !set.find(edge.source).equals(set.find(edge.target)) ){
                result.add(edge);
                set.union(edge.source, edge.target);
            }
        }

        return result;
    }

}
