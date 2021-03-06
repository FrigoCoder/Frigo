
package frigo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Kruskal implements Mst {

    @Override
    public <T extends Comparable<T>> List<Edge> run (Graph graph, EdgeMap<T> weights) {
        List<Edge> edges = new ArrayList<>(graph.edges());
        Collections.sort(edges, (edge1, edge2) -> weights.get(edge1).compareTo(weights.get(edge2)));

        DisjointSet<Node> set = new DisjointSet<>();
        for( Edge edge : edges ){
            set.add(edge.node1);
            set.add(edge.node2);
        }

        List<Edge> result = new LinkedList<>();
        for( Edge edge : edges ){
            if( !set.find(edge.node1).equals(set.find(edge.node2)) ){
                result.add(edge);
                set.union(edge.node1, edge.node2);
            }
        }
        return result;
    }

}
