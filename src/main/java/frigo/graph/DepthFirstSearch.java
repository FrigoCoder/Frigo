
package frigo.graph;

import java.util.HashMap;

public class DepthFirstSearch {

    /**
     * Based on http://www.boost.org/doc/libs/1_63_0/libs/graph/doc/undirected_dfs.html
     */

    @SuppressWarnings("unused")
    public interface Visitor {

        default void initializeVertex (Object node) {}

        default void startVertex (Object node) {}

        default void discoverVertex (Object node) {}

        default void examineEdge (Edge edge) {}

        default void treeEdge (Edge edge) {}

        default void backEdge (Edge edge) {}

        default void finishEdge (Edge edge) {}

        default void finishVertex (Object node) {}

    }

    private enum Color {
        WHITE,
        GREY,
        BLACK
    }

    private Graph graph;
    private Visitor visitor;
    private HashMap<Object, Color> vertexColors = new HashMap<>();
    private HashMap<Edge, Color> edgeColors = new HashMap<>();

    public DepthFirstSearch (Graph graph, Visitor visitor) {
        this.graph = graph;
        this.visitor = visitor;
    }

    public void run () {
        run(null);
    }

    public void run (Object root) {
        for( Object node : graph.getNodes() ){
            vertexColors.put(node, Color.WHITE);
            visitor.initializeVertex(node);
        }
        for( Edge edge : graph.getEdges() ){
            edgeColors.put(edge, Color.WHITE);
        }
        if( root != null ){
            visitor.startVertex(root);
            dfsVisitRecursive(root);
        }
        for( Object node : graph.getNodes() ){
            if( vertexColors.get(node) == Color.WHITE ){
                visitor.startVertex(node);
                dfsVisitRecursive(node);
            }
        }
    }

    private void dfsVisitRecursive (Object source) {
        vertexColors.put(source, Color.GREY);
        visitor.discoverVertex(source);

        for( Edge edge : graph.getEdges(source) ){
            visitor.examineEdge(edge);

            Object target = graph.getTarget(source, edge);
            switch( vertexColors.get(target) ){
                case WHITE:
                    visitor.treeEdge(edge);
                    edgeColors.put(edge, Color.BLACK);
                    dfsVisitRecursive(target);
                    break;
                case GREY:
                    if( edgeColors.get(edge) == Color.WHITE ){
                        visitor.backEdge(edge);
                    }
                    break;
                case BLACK:
                default:
                    break;
            }

            visitor.finishEdge(edge);
        }
        vertexColors.put(source, Color.BLACK);
        visitor.finishVertex(source);
    }

}
